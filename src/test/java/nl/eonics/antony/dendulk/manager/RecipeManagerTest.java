package nl.eonics.antony.dendulk.manager;

import nl.eonics.antony.dendulk.model.Recipe;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class RecipeManagerTest {
    private static final Recipe RECIPE1 = new Recipe("Recipe 1", true, 2, List.of("ingredient1", "ingredient2"), "Instructions");
    private static final Recipe RECIPE2 = new Recipe("Recipe 2", false, 4, List.of("ingredient1", "ingredient2", "ingredient3"), "Instructions");
    private static final Recipe RECIPE3 = new Recipe("Recipe 3", true, 2, List.of("ingredient1", "ingredient2", "ingredient3"), "Instructions");

    @Test
    void testRecipeManagerCreation() {
        RecipeManager recipeManager = new RecipeManager();
        assertNotNull(recipeManager);
    }

    @Test
    void testAddRecipe_OneRecipeAdded() {
        // Arrange
        var recipeManager = new RecipeManager();
        var mockRecipe = Mockito.mock(Recipe.class);

        recipeManager.addRecipe(mockRecipe);
        assertEquals(1, recipeManager.getRecipesCount());
    }

    @Test
    void testUpdateRecipe_UpdatedRecipeRetrieved() {
        // Arrange
        var recipeManager = new RecipeManager();
        var originalRecipe = new Recipe("Original Recipe", true, 2, List.of("ingredient1", "ingredient2"), "Instructions");
        var updatedRecipe = new Recipe("Updated Recipe", false, 4, List.of("newIngredient1", "newIngredient2"), "New Instructions");

        recipeManager.addRecipe(originalRecipe);
        recipeManager.updateRecipe(originalRecipe.name(), updatedRecipe);

        var retrievedRecipe = recipeManager.getRecipe(updatedRecipe.name());
        assertEquals(updatedRecipe, retrievedRecipe);
    }

    @Test
    void testGetRecipe_ExistingRecipeRetrieved() {
        // Arrange
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);

        var retrievedRecipe = recipeManager.getRecipe(RECIPE1.name());
        assertEquals(RECIPE1, retrievedRecipe);
    }

    @Test
    void testGetRecipe_NotFound() {
        // Arrange
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);

        var retrievedRecipe = recipeManager.getRecipe("Not Found");
        assertNull(retrievedRecipe);
    }

    @Test
    void testGetRecipesCount_NoRecipes() {
        // Arrange
        var recipeManager = new RecipeManager();

        assertEquals(0, recipeManager.getRecipesCount());
    }

    @Test
    void testGetRecipesCount_OneRecipeAdded() {
        // Arrange
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);

        assertEquals(1, recipeManager.getRecipesCount());
    }

    @Test
    void testGetRecipesCount_TwoRecipesAdded() {
        // Arrange
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.addRecipe(RECIPE2);

        assertEquals(2, recipeManager.getRecipesCount());
    }

    @Test
    void testRemoveRecipe_AddOneThenRemove() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.removeRecipe(RECIPE1.name());

        assertEquals(0, recipeManager.getRecipesCount());
    }

    @Test
    void testRemoveRecipe_NotFound() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.removeRecipe("Not Found");

        assertEquals(1, recipeManager.getRecipesCount());
    }

    @Test
    void testRemoveRecipe_RemoveFirst() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.addRecipe(RECIPE2);
        recipeManager.removeRecipe(RECIPE1.name());

        assertEquals(1, recipeManager.getRecipesCount());
    }

    @Test
    void testRemoveRecipe_MultipleRecipesNotFound() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.addRecipe(RECIPE2);
        recipeManager.removeRecipe("Not Found");

        assertEquals(2, recipeManager.getRecipesCount());
    }

    @Test
    void testFetchRecipes_IsVegetarian() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.addRecipe(RECIPE2);
        recipeManager.addRecipe(RECIPE3);

        var vegetarianRecipes = recipeManager.fetchRecipesByFilter(Recipe::vegetarian);
        assertEquals(2, vegetarianRecipes.size());
    }

    @Test
    void testFetchRecipes_NotVegetarian() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.addRecipe(RECIPE2);
        recipeManager.addRecipe(RECIPE3);

        var vegetarianRecipes = recipeManager.fetchRecipesByFilter(recipe -> !recipe.vegetarian());
        assertEquals(1, vegetarianRecipes.size());
    }

    @Test
    void testFetchRecipes_MultipleServings() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.addRecipe(RECIPE2);
        recipeManager.addRecipe(RECIPE3);

        var recipes = recipeManager.fetchRecipesByFilter(recipe -> recipe.servings() > 2);
        assertEquals(1, recipes.size());
    }

    @Test
    void testFetchRecipes_MultipleIngredients() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.addRecipe(RECIPE2);
        recipeManager.addRecipe(RECIPE3);

        var recipes = recipeManager.fetchRecipesByFilter(recipe -> recipe.ingredients().size() > 2);
        assertEquals(2, recipes.size());
    }

    @Test
    void testFetchRecipes_NotVegetarian_MultipleServings_MultipleIngredients() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.addRecipe(RECIPE2);
        recipeManager.addRecipe(RECIPE3);

        var recipes = recipeManager.fetchRecipesByFilter(recipe -> !recipe.vegetarian() && recipe.servings() > 2 && recipe.ingredients().size() > 2);
        assertEquals(1, recipes.size());
    }

    @Test
    void testUpdate_SameData() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.updateRecipe(RECIPE1.name(), RECIPE1);

        var retrievedRecipe = recipeManager.getRecipe(RECIPE1.name());
        assertEquals(RECIPE1, retrievedRecipe);
    }

    @Test
    void testRemove_RecipesEmpty() {
        var recipeManager = new RecipeManager();

        recipeManager.removeRecipe(RECIPE1.name());

        assertEquals(0, recipeManager.getRecipesCount());
    }

    @Test
    void testFetch_RecipesEmpty() {
        var recipeManager = new RecipeManager();

        var recipes = recipeManager.fetchRecipesByFilter(Recipe::vegetarian);
        assertEquals(0, recipes.size());
    }

    @Test
    void testFetch_NoMatchingRecipes_MultipleCriteria() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.addRecipe(RECIPE2);
        recipeManager.addRecipe(RECIPE3);

        var recipes = recipeManager.fetchRecipesByFilter(recipe -> !recipe.vegetarian() && recipe.servings() > 8 && recipe.ingredients().size() > 2);
        assertEquals(0, recipes.size());
    }

    @Test
    void testFetch_NotVegetarian_HasSpecificIngredient() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.addRecipe(RECIPE2);
        recipeManager.addRecipe(RECIPE3);

        var recipes = recipeManager.fetchRecipesByFilter(recipe -> !recipe.vegetarian() && recipe.ingredients().contains("ingredient3"));
        assertEquals(1, recipes.size());
    }

    @Test
    void testFetch_IsVegetarian_HasSpecificIngredient() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.addRecipe(RECIPE2);
        recipeManager.addRecipe(RECIPE3);

        var recipes = recipeManager.fetchRecipesByFilter(recipe -> recipe.vegetarian() && recipe.ingredients().contains("ingredient2"));
        assertEquals(2, recipes.size());
    }

    @Test
    void testFetch_NotVegetarian_HasSpecificIngredients() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.addRecipe(RECIPE2);

        var recipes = recipeManager.fetchRecipesByFilter(recipe -> !recipe.vegetarian() && recipe.ingredients().containsAll(List.of("ingredient1", "ingredient2")));
        assertEquals(1, recipes.size());
    }

    @Test
    void testFetch_IsVegetarian_HasSpecificIngredients() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.addRecipe(RECIPE3);

        var recipes = recipeManager.fetchRecipesByFilter(recipe -> recipe.vegetarian() && recipe.ingredients().containsAll(List.of("ingredient1", "ingredient3")));
        assertEquals(1, recipes.size());
    }

    @Test
    void testFetch_IsVegetarian_DoesNotHaveSpecificIngredient() {
        var recipeManager = new RecipeManager();

        recipeManager.addRecipe(RECIPE1);
        recipeManager.addRecipe(RECIPE2);
        recipeManager.addRecipe(RECIPE3);

        var recipes = recipeManager.fetchRecipesByFilter(recipe -> recipe.vegetarian() && recipe.ingredients().contains("ingredient4"));
        assertEquals(0, recipes.size());
    }
}
