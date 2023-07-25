package themastergeneral.thismeanswar.compat.jei;

import javax.annotation.Nonnull;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.TMWBlocks;
import themastergeneral.thismeanswar.registry.TMWRecipeTypeRegistration;
@JeiPlugin
public class JEIPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation("jei", TMWMain.MODID);
	}

	@Override
	  public void registerCategories(@Nonnull final IRecipeCategoryRegistration registration) {
	    IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
	    
	    registration.addRecipeCategories(new BulletFoundaryCategory(guiHelper));
	    
	}
	
	
	
	@Override
  public void registerRecipes(@Nonnull final IRecipeRegistration registration) 
	{
		TMWMain.LOGGER.info("Adding recipes to JEI!!");
		registration.addRecipes(RecipeTypes.BULLET_FOUNDARY, Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(TMWRecipeTypeRegistration.FOUNDARY_TYPE.get()));
	}
	
	@Override
	public void registerRecipeCatalysts(@Nonnull final IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(TMWBlocks.bullet_foundary), RecipeTypes.BULLET_FOUNDARY);
	}
}
