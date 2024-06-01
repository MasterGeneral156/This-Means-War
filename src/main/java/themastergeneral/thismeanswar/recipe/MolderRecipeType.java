package themastergeneral.thismeanswar.recipe;

import net.minecraft.world.item.crafting.RecipeType;

public class MolderRecipeType implements RecipeType<MolderRecipe>
{
	@Override
    public String toString() 
	{
        // Return a unique identifier for your recipe type
        return "molder";
    }
}
