package nl.eonics.antony.dendulk.controller;

import nl.eonics.antony.dendulk.manager.RecipeManager;
import nl.eonics.antony.dendulk.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Predicate;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeManager recipeManager;

    @Autowired
    public RecipeController(RecipeManager recipeManager) {
        this.recipeManager = recipeManager;
    }

    @GetMapping
    public List<Recipe> getRecipes(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "vegetarian", required = false) Boolean vegetarian,
            @RequestParam(name = "servings", required = false) Integer servings,
            @RequestParam(name = "ingredient", required = false) List<String> ingredients,
            @RequestParam(name = "instructions", required = false) String instructions
    ) {
        Predicate<Recipe> filter = recipe ->
                (name == null || recipe.getName().contains(name)) &&
                        (vegetarian == null || recipe.isVegetarian() == vegetarian) &&
                        (servings == null || recipe.getServings() == servings) &&
                        (ingredients == null || ingredients.isEmpty() || ingredients.stream().anyMatch(recipe.getIngredients()::contains)) &&
                        (instructions == null || recipe.getInstructions().contains(instructions));

        return recipeManager.fetchRecipesByFilter(filter);
    }

    @GetMapping("/count")
    public int getRecipesCount() {
        return recipeManager.getRecipesCount();
    }

    @GetMapping("/add")
    public boolean addRecipe(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "vegetarian") boolean vegetarian,
            @RequestParam(name = "servings") int servings,
            @RequestParam(name = "ingredient") List<String> ingredients,
            @RequestParam(name = "instructions") String instructions
    ) {
        Recipe recipe = new Recipe(name, vegetarian, servings, ingredients, instructions);
        return recipeManager.addRecipe(recipe);
    }

    @GetMapping("/update")
    public boolean updateRecipe(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "vegetarian") boolean vegetarian,
            @RequestParam(name = "servings") int servings,
            @RequestParam(name = "ingredient") List<String> ingredients,
            @RequestParam(name = "instructions") String instructions
    ) {
        Recipe recipe = new Recipe(name, vegetarian, servings, ingredients, instructions);
        return recipeManager.updateRecipe(name, recipe);
    }

    @GetMapping("/remove")
    public boolean removeRecipe(
            @RequestParam(name = "name") String name
    ) {
        return recipeManager.removeRecipe(name);
    }
}
