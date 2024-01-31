package nl.eonics.antony.dendulk.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RecipeTest {

    @Test
    void testRecipeCreation_notVegetarian() {
        // Arrange
        var name = "Spaghetti Bolognese";
        var vegetarian = false;
        var servings = 4;
        var ingredients = Arrays.asList("pasta", "tomato sauce", "ground beef");
        var instructions = "Cook pasta, prepare sauce, mix together.";

        // Act
        Recipe recipe = new Recipe(name, vegetarian, servings, ingredients, instructions);

        // Assert
        assertNotNull(recipe);
        assertEquals(name, recipe.name());
        assertEquals(vegetarian, recipe.vegetarian());
        assertEquals(servings, recipe.servings());
        assertEquals(ingredients, recipe.ingredients());
        assertEquals(instructions, recipe.instructions());
    }

    @Test
    void testRecipeCreation_isVegetarian() {
        var name = "Spaghetti Bolognese";
        var vegetarian = true;
        var servings = 4;
        var ingredients = Arrays.asList("pasta", "tomato sauce", "ground beef");
        var instructions = "Cook pasta, prepare sauce, mix together.";

        Recipe recipe = new Recipe(name, vegetarian, servings, ingredients, instructions);

        assertEquals(vegetarian, recipe.vegetarian());
    }
}
