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

public class TMWRecipeTypeRegistration 
{
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TMWMain.MODID);
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, TMWMain.MODID);
	
	public static final RegistryObject<RecipeSerializer<?>> CRUSHER_SERIALIZER = RECIPE_SERIALIZER.register("crusher", () -> new CrusherRecipe.Serializer());
	public static final RegistryObject<RecipeSerializer<?>> BULLET_FOUNDARY = RECIPE_SERIALIZER.register("bullet_foundary", () -> new BulletRecipe.Serializer());
	public static final RegistryObject<RecipeType> FOUNDARY_TYPE = RECIPE_TYPES.register("bullet_foundary", () -> new BulletRecipeType());
	public static final RegistryObject<RecipeType> CRUSHER_TYPE = RECIPE_TYPES.register("crusher", () -> new CrusherRecipeType());
}
