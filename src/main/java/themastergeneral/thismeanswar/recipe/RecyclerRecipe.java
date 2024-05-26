package themastergeneral.thismeanswar.recipe;

import com.google.gson.JsonObject;

import java.util.stream.Stream;
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

public class RecyclerRecipe implements Recipe<Container> {
	
   private final ResourceLocation id;
   final Ingredient base;
   final ItemStack result;

   public RecyclerRecipe(ResourceLocation p_267143_, Ingredient p_266787_, ItemStack p_267031_) {
      this.id = p_267143_;
      this.base = p_266787_;
      this.result = p_267031_;
   }

   public boolean matches(Container p_266855_, Level p_266781_) {
      return this.base.test(p_266855_.getItem(0));
   }

   public ItemStack assemble(Container p_267036_, RegistryAccess p_266699_) {
      ItemStack itemstack = this.result.copy();
      CompoundTag compoundtag = p_267036_.getItem(0).getTag();
      if (compoundtag != null) {
         itemstack.setTag(compoundtag.copy());
      }

      return itemstack;
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
      return TMWRecipeTypeRegistration.RECYCLER.get();
   }

   public boolean isIncomplete() {
      return Stream.of(this.base).anyMatch(net.minecraftforge.common.ForgeHooks::hasNoElements);
   }
   
   @Override
   public RecipeType<RecyclerRecipe> getType() {
       // Return an instance of your recipe type
       return TMWRecipeTypeRegistration.RECYCLER_TYPE.get();
   }
   
   public Ingredient returnBase()
   {
	   return this.base;
   }

   public static class Serializer implements RecipeSerializer<RecyclerRecipe> {
      public RecyclerRecipe fromJson(ResourceLocation p_266953_, JsonObject p_266720_) {
         Ingredient ingredient1 = Ingredient.fromJson(GsonHelper.getNonNull(p_266720_, "base"));
         ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(p_266720_, "result"));
         return new RecyclerRecipe(p_266953_, ingredient1, itemstack);
      }

      public RecyclerRecipe fromNetwork(ResourceLocation p_267117_, FriendlyByteBuf p_267316_) {
         Ingredient ingredient1 = Ingredient.fromNetwork(p_267316_);
         ItemStack itemstack = p_267316_.readItem();
         return new RecyclerRecipe(p_267117_, ingredient1, itemstack);
      }

      public void toNetwork(FriendlyByteBuf p_266746_, RecyclerRecipe p_266927_) {
         p_266927_.base.toNetwork(p_266746_);
         p_266746_.writeItem(p_266927_.result);
      }
   }

	@Override
	public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
		return true;
	}
}
