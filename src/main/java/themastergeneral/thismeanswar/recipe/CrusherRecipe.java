package themastergeneral.thismeanswar.recipe;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.common.util.JsonUtils;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import themastergeneral.thismeanswar.items.TMWItems;
import themastergeneral.thismeanswar.registry.TMWRecipeTypeRegistration;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CrusherRecipe implements Recipe<Container> {
    private final ResourceLocation id;
    private final String group;
    private final Ingredient ingredient;
    private final ItemStack result;
    private final int crushTime;

    public CrusherRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, int crushTime) {
        this.id = id;
        this.group = group;
        this.ingredient = ingredient;
        this.result = result;
        this.crushTime = crushTime;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(TMWItems.hammer_creative);
    }

    public int getCrushTime() {
        return crushTime;
    }


    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(this.ingredient);
        return ingredients;
    }

    @Override
    public boolean matches(Container inv, Level level) {
        return this.ingredient.test(inv.getItem(0));
    }

    @Override
    public ItemStack assemble(Container inv) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.result;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return TMWRecipeTypeRegistration.CRUSHER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return TMWRecipeTypeRegistration.CRUSHING_RECIPE;
    }

    public ItemStack getSecondRecipeOutput() {
        return ItemStack.EMPTY;
    }

    public static class CrusherRecipeType implements RecipeType<CrusherRecipe> {
        @Override
        public String toString() {
            return "thismeanswar:crusher";
        }
    }
    
    public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<CrusherRecipe> {
        @Override
        public CrusherRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            String s = GsonHelper.getAsString(json, "group", "");
            JsonElement jsonelement = (GsonHelper.isArrayNode(json, "ingredient") ? GsonHelper.getAsJsonArray(json, "ingredient") : GsonHelper.getAsJsonObject(json, "ingredient"));
            Ingredient ingredient = Ingredient.fromJson(jsonelement);

            if (!json.has("result")) {
                throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
            }

            ItemStack itemstack;

            if (json.get("result").isJsonObject())
                itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            else {
                String s1 = GsonHelper.getAsString(json, "result");
                ResourceLocation location = new ResourceLocation(s1);
                Item item = ForgeRegistries.ITEMS.getValue(location);

                if (item != null) {
                    itemstack = new ItemStack(item);
                } else {
                    throw new IllegalStateException("Item: " + s1 + " does not exist");
                }
            }

            int i = GsonHelper.getAsInt(json, "processingtime", 200);

            return new CrusherRecipe(recipeId, s, ingredient, itemstack, i);
        }

        @Nullable
        @Override
        public CrusherRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String s = buffer.readUtf(32767);
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack itemstack = buffer.readItem();
            int i = buffer.readVarInt();

            return new CrusherRecipe(recipeId, s, ingredient, itemstack, i);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CrusherRecipe recipe) {
            buffer.writeUtf(recipe.group);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);
            buffer.writeVarInt(recipe.crushTime);
        }
    }
}
