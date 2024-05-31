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
import themastergeneral.thismeanswar.recipe.AlloySmelterRecipe;

public class AlloySmeltCategory implements IRecipeCategory<AlloySmelterRecipe> {

	@Nonnull private final IDrawable background;
	@Nonnull private final IDrawable icon;
	
	public AlloySmeltCategory(@Nonnull final IGuiHelper guiHelper) {
		background = guiHelper.createDrawable(new ResourceLocation("jei", "textures/jei/gui/gui_vanilla.png"), 0, 114, 82, 54);
	    icon = guiHelper.createDrawableItemStack(new ItemStack(TMWBlocks.alloy_smelter));
	  }
	@Override
	public RecipeType<AlloySmelterRecipe> getRecipeType() {
		return new RecipeType<>(new ResourceLocation(TMWMain.MODID, "alloy_smelter"), AlloySmelterRecipe.class);
	}

	@Override
	public Component getTitle() {
		return ModUtils.displayTranslation("thismeanswar.jei.alloy_smelter");
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
	public void setRecipe(IRecipeLayoutBuilder builder, AlloySmelterRecipe recipe, IFocusGroup focuses) {

		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
			.addIngredients(recipe.returnBase());
		
		builder.addSlot(RecipeIngredientRole.INPUT, 17, 1)
		.addIngredients(recipe.returnExtra());

		builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 19)
			.addItemStack(new ItemStack(recipe.getResultItem(null).getItem(), recipe.getResultItem(null).getCount()));
	}

}
