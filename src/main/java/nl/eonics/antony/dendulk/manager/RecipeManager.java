package nl.eonics.antony.dendulk.manager;

import nl.eonics.antony.dendulk.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeManager {
    private final List<Recipe> recipes = new ArrayList<>();

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public int getRecipesCount() {
        return recipes.size();
    }
}
