package themastergeneral.thismeanswar.recipe;

import com.google.gson.JsonObject;
import com.mojang.datafixers.types.templates.List;
import com.mojang.serialization.Codec;

import java.util.stream.Stream;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;
import themastergeneral.thismeanswar.registry.TMWRecipeTypeRegistration;

public class BulletRecipe implements SmithingRecipe {
	
   final Ingredient template;
   final Ingredient base;
   final Ingredient addition;
   final ItemStack result;
   
   public BulletRecipe(Ingredient p_266750_, Ingredient p_266787_, Ingredient p_267292_, ItemStack p_267031_) {
	      this.template = p_266750_;
	      this.base = p_266787_;
	      this.addition = p_267292_;
	      this.result = p_267031_;
   }

   public boolean matches(Container p_266855_, Level p_266781_) {
      return this.template.test(p_266855_.getItem(0)) && this.base.test(p_266855_.getItem(1)) && this.addition.test(p_266855_.getItem(2));
   }

   public ItemStack assemble(Container p_267036_, RegistryAccess p_266699_) {
      ItemStack itemstack = this.result.copy();
      CompoundTag compoundtag = p_267036_.getItem(1).getTag();
      if (compoundtag != null) {
         itemstack.setTag(compoundtag.copy());
      }

      return itemstack;
   }

   public ItemStack getResultItem(RegistryAccess p_267209_) {
      return this.result;
   }

   public boolean isTemplateIngredient(ItemStack p_267113_) {
      return this.template.test(p_267113_);
   }

   public boolean isBaseIngredient(ItemStack p_267276_) {
      return this.base.test(p_267276_);
   }

   public boolean isAdditionIngredient(ItemStack p_267260_) {
      return this.addition.test(p_267260_);
   }

   public RecipeSerializer<?> getSerializer() {
      return TMWRecipeTypeRegistration.BULLET_FOUNDARY.get();
   }

   public boolean isIncomplete() {
      return Stream.of(this.template, this.base, this.addition).anyMatch(net.minecraftforge.common.ForgeHooks::hasNoElements);
   }
   
   @Override
   public RecipeType<BulletRecipe> getType() {
       // Return an instance of your recipe type
       return TMWRecipeTypeRegistration.FOUNDARY_TYPE.get();
   }
   
   public Ingredient returnTemplate()
   {
	   return this.template;
   }
   
   public Ingredient returnBase()
   {
	   return this.base;
   }
   
   public Ingredient returnAddon()
   {
	   return this.addition;
   }

   public static class Serializer implements RecipeSerializer<BulletRecipe> {

      public void toNetwork(FriendlyByteBuf p_266746_, BulletRecipe p_266927_) {
         p_266927_.template.toNetwork(p_266746_);
         p_266927_.base.toNetwork(p_266746_);
         p_266927_.addition.toNetwork(p_266746_);
         p_266746_.writeItem(p_266927_.result);
      }

	@Override
	public Codec<BulletRecipe> codec() {
		return null;
	}

	@Override
	public @Nullable BulletRecipe fromNetwork(FriendlyByteBuf p_267316_) {
		Ingredient ingredient = Ingredient.fromNetwork(p_267316_);
        Ingredient ingredient1 = Ingredient.fromNetwork(p_267316_);
        Ingredient ingredient2 = Ingredient.fromNetwork(p_267316_);
        ItemStack itemstack = p_267316_.readItem();
        return new BulletRecipe(ingredient, ingredient1, ingredient2, itemstack);
	}
   }
}
