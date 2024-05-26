package themastergeneral.thismeanswar.registry;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.recipe.BulletRecipe;
import themastergeneral.thismeanswar.recipe.BulletRecipeType;
import themastergeneral.thismeanswar.recipe.CrusherRecipe;
import themastergeneral.thismeanswar.recipe.CrusherRecipeType;
import themastergeneral.thismeanswar.recipe.RecyclerRecipe;
import themastergeneral.thismeanswar.recipe.RecyclerRecipeType;
import themastergeneral.thismeanswar.recipe.ReprocessorRecipe;
import themastergeneral.thismeanswar.recipe.ReprocessorRecipeType;

public class TMWRecipeTypeRegistration 
{
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TMWMain.MODID);
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, TMWMain.MODID);
	
	public static final RegistryObject<RecipeSerializer<?>> CRUSHER_SERIALIZER = RECIPE_SERIALIZER.register("crusher", () -> new CrusherRecipe.Serializer());
	public static final RegistryObject<RecipeSerializer<?>> BULLET_FOUNDARY = RECIPE_SERIALIZER.register("bullet_foundary", () -> new BulletRecipe.Serializer());
	public static final RegistryObject<RecipeSerializer<?>> REPROCESSOR = RECIPE_SERIALIZER.register("reprocessor", () -> new ReprocessorRecipe.Serializer());
	public static final RegistryObject<RecipeSerializer<?>> RECYCLER = RECIPE_SERIALIZER.register("recycler", () -> new RecyclerRecipe.Serializer());
	
	public static final RegistryObject<RecipeType> FOUNDARY_TYPE = RECIPE_TYPES.register("bullet_foundary", () -> new BulletRecipeType());
	public static final RegistryObject<RecipeType> CRUSHER_TYPE = RECIPE_TYPES.register("crusher", () -> new CrusherRecipeType());
	public static final RegistryObject<RecipeType> REPROCESSOR_TYPE = RECIPE_TYPES.register("reprocessor", () -> new ReprocessorRecipeType());
	public static final RegistryObject<RecipeType> RECYCLER_TYPE = RECIPE_TYPES.register("recycler", () -> new RecyclerRecipeType());
}
