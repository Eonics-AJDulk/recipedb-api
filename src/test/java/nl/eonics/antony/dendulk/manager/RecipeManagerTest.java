package nl.eonics.antony.dendulk.manager;

import nl.eonics.antony.dendulk.model.Recipe;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class RecipeManagerTest {

    @Test
    void testRecipeManagerCreation() {
        RecipeManager recipeManager = new RecipeManager();
        assertNotNull(recipeManager);
    }

    @Test
    void testAddRecipe() {
        // Arrange
        var recipeManager = new RecipeManager();
        var mockRecipe = Mockito.mock(Recipe.class);

        recipeManager.addRecipe(mockRecipe);
        assertEquals(1, recipeManager.getRecipesCount());
    }

    @Test
    void testUpdateRecipe() {
        // Arrange
        var recipeManager = new RecipeManager();
        var originalRecipe = new Recipe("Original Recipe", true, 2, List.of("ingredient1", "ingredient2"), "Instructions");
        var updatedRecipe = new Recipe("Updated Recipe", false, 4, List.of("newIngredient1", "newIngredient2"), "New Instructions");

        recipeManager.addRecipe(originalRecipe);
        recipeManager.updateRecipe(originalRecipe.name(), updatedRecipe);

        var retrievedRecipe = recipeManager.getRecipe(updatedRecipe.name());
        assertEquals(updatedRecipe, retrievedRecipe);
    }
}
