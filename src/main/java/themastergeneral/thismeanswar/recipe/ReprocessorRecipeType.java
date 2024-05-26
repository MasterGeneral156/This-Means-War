package themastergeneral.thismeanswar.recipe;

import net.minecraft.world.item.crafting.RecipeType;

public class ReprocessorRecipeType implements RecipeType<ReprocessorRecipe>
{
	@Override
    public String toString() 
	{
        // Return a unique identifier for your recipe type
        return "reprocessor";
    }
}
