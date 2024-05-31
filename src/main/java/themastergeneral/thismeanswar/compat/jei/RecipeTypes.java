package themastergeneral.thismeanswar.compat.jei;

import mezz.jei.api.recipe.RecipeType;
import themastergeneral.thismeanswar.TMWMain;
import themastergeneral.thismeanswar.recipe.AlloySmelterRecipe;
import themastergeneral.thismeanswar.recipe.BulletRecipe;
import themastergeneral.thismeanswar.recipe.CrusherRecipe;
import themastergeneral.thismeanswar.recipe.FormerRecipe;
import themastergeneral.thismeanswar.recipe.PopperRecipe;
import themastergeneral.thismeanswar.recipe.PressRecipe;
import themastergeneral.thismeanswar.recipe.RecyclerRecipe;
import themastergeneral.thismeanswar.recipe.ReprocessorRecipe;

public class RecipeTypes {

	public static final RecipeType<BulletRecipe> BULLET_FOUNDARY =
		      RecipeType.create(
		          TMWMain.MODID, "bullet_foundary", BulletRecipe.class);
	
	public static final RecipeType<ReprocessorRecipe> REPROCESSOR =
		      RecipeType.create(
		          TMWMain.MODID, "reprocessor", ReprocessorRecipe.class);
	
	public static final RecipeType<RecyclerRecipe> RECYCLER =
		      RecipeType.create(
		          TMWMain.MODID, "recycler", RecyclerRecipe.class);
	
	public static final RecipeType<CrusherRecipe> CRUSHER =
		      RecipeType.create(
		          TMWMain.MODID, "crusher", CrusherRecipe.class);
	
	public static final RecipeType<PressRecipe> PRESS =
		      RecipeType.create(
		          TMWMain.MODID, "press", PressRecipe.class);
	
	public static final RecipeType<PopperRecipe> POPPER =
		      RecipeType.create(
		          TMWMain.MODID, "popper", PopperRecipe.class);
	
	public static final RecipeType<AlloySmelterRecipe> ALLOY_SMELTER =
		      RecipeType.create(
		          TMWMain.MODID, "alloy_smelter", AlloySmelterRecipe.class);
	
	public static final RecipeType<FormerRecipe> FORMER =
		      RecipeType.create(
		          TMWMain.MODID, "former", FormerRecipe.class);
}
