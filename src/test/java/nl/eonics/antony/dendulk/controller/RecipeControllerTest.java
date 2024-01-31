package nl.eonics.antony.dendulk.controller;

import nl.eonics.antony.dendulk.manager.RecipeManager;
import nl.eonics.antony.dendulk.model.Recipe;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeManager recipeManager;

    private Recipe recipe1() {
        return new Recipe("Recipe 1", true, 2, Arrays.asList("ingredient1", "ingredient2"), "Instructions");
    }

    private Recipe recipe2() {
        return new Recipe("Recipe 2", false, 4, Arrays.asList("ingredient1", "ingredient2", "ingredient3"), "Instructions");
    }

    @Test
    public void testGetRecipes_ShouldReturnAllRecipes() throws Exception {
        Recipe recipe1 = recipe1();
        Recipe recipe2 = recipe2();

        Mockito.when(recipeManager.fetchRecipesByFilter(Mockito.any())).thenReturn(Arrays.asList(recipe1, recipe2));

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Recipe 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Recipe 2"));
    }

    @Test
    public void testGetRecipes_ShouldReturnFilteredRecipes() throws Exception {
        Recipe recipe1 = recipe1();
        Recipe recipe2 = recipe2();

        List<Recipe> recipes = new ArrayList<>();
        Mockito.when(recipeManager.addRecipe(Mockito.any())).thenAnswer(invocation -> {
            Recipe recipe = invocation.getArgument(0);
            recipes.add(recipe);
            return true;
        });

        // Add the recipes to the RecipeManager
        recipeManager.addRecipe(recipe1);
        recipeManager.addRecipe(recipe2);

        Mockito.when(recipeManager.fetchRecipesByFilter(Mockito.any())).thenAnswer(invocation -> {
            Predicate<Recipe> filter = invocation.getArgument(0);
            return recipes.stream().filter(filter).collect(Collectors.toList());
        });

        // Now, when we call getRecipes with the name filter, it should return the correct recipe
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes?name=Recipe 1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Recipe 1"));
    }

    @Test
    public void testAddRecipe_ShouldReturnTrue() throws Exception {
        Recipe recipe = recipe1();

        Mockito.when(recipeManager.addRecipe(Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/add")
                        .param("name", recipe.name())
                        .param("vegetarian", String.valueOf(recipe.isVegetarian()))
                        .param("servings", String.valueOf(recipe.servings()))
                        .param("ingredient", recipe.ingredients().toArray(String[]::new))
                        .param("instructions", recipe.instructions()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

    @Test
    public void testAddRecipe_ShouldReturnFalse() throws Exception {
        Recipe recipe = recipe1();

        Mockito.when(recipeManager.addRecipe(Mockito.any())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/add")
                        .param("name", recipe.name())
                        .param("vegetarian", String.valueOf(recipe.isVegetarian()))
                        .param("servings", String.valueOf(recipe.servings()))
                        .param("ingredient", recipe.ingredients().toArray(String[]::new))
                        .param("instructions", recipe.instructions()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("false"));
    }

    @Test
    public void testUpdateRecipe_ShouldReturnTrue() throws Exception {
        Recipe recipe = recipe1();

        Mockito.when(recipeManager.addRecipe(Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/update")
                        .param("name", recipe.name())
                        .param("vegetarian", String.valueOf(recipe.isVegetarian()))
                        .param("servings", String.valueOf(recipe.servings()))
                        .param("ingredient", recipe.ingredients().toArray(String[]::new))
                        .param("instructions", recipe.instructions()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateRecipe_ShouldReturnFalse() throws Exception {
        Recipe recipe = recipe1();

        Mockito.when(recipeManager.addRecipe(Mockito.any())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/update")
                        .param("name", recipe.name())
                        .param("vegetarian", String.valueOf(recipe.isVegetarian()))
                        .param("servings", String.valueOf(recipe.servings()))
                        .param("ingredient", recipe.ingredients().toArray(String[]::new))
                        .param("instructions", recipe.instructions()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetRecipesCount() throws Exception {
        Mockito.when(recipeManager.getRecipesCount()).thenReturn(2);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/count"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("2"));
    }

    @Test
    public void testRemoveRecipe() throws Exception {
        Recipe recipe = recipe1();

        Mockito.when(recipeManager.addRecipe(Mockito.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/remove")
                        .param("name", recipe.name())
                        .param("vegetarian", String.valueOf(recipe.isVegetarian()))
                        .param("servings", String.valueOf(recipe.servings()))
                        .param("ingredient", recipe.ingredients().toArray(String[]::new))
                        .param("instructions", recipe.instructions()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
