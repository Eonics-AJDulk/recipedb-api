package nl.eonics.antony.dendulk.controller;

import nl.eonics.antony.dendulk.manager.RecipeManager;
import nl.eonics.antony.dendulk.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeManager recipeManager;

    @Autowired
    public RecipeController(RecipeManager recipeManager) {
        this.recipeManager = recipeManager;
    }

    @GetMapping
    public List<Recipe> getRecipes() {
        // You can define a filter or use a default one
        // For example, let's return all recipes for now
        return recipeManager.fetchRecipesByFilter(recipe -> true);
    }
}