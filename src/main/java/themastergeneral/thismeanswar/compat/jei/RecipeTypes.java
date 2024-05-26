package themastergeneral.thismeanswar.compat.jei;

import mezz.jei.api.recipe.RecipeType;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.recipe.BulletRecipe;
import themastergeneral.thismeanswar.recipe.ReprocessorRecipe;

public class RecipeTypes {

	public static final RecipeType<BulletRecipe> BULLET_FOUNDARY =
		      RecipeType.create(
		          TMWMain.MODID, "bullet_foundary", BulletRecipe.class);
	
	public static final RecipeType<ReprocessorRecipe> REPROCESSOR =
		      RecipeType.create(
		          TMWMain.MODID, "reprocessor", ReprocessorRecipe.class);
}
