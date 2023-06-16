package themastergeneral.thismeanswar.recipe;

import net.minecraft.world.item.crafting.RecipeType;

public class BulletRecipeType implements RecipeType<BulletRecipe>
{
	@Override
    public String toString() 
	{
        // Return a unique identifier for your recipe type
        return "smithing_table";
    }
}
