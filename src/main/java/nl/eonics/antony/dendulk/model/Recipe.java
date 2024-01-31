package nl.eonics.antony.dendulk.model;

import java.util.List;

public class Recipe {
    private String name;
    private boolean vegetarian;
    private int servings;
    private List<String> ingredients;
    private String instructions;

    public Recipe(String name, boolean vegetarian, int servings, List<String> ingredients, String instructions) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.servings = servings;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getServings() {
        return servings;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }
}
