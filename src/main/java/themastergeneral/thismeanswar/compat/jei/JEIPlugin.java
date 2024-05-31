package themastergeneral.thismeanswar.compat.jei;

import javax.annotation.Nonnull;

import com.themastergeneral.ctdcore.helpers.ModUtils;

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
import themastergeneral.thismeanswar.items.TMWItems;
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
	    registration.addRecipeCategories(new ReprocessorCategory(guiHelper));
	    registration.addRecipeCategories(new RecyclerCategory(guiHelper));
	    registration.addRecipeCategories(new CrusherCategory(guiHelper));
	    registration.addRecipeCategories(new PressCategory(guiHelper));
	    registration.addRecipeCategories(new PopperCategory(guiHelper));
	    registration.addRecipeCategories(new AlloySmeltCategory(guiHelper));
	}
	
	
	
	@Override
  public void registerRecipes(@Nonnull final IRecipeRegistration registration) 
	{
		TMWMain.LOGGER.info("Adding recipes to JEI!!");
		registration.addItemStackInfo(new ItemStack(TMWItems.ammo_box), ModUtils.displayTranslation("thismeanswar.jei.ammo_box_info"));
		registration.addItemStackInfo(new ItemStack(TMWItems.ammo_box_medium), ModUtils.displayTranslation("thismeanswar.jei.ammo_box_info"));
		registration.addItemStackInfo(new ItemStack(TMWItems.ammo_box_large), ModUtils.displayTranslation("thismeanswar.jei.ammo_box_info"));
		
		registration.addItemStackInfo(new ItemStack(TMWItems.medic_box), ModUtils.displayTranslation("thismeanswar.jei.medic_box_info"));
		registration.addItemStackInfo(new ItemStack(TMWItems.medic_box_medium), ModUtils.displayTranslation("thismeanswar.jei.medic_box_info"));
		registration.addItemStackInfo(new ItemStack(TMWItems.medic_box_large), ModUtils.displayTranslation("thismeanswar.jei.medic_box_info"));
		
		registration.addItemStackInfo(new ItemStack(TMWItems.base_upgrade), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info"));
		registration.addItemStackInfo(new ItemStack(TMWItems.bullet_upgrade_ap), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info"));
		registration.addItemStackInfo(new ItemStack(TMWItems.bullet_upgrade_ap), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info_ap"));
		registration.addItemStackInfo(new ItemStack(TMWItems.bullet_upgrade_fire), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info"));
		registration.addItemStackInfo(new ItemStack(TMWItems.bullet_upgrade_fire), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info_fire"));
		registration.addItemStackInfo(new ItemStack(TMWItems.bullet_upgrade_inert), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info"));
		registration.addItemStackInfo(new ItemStack(TMWItems.bullet_upgrade_inert), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info_inert"));
		registration.addItemStackInfo(new ItemStack(TMWItems.bullet_upgrade_normal), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info"));
		registration.addItemStackInfo(new ItemStack(TMWItems.bullet_upgrade_normal), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info_normal"));
		registration.addItemStackInfo(new ItemStack(TMWItems.bullet_upgrade_tracer), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info"));
		registration.addItemStackInfo(new ItemStack(TMWItems.bullet_upgrade_tracer), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info_tracer"));
		registration.addItemStackInfo(new ItemStack(TMWItems.gun_rof_upgrade), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info"));
		registration.addItemStackInfo(new ItemStack(TMWItems.gun_rof_upgrade), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info_rofup"));
		registration.addItemStackInfo(new ItemStack(TMWItems.gun_rof_downgrade), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info"));
		registration.addItemStackInfo(new ItemStack(TMWItems.gun_rof_downgrade), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info_rofdown"));
		registration.addItemStackInfo(new ItemStack(TMWItems.mag_capacity_upgrade), ModUtils.displayTranslation("thismeanswar.jei.upgrade_info"));
		registration.addItemStackInfo(new ItemStack(TMWItems.mag_capacity_upgrade), ModUtils.displayTranslation("thismeanswar.jei.upgrade_magcap"));
		
		registration.addRecipes(RecipeTypes.BULLET_FOUNDARY, Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(TMWRecipeTypeRegistration.FOUNDARY_TYPE.get()));
		registration.addRecipes(RecipeTypes.REPROCESSOR, Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(TMWRecipeTypeRegistration.REPROCESSOR_TYPE.get()));
		registration.addRecipes(RecipeTypes.RECYCLER, Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(TMWRecipeTypeRegistration.RECYCLER_TYPE.get()));
		registration.addRecipes(RecipeTypes.CRUSHER, Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(TMWRecipeTypeRegistration.CRUSHER_TYPE.get()));
		registration.addRecipes(RecipeTypes.PRESS, Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(TMWRecipeTypeRegistration.PRESS_TYPE.get()));
		registration.addRecipes(RecipeTypes.POPPER, Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(TMWRecipeTypeRegistration.POPPER_TYPE.get()));
		registration.addRecipes(RecipeTypes.ALLOY_SMELTER, Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(TMWRecipeTypeRegistration.ALLOY_SMELTER_TYPE.get()));
	}
	
	@Override
	public void registerRecipeCatalysts(@Nonnull final IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(TMWBlocks.bullet_foundary), RecipeTypes.BULLET_FOUNDARY);
		registration.addRecipeCatalyst(new ItemStack(TMWBlocks.tip_recycler), RecipeTypes.REPROCESSOR);
		registration.addRecipeCatalyst(new ItemStack(TMWBlocks.casing_recycler), RecipeTypes.RECYCLER);
		registration.addRecipeCatalyst(new ItemStack(TMWBlocks.crusher), RecipeTypes.CRUSHER);
		registration.addRecipeCatalyst(new ItemStack(TMWBlocks.press), RecipeTypes.PRESS);
		
		registration.addRecipeCatalyst(new ItemStack(TMWBlocks.primer_popper), RecipeTypes.POPPER);
		registration.addRecipeCatalyst(new ItemStack(TMWBlocks.factory_holder), RecipeTypes.POPPER);
		
		registration.addRecipeCatalyst(new ItemStack(TMWBlocks.alloy_smelter), RecipeTypes.ALLOY_SMELTER);
	}
}
