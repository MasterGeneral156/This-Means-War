package themastergeneral.thismeanswar.recipe;

import java.util.stream.Stream;

import com.google.gson.JsonObject;

import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import themastergeneral.thismeanswar.registry.TMWRecipeTypeRegistration;

public class CrusherRecipe implements Recipe<Container> {

	   private final ResourceLocation id;
	   final Ingredient base;
	   final ItemStack result;

	   public CrusherRecipe(ResourceLocation p_267143_, Ingredient p_266787_, ItemStack p_267031_) {
	      this.id = p_267143_;
	      this.base = p_266787_;
	      this.result = p_267031_;
	   }

	   public boolean matches(Container container, Level level) {
		   ItemStack inputStack = container.getItem(0);  // Get the item from the container
		   int requiredCount = this.base.getItems()[0].getCount();

		   return this.base.test(inputStack) && inputStack.getCount() >= requiredCount;
	   }

	public ItemStack assemble(Container container, RegistryAccess registryAccess) {
		ItemStack inputStack = container.getItem(0);
		int requiredCount = this.base.getItems()[0].getCount();

		inputStack.shrink(requiredCount);

		ItemStack resultStack = this.result.copy();
		CompoundTag tag = inputStack.getTag();

		if (tag != null) {
			resultStack.setTag(tag.copy());
		}

		return resultStack;
	}

	   public ItemStack getResultItem(RegistryAccess p_267209_) {
	      return this.result;
	   }

	   public boolean isBaseIngredient(ItemStack p_267276_) {
	      return this.base.test(p_267276_);
	   }

	   public ResourceLocation getId() {
	      return this.id;
	   }

	   public RecipeSerializer<?> getSerializer() {
	      return TMWRecipeTypeRegistration.CRUSHER_SERIALIZER.get();
	   }

	   public boolean isIncomplete() {
	      return Stream.of(this.base).anyMatch(net.minecraftforge.common.ForgeHooks::hasNoElements);
	   }

	   @Override
	   public RecipeType<CrusherRecipe> getType() {
	       // Return an instance of your recipe type
	       return TMWRecipeTypeRegistration.CRUSHER_TYPE.get();
	   }

	   public Ingredient returnBase()
	   {
		   return this.base;
	   }

	   public static class Serializer implements RecipeSerializer<CrusherRecipe> {
	      public CrusherRecipe fromJson(ResourceLocation p_266953_, JsonObject p_266720_) {
	         Ingredient ingredient1 = Ingredient.fromJson(GsonHelper.getNonNull(p_266720_, "base"));
	         ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(p_266720_, "result"));
	         return new CrusherRecipe(p_266953_, ingredient1, itemstack);
	      }

	      public CrusherRecipe fromNetwork(ResourceLocation p_267117_, FriendlyByteBuf p_267316_) {
	         Ingredient ingredient1 = Ingredient.fromNetwork(p_267316_);
	         ItemStack itemstack = p_267316_.readItem();
	         return new CrusherRecipe(p_267117_, ingredient1, itemstack);
	      }

	      public void toNetwork(FriendlyByteBuf p_266746_, CrusherRecipe p_266927_) {
	         p_266927_.base.toNetwork(p_266746_);
	         p_266746_.writeItem(p_266927_.result);
	      }
	   }

		@Override
		public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
			return true;
		}
	}