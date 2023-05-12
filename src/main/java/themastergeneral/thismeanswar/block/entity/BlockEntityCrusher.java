package themastergeneral.thismeanswar.block.entity;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import themastergeneral.thismeanswar.block.BlockCrusher;
import themastergeneral.thismeanswar.registry.TMWBlockEntityRegistry;
import themastergeneral.thismeanswar.registry.TMWRecipeTypeRegistration;

/*public class BlockEntityCrusher extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible {

   protected static final int SLOT_INPUT = 0;
   protected static final int SLOT_FUEL = 1;
   protected static final int SLOT_RESULT = 2;
   public static final int DATA_LIT_TIME = 0;
   private static final int[] SLOTS_FOR_UP = new int[]{0};
   private static final int[] SLOTS_FOR_DOWN = new int[]{2, 1};
   private static final int[] SLOTS_FOR_SIDES = new int[]{1};
   public static final int DATA_LIT_DURATION = 1;
   public static final int DATA_COOKING_PROGRESS = 2;
   public static final int DATA_COOKING_TOTAL_TIME = 3;
   public static final int NUM_DATA_VALUES = 4;
   public static final int BURN_TIME_STANDARD = 200;
   public static final int BURN_COOL_SPEED = 2;
   protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
   int litTime;
   int litDuration;
   int cookingProgress;
   int cookingTotalTime;
   
   protected final ContainerData dataAccess = new ContainerData() {
      public int get(int p_58431_) {
         switch(p_58431_) {
         case 0:
            return BlockEntityCrusher.this.litTime;
         case 1:
            return BlockEntityCrusher.this.litDuration;
         case 2:
            return BlockEntityCrusher.this.cookingProgress;
         case 3:
            return BlockEntityCrusher.this.cookingTotalTime;
         default:
            return 0;
         }
      }

	      @Override
	      public void set(int p_58433_, int p_58434_) {
	          switch(p_58433_) {
	          case 0:
	        	  BlockEntityCrusher.this.litTime = p_58434_;
	             break;
	          case 1:
	        	  BlockEntityCrusher.this.litDuration = p_58434_;
	             break;
	          case 2:
	        	  BlockEntityCrusher.this.cookingProgress = p_58434_;
	             break;
	          case 3:
	        	  BlockEntityCrusher.this.cookingTotalTime = p_58434_;
	          }

	       }

		@Override
		public int getCount() {
			return 4;
		}

   	};
   	
   	private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    private final RecipeType<? extends CrusherRecipe> recipeType = TMWRecipeTypeRegistration.CRUSHING_RECIPE;
    
    public BlockEntityCrusher(BlockEntityType<?> p_155076_, BlockPos p_155077_, BlockState p_155078_, RecipeType<? extends CrusherRecipe> p_154994_) {
		super(p_155076_, p_155077_, p_155078_);
	}
    
     public BlockEntityCrusher(BlockPos p_155229_, BlockState p_155230_) {
		super(TMWBlockEntityRegistry.crusher.get(), p_155229_, p_155230_);
	}
   	
	@Override
	public int getContainerSize() {
		return this.items.size();
	}
	
	@Override
	public boolean isEmpty() {
		for(ItemStack itemstack : this.items) {
	         if (!itemstack.isEmpty()) {
	            return false;
	         }
	      }
	      return true;
	}
	
	@Override
	public ItemStack getItem(int slot) {
		return this.items.get(slot);
	}
	
	@Override
	public ItemStack removeItem(int p_18942_, int p_18943_) {
		return ContainerHelper.removeItem(this.items, p_18942_, p_18943_);
	}
	
	@Override
	public ItemStack removeItemNoUpdate(int p_18951_) {
		return ContainerHelper.takeItem(this.items, p_18951_);
	}
	
	@Override
	public void setItem(int slot, ItemStack stack) {
		ItemStack itemstack = this.items.get(slot);
		boolean flag = !stack.isEmpty() && stack.sameItem(itemstack) && ItemStack.tagMatches(stack, itemstack);
		this.items.set(slot, stack);
		if (stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
		}
		
		if (slot == 0 && !flag) {
		   this.cookingTotalTime = getTotalCookTime(this.level, this.recipeType, this);
		   this.cookingProgress = 0;
		   this.setChanged();
		}
	}
	
	@Override
	public boolean stillValid(Player player) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
	         return false;
	      } else {
	         return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
	      }
	}
	
	@Override
	public void clearContent() {
		this.items.clear();
	}
	
	@Override
	public void fillStackedContents(StackedContents p_40281_) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setRecipeUsed(Recipe<?> recipe) {
		if (recipe != null) {
	         ResourceLocation resourcelocation = recipe.getId();
	         this.recipesUsed.addTo(resourcelocation, 1);
	      }
	}
	
	@Nullable
	@Override
	public Recipe<?> getRecipeUsed() {
		return null;
	}
	
	@Override
	public int[] getSlotsForFace(Direction dir) {
		if (dir == Direction.DOWN) {
			return SLOTS_FOR_DOWN;
		} else {
			return dir == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
		}
	}
	
	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, Direction dir) {
		return this.canPlaceItem(slot, stack);
	}
	
	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
		if (dir == Direction.DOWN && slot == 1) {
			return stack.is(Items.WATER_BUCKET) || stack.is(Items.BUCKET);
		} else {
			return true;
		}
	}
	
	public static void tick(Level level, BlockPos blockpos, BlockState blockstate, BlockEntityCrusher e) {
		boolean flag = e.isLit();
	    boolean flag1 = false;
	    if (e.isLit()) {
	         --e.litTime;
	      }
	    ItemStack itemstack = e.items.get(1);
	      if (e.isLit() || !itemstack.isEmpty() && !e.items.get(0).isEmpty()) {
	         Recipe<?> recipe = level.getRecipeManager().getRecipeFor((RecipeType<CrusherRecipe>)e.recipeType, e, level).orElse(null);
	         int i = e.getMaxStackSize();
	         if (!e.isLit() && e.canBurn(recipe, e.items, i)) {
	            e.litTime = e.getBurnDuration(itemstack);
	            e.litDuration = e.litTime;
	            if (e.isLit()) {
	               flag1 = true;
	               if (itemstack.hasCraftingRemainingItem())
	                  e.items.set(1, itemstack.getCraftingRemainingItem());
	               else
	               if (!itemstack.isEmpty()) {
	                  Item item = itemstack.getItem();
	                  itemstack.shrink(1);
	                  if (itemstack.isEmpty()) {
	                     e.items.set(1, itemstack.getCraftingRemainingItem());
	                  }
	               }
	            }
	         }

	         if (e.isLit() && e.canBurn(recipe, e.items, i)) {
	            ++e.cookingProgress;
	            if (e.cookingProgress == e.cookingTotalTime) {
	               e.cookingProgress = 0;
	               e.cookingTotalTime = getTotalCookTime(level, e.recipeType, e);
	               if (e.burn(recipe, e.items, i)) {
	                  e.setRecipeUsed(recipe);
	               }

	            }
	         } else {
	            e.cookingProgress = 0;
	         }
	      } else if (!e.isLit() && e.cookingProgress > 0) {
	         e.cookingProgress = Mth.clamp(e.cookingProgress - 2, 0, e.cookingTotalTime);
	      }

	      if (flag != e.isLit()) {
	         flag1 = true;
	         blockstate = blockstate.setValue(BlockCrusher.LIT, Boolean.valueOf(e.isLit()));
	         level.setBlock(blockpos, blockstate, 3);
	      }

	      if (flag1) {
	         setChanged(level, blockpos, blockstate);
	      }
	}
	
	private boolean canBurn(@Nullable Recipe<?> recipe, NonNullList<ItemStack> stack, int slot) {
	      if (!stack.get(0).isEmpty() && recipe != null) {
	         ItemStack itemstack = ((Recipe<WorldlyContainer>) recipe).assemble(this);
	         if (itemstack.isEmpty()) {
	            return false;
	         } else {
	            ItemStack itemstack1 = stack.get(2);
	            if (itemstack1.isEmpty()) {
	               return true;
	            } else if (!itemstack1.sameItem(itemstack)) {
	               return false;
	            } else if (itemstack1.getCount() + itemstack.getCount() <= slot && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) {
	               return true;
	            } else {
	               return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();
	            }
	         }
	      } else {
	         return false;
	      }
	   }

	   private boolean burn(@Nullable Recipe<?> recipe, NonNullList<ItemStack> stack, int slot) {
	      if (recipe != null && this.canBurn(recipe, stack, slot)) {
	         ItemStack itemstack = stack.get(0);
	         ItemStack itemstack1 = ((Recipe<WorldlyContainer>) recipe).assemble(this);
	         ItemStack itemstack2 = stack.get(2);
	         if (itemstack2.isEmpty()) {
	        	 stack.set(2, itemstack1.copy());
	         } else if (itemstack2.is(itemstack1.getItem())) {
	            itemstack2.grow(itemstack1.getCount());
	         }

	         if (itemstack.is(Blocks.WET_SPONGE.asItem()) && !stack.get(1).isEmpty() && stack.get(1).is(Items.BUCKET)) {
	        	 stack.set(1, new ItemStack(Items.WATER_BUCKET));
	         }

	         itemstack.shrink(1);
	         return true;
	      } else {
	         return false;
	      }
	   }
	
	@Override
	public BlockEntityType<?> getType() {
		return TMWBlockEntityRegistry.crusher.get();
	}
	
	@Override
	protected Component getDefaultName() {
		return ModUtils.displayString("thismeanswar.crusher");
	}
	
	@Override
	protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void load(CompoundTag p_155025_) {
		super.load(p_155025_);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(p_155025_, this.items);
		this.litTime = p_155025_.getInt("BurnTime");
		this.cookingProgress = p_155025_.getInt("CookTime");
		this.cookingTotalTime = p_155025_.getInt("CookTimeTotal");
		this.litDuration = this.getBurnDuration(this.items.get(1));
		CompoundTag compoundtag = p_155025_.getCompound("RecipesUsed");
		
		for(String s : compoundtag.getAllKeys()) {
		   this.recipesUsed.put(new ResourceLocation(s), compoundtag.getInt(s));
		}
	   }

	@Override
	   protected void saveAdditional(CompoundTag p_187452_) {
	      super.saveAdditional(p_187452_);
	    p_187452_.putInt("BurnTime", this.litTime);
	    p_187452_.putInt("CookTime", this.cookingProgress);
	    p_187452_.putInt("CookTimeTotal", this.cookingTotalTime);
	    ContainerHelper.saveAllItems(p_187452_, this.items);
	    CompoundTag compoundtag = new CompoundTag();
	    this.recipesUsed.forEach((p_187449_, p_187450_) -> {
	       compoundtag.putInt(p_187449_.toString(), p_187450_);
	    });
	    p_187452_.put("RecipesUsed", compoundtag);
	}
	
	protected int getBurnDuration(ItemStack p_58343_) {
	   if (p_58343_.isEmpty()) {
	      return 0;
	   } else {
	      return net.minecraftforge.common.ForgeHooks.getBurnTime(p_58343_, this.recipeType);
	   }
	}
	
	private static int getTotalCookTime(Level p_155010_, RecipeType<? extends CrusherRecipe> p_155011_, Container p_155012_) {
	   return p_155010_.getRecipeManager().getRecipeFor((RecipeType<CrusherRecipe>)p_155011_, p_155012_, p_155010_).map(CrusherRecipe::getCrushTime).orElse(200);
	}
	
	public static boolean isFuel(ItemStack p_58400_) {
		return net.minecraftforge.common.ForgeHooks.getBurnTime(p_58400_, null) > 0;
	}
	
	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
      if (slot == 2) {
         return false;
      } else if (slot != 1) {
         return true;
      } else {
         ItemStack itemstack = this.items.get(1);
         return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, this.recipeType) > 0 || stack.is(Items.BUCKET) && !itemstack.is(Items.BUCKET);
      }
    }
	
	private boolean isLit() {
	      return this.litTime > 0;
   }
}*/