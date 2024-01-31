package nl.eonics.antony.dendulk.manager;

import nl.eonics.antony.dendulk.model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class RecipeManager {
    private final List<Recipe> recipes = new ArrayList<>();

    public int getRecipesCount() {
        return recipes.size();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void updateRecipe(String recipeName, Recipe updatedRecipe) {
        Recipe existingRecipe = getRecipe(recipeName);

        if (existingRecipe != null) {
            int index = recipes.indexOf(existingRecipe);
            recipes.set(index, updatedRecipe);
        }
    }

    Recipe getRecipe(String recipeName) {
        return recipes.stream()
                .filter(recipe -> recipe.name().equals(recipeName))
                .findFirst()
                .orElse(null);
    }

    public void removeRecipe(String name) {
        Recipe recipe = getRecipe(name);
        recipes.remove(recipe);
    }

    //    public List<Recipe> fetchRecipesByVegetarian(boolean vegetarian) {
//        return recipes.stream()
//                .filter(recipe -> recipe.vegetarian() == vegetarian)
//                .toList();
//    }
    public List<Recipe> fetchRecipesByFilter(Predicate<Recipe> filter) {
        return recipes.stream()
                .filter(filter)
                .toList();
    }
}
