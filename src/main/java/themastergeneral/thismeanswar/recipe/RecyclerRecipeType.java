package themastergeneral.thismeanswar.recipe;

import net.minecraft.world.item.crafting.RecipeType;

public class RecyclerRecipeType implements RecipeType<RecyclerRecipe>
{
	@Override
    public String toString() 
	{
        // Return a unique identifier for your recipe type
        return "recycler";
    }
}
