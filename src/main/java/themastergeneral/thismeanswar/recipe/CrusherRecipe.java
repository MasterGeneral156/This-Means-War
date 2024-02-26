package themastergeneral.thismeanswar.recipe;

import org.jetbrains.annotations.Nullable;

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
	
	protected final ResourceLocation id;
   	protected final Ingredient ingredient;
   	protected final ItemStack result;
   	protected final int cookingTime;
   	
   	public CrusherRecipe(ResourceLocation ID, Ingredient input, ItemStack result, int time) {
        this.id = ID;
        this.ingredient = input;
        this.result = result;
        this.cookingTime = time;
     }
   
	@Override
	public boolean matches(Container container, Level lvl) {
		return this.ingredient.test(container.getItem(0));
	}

	@Override
	public ItemStack assemble(Container container, RegistryAccess r) {
		ItemStack itemstack = this.result.copy();
	      CompoundTag compoundtag = container.getItem(1).getTag();
	      if (compoundtag != null) {
	         itemstack.setTag(compoundtag.copy());
	      }

	      return itemstack;
	}

	@Override
	public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
		return true;
	}
	
	public Ingredient getIngredientItem()
	{
		return this.ingredient;
	}
	
	public int getCookTime()
	{
		return this.cookingTime;
	}

	@Override
	public ItemStack getResultItem(RegistryAccess p_267052_) {
		return this.result;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return TMWRecipeTypeRegistration.CRUSHER_SERIALIZER.get();
	}

	@Override
	public RecipeType<?> getType() {
		return TMWRecipeTypeRegistration.CRUSHER_TYPE.get();
	}
	
	public static class Serializer implements RecipeSerializer<CrusherRecipe>
	{

		@Override
		public CrusherRecipe fromJson(ResourceLocation resource, JsonObject json) {
			Ingredient ingredient = Ingredient.fromJson(GsonHelper.getNonNull(json, "ingredient"));
			int cookTime = GsonHelper.getAsInt(json, "ingredient");
			ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			return new CrusherRecipe(resource, ingredient, itemstack, cookTime);
		}

		@Override
		public @Nullable CrusherRecipe fromNetwork(ResourceLocation resource, FriendlyByteBuf fbyte) {
			Ingredient ingredient = Ingredient.fromNetwork(fbyte);
			int cookTime = fbyte.readVarInt();
			ItemStack itemstack = fbyte.readItem();
			return new CrusherRecipe(resource, ingredient, itemstack, cookTime);
		}

		@Override
		public void toNetwork(FriendlyByteBuf fbyte, CrusherRecipe recipe) {
			recipe.ingredient.toNetwork(fbyte);
			fbyte.writeVarInt(recipe.cookingTime);
			fbyte.writeItem(recipe.result);
		}
		
	}
	
}