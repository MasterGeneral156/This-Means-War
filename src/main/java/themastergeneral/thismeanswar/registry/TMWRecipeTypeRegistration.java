package themastergeneral.thismeanswar.registry;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.thismeanswar.TMWMain;

public class TMWRecipeTypeRegistration 
{
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TMWMain.MODID);
	
	//public static final RegistryObject<CrusherRecipe.Serializer> CRUSHER_SERIALIZER = RECIPE_SERIALIZER.register("crusher", CrusherRecipe.Serializer::new);

	//public static final RecipeType<CrusherRecipe> CRUSHING_RECIPE = new CrusherRecipe.CrusherRecipeType();
}
