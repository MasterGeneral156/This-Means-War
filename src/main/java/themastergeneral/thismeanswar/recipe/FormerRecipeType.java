package themastergeneral.thismeanswar.recipe;

import net.minecraft.world.item.crafting.RecipeType;

public class FormerRecipeType implements RecipeType<FormerRecipe>
{
	@Override
    public String toString() 
	{
        // Return a unique identifier for your recipe type
        return "former";
    }
}
