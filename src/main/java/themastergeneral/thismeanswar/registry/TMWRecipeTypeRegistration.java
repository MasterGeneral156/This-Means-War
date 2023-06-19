package themastergeneral.thismeanswar.registry;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.recipe.BulletRecipe;
import themastergeneral.thismeanswar.recipe.BulletRecipeType;

public class TMWRecipeTypeRegistration 
{
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TMWMain.MODID);
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, TMWMain.MODID);
	
	//public static final RegistryObject<CrusherRecipe.Serializer> CRUSHER_SERIALIZER = RECIPE_SERIALIZER.register("crusher", CrusherRecipe.Serializer::new);
	public static final RegistryObject<RecipeSerializer<?>> SMITHING_TABLE = RECIPE_SERIALIZER.register("smithing_table", () -> new BulletRecipe.Serializer());
	public static final RegistryObject<RecipeType> SMITHING_TYPE = RECIPE_TYPES.register("smithing_table", () -> new BulletRecipeType());
	
}
