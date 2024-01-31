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

import java.util.Arrays;

@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeManager recipeManager;

    @Test
    public void testGetRecipes_ShouldReturnRecipes() throws Exception {
        Recipe recipe1 = new Recipe("Recipe 1", true, 2, Arrays.asList("ingredient1", "ingredient2"), "Instructions");
        Recipe recipe2 = new Recipe("Recipe 2", false, 4, Arrays.asList("ingredient1", "ingredient2", "ingredient3"), "Instructions");

        Mockito.when(recipeManager.fetchRecipesByFilter(Mockito.any())).thenReturn(Arrays.asList(recipe1, recipe2));

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Recipe 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Recipe 2"));
    }
}
