package nl.eonics.antony.dendulk.manager;

import nl.eonics.antony.dendulk.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class RecipeManager {
    private final List<Recipe> recipes = new ArrayList<>();

    public int getRecipesCount() {
        return recipes.size();
    }

    public boolean addRecipe(Recipe recipe) {
        // Check if a recipe with the same name already exists
        if (getRecipe(recipe.name()) != null) {
            // Recipe with the same name already exists
            return false;
        }

        // Add the new recipe
        recipes.add(recipe);
        return true;
    }

    public boolean updateRecipe(String recipeName, Recipe updatedRecipe) {
        Recipe existingRecipe = getRecipe(recipeName);
        if (existingRecipe != null) {
            int index = recipes.indexOf(existingRecipe);
            recipes.set(index, updatedRecipe);
            return true;
        }
        return false;
    }

    Recipe getRecipe(String recipeName) {
        return recipes.stream()
                .filter(recipe -> recipe.name().equals(recipeName))
                .findFirst()
                .orElse(null);
    }

    public boolean removeRecipe(String name) {
        Recipe recipe = getRecipe(name);
        if (recipe != null) {
            recipes.remove(recipe);
            return true;
        }
        return false;
    }

    public List<Recipe> fetchRecipesByFilter(Predicate<Recipe> filter) {
        return recipes.stream()
                .filter(filter)
                .toList();
    }
}
