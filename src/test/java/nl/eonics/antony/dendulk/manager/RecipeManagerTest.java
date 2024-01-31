package nl.eonics.antony.dendulk.manager;

import nl.eonics.antony.dendulk.model.Recipe;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
}
