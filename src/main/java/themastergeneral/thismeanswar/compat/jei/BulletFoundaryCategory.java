package themastergeneral.thismeanswar.compat.jei;

import javax.annotation.Nonnull;

import com.themastergeneral.ctdcore.helpers.ModUtils;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.block.TMWBlocks;
import themastergeneral.thismeanswar.recipe.BulletRecipe;

public class BulletFoundaryCategory implements IRecipeCategory<BulletRecipe> {

	@Nonnull private final IDrawable background;
	@Nonnull private final IDrawable icon;
	
	public BulletFoundaryCategory(@Nonnull final IGuiHelper guiHelper) {
	    background = guiHelper.createDrawable(new ResourceLocation("jei", "textures/jei/gui/gui_vanilla.png"), 0, 168, 108, 18);
	    icon = guiHelper.createDrawableItemStack(new ItemStack(TMWBlocks.bullet_foundary));
	  }
	@Override
	public RecipeType<BulletRecipe> getRecipeType() {
		return new RecipeType<>(new ResourceLocation(TMWMain.MODID, "bullet_foundary"), BulletRecipe.class);
	}

	@Override
	public Component getTitle() {
		return ModUtils.displayTranslation("thismeanswar.jei.bullet_foundary");
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, BulletRecipe recipe, IFocusGroup focuses) {

		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
				.addIngredients(recipe.returnTemplate());

		builder.addSlot(RecipeIngredientRole.INPUT, 19, 1)
			.addIngredients(recipe.returnBase());

		builder.addSlot(RecipeIngredientRole.INPUT, 37, 1)
			.addIngredients(recipe.returnAddon());

		builder.addSlot(RecipeIngredientRole.OUTPUT, 91, 1)
			.addItemStack(recipe.getResultItem(null));
	}

}
