package nl.eonics.antony.dendulk.model;

import java.util.List;

public record Recipe(String name, boolean vegetarian, int servings, List<String> ingredients, String instructions) {

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Recipe recipe) {
            return name.equals(recipe.name) &&
                    vegetarian == recipe.vegetarian &&
                    servings == recipe.servings &&
                    ingredients.equals(recipe.ingredients) &&
                    instructions.equals(recipe.instructions);
        }
        return false;
    }

}
