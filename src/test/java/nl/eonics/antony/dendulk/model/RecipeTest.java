package nl.eonics.antony.dendulk.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RecipeTest {
    boolean notVegetarian = false;
    String notVegetarianName = "Spaghetti Bolognese";
    List<String> notVegetarianIngredients = Arrays.asList("pasta", "tomato sauce", "ground beef");
    boolean vegetarian = true;
    String vegetarianName = "Vegetarian Bolognese";
    int servings = 4;
    List<String> vegetarianIngredients = Arrays.asList("pasta", "tomato sauce", "lentils`");
    String instructions = "Cook pasta, prepare sauce, mix together.";

    Recipe recipeNotVegetarian() {
        return new Recipe(notVegetarianName, notVegetarian, servings, notVegetarianIngredients, instructions);
    }

    Recipe recipeVegetarian() {
        return new Recipe(vegetarianName, vegetarian, servings, vegetarianIngredients, instructions);
    }

    @Test
    void testRecipeCreation_notVegetarian() {
        Recipe recipeNotVegetarian = recipeNotVegetarian();

        assertNotNull(recipeNotVegetarian);
        assertEquals(notVegetarian, recipeNotVegetarian.vegetarian());
        assertEquals(notVegetarianName, recipeNotVegetarian.name());
        assertEquals(notVegetarianIngredients, recipeNotVegetarian.ingredients());
        assertEquals(servings, recipeNotVegetarian.servings());
        assertEquals(instructions, recipeNotVegetarian.instructions());
    }

    @Test
    void testRecipeCreation_isVegetarian() {
        Recipe recipeVegetarian = recipeVegetarian();

        assertNotNull(recipeVegetarian);
        assertEquals(vegetarian, recipeVegetarian.vegetarian());
        assertEquals(vegetarianName, recipeVegetarian.name());
        assertEquals(vegetarianIngredients, recipeVegetarian.ingredients());
        assertEquals(servings, recipeVegetarian.servings());
        assertEquals(instructions, recipeVegetarian.instructions());
    }

    @Test
    void testIsVegetarian() {
        Recipe recipeVegetarian = recipeVegetarian();
        Recipe recipeNotVegetarian = recipeNotVegetarian();

        assertEquals(vegetarian, recipeVegetarian.isVegetarian());
        assertEquals(notVegetarian, recipeNotVegetarian.isVegetarian());
    }

    @Test
    void testGetName() {
        Recipe recipeVegetarian = recipeVegetarian();
        Recipe recipeNotVegetarian = recipeNotVegetarian();

        assertEquals(vegetarianName, recipeVegetarian.getName());
        assertEquals(notVegetarianName, recipeNotVegetarian.getName());
    }

    @Test
    void testGetIngredients() {
        Recipe recipeVegetarian = recipeVegetarian();
        Recipe recipeNotVegetarian = recipeNotVegetarian();

        assertEquals(vegetarianIngredients, recipeVegetarian.getIngredients());
        assertEquals(notVegetarianIngredients, recipeNotVegetarian.getIngredients());
    }

    @Test
    void testGetServings() {
        Recipe recipeVegetarian = recipeVegetarian();
        Recipe recipeNotVegetarian = recipeNotVegetarian();

        assertEquals(servings, recipeVegetarian.getServings());
        assertEquals(servings, recipeNotVegetarian.getServings());
    }

    @Test
    void testGetInstructions() {
        Recipe recipeVegetarian = recipeVegetarian();
        Recipe recipeNotVegetarian = recipeNotVegetarian();

        assertEquals(instructions, recipeVegetarian.getInstructions());
        assertEquals(instructions, recipeNotVegetarian.getInstructions());
    }
}
