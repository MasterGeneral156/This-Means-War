package themastergeneral.thismeanswar.menu;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.TMWBlocks;
import themastergeneral.thismeanswar.recipe.BulletRecipe;
import themastergeneral.thismeanswar.recipe.BulletRecipeType;
import themastergeneral.thismeanswar.registry.TMWRecipeTypeRegistration;

public class BulletWorkshopMenu extends ItemCombinerMenu {
	public static final int TEMPLATE_SLOT = 0;
	public static final int BASE_SLOT = 1;
	public static final int ADDITIONAL_SLOT = 2;
	public static final int RESULT_SLOT = 3;
	public static final int TEMPLATE_SLOT_X_PLACEMENT = 8;
	public static final int BASE_SLOT_X_PLACEMENT = 26;
	public static final int ADDITIONAL_SLOT_X_PLACEMENT = 44;
	private static final int RESULT_SLOT_X_PLACEMENT = 98;
	public static final int SLOT_Y_PLACEMENT = 48;
	private final Level level;
	@Nullable
	private BulletRecipe selectedRecipe;
	private final List<BulletRecipe> recipes;
   
	public BulletWorkshopMenu(int p_40245_, Inventory p_40246_) {
		this(p_40245_, p_40246_, ContainerLevelAccess.NULL);
	}
	
	public BulletWorkshopMenu(int p_40248_, Inventory p_40249_, ContainerLevelAccess p_40250_) {
		super(MenuType.SMITHING, p_40248_, p_40249_, p_40250_);
		this.level = p_40249_.player.level();
		this.recipes = this.level.getRecipeManager().getAllRecipesFor(TMWRecipeTypeRegistration.SMITHING_TYPE.get());
	}

	@Override
	protected boolean mayPickup(Player player, boolean bool) {
		return this.selectedRecipe != null && this.selectedRecipe.matches(this.inputSlots, this.level);
	}

	@Override
	protected void onTake(Player player, ItemStack stack) {
		stack.onCraftedBy(player.level(), player, stack.getCount());
			this.resultSlots.awardUsedRecipes(player, this.getRelevantItems());
			this.shrinkStackInSlot(0);
			this.shrinkStackInSlot(1);
			this.shrinkStackInSlot(2);
			this.access.execute((level, pos) -> {
	         level.levelEvent(1044, pos, 0);
	      });
	}

	private List<ItemStack> getRelevantItems() {
	      return List.of(this.inputSlots.getItem(0), this.inputSlots.getItem(1), this.inputSlots.getItem(2));
	   }

	   private void shrinkStackInSlot(int p_40271_) {
	      ItemStack itemstack = this.inputSlots.getItem(p_40271_);
	      if (!itemstack.isEmpty()) 
	      {
	    	  if ((!itemstack.isDamageableItem()) || (itemstack.getItem() instanceof ArmorItem) || (itemstack.getItem() instanceof TieredItem))
	    	  {
	    		  itemstack.shrink(1);
	    		  this.inputSlots.setItem(p_40271_, itemstack);
	    	  }
	    	  else
	    	  {
	    		  if(itemstack.hurt(1, RandomSource.createNewThreadLocalInstance(), null))
	    				itemstack = ItemStack.EMPTY;
	    		  this.inputSlots.setItem(p_40271_, itemstack);
	    	  }
	      }

	   }
	   
	@Override
	protected boolean isValidBlock(BlockState state) {
		return state.is(TMWBlocks.smithing_table);
	}

	@Override
	public void createResult() {
		List<BulletRecipe> list = this.level.getRecipeManager().getRecipesFor(TMWRecipeTypeRegistration.SMITHING_TYPE.get(), this.inputSlots, this.level);
	      if (list.isEmpty()) {
	         this.resultSlots.setItem(0, ItemStack.EMPTY);
	      } else {
	    	  BulletRecipe smithingrecipe = list.get(0);
	         ItemStack itemstack = smithingrecipe.assemble(this.inputSlots, this.level.registryAccess());
	         if (itemstack.isItemEnabled(this.level.enabledFeatures())) {
	            this.selectedRecipe = smithingrecipe;
	            this.resultSlots.setRecipeUsed(smithingrecipe);
	            this.resultSlots.setItem(0, itemstack);
	         }
	      }
	}

	@Override
	protected ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
      return ItemCombinerMenuSlotDefinition.create().withSlot(0, 8, 48, (p_266643_) -> {
         return this.recipes.stream().anyMatch((p_266642_) -> {
            return p_266642_.isTemplateIngredient(p_266643_);
         });
      }).withSlot(1, 26, 48, (p_286208_) -> {
         return this.recipes.stream().anyMatch((p_286206_) -> {
            return p_286206_.isBaseIngredient(p_286208_);
         });
      }).withSlot(2, 44, 48, (p_286207_) -> {
         return this.recipes.stream().anyMatch((p_286204_) -> {
            return p_286204_.isAdditionIngredient(p_286207_);
         });
      }).withResultSlot(3, 98, 48).build();
   }

}
