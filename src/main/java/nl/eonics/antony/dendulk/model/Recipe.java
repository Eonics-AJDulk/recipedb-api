package nl.eonics.antony.dendulk.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "A recipe")
public record Recipe(
        @ApiModelProperty(value = "The name of the recipe", example = "Pasta") String name,
        @ApiModelProperty(value = "Whether the recipe is vegetarian", example = "true") Boolean vegetarian,
        @ApiModelProperty(value = "The number of servings", example = "4") int servings,
        @ApiModelProperty(value = "The ingredients", example = "[\"pasta\", \"tomato sauce\", \"cheese\"]") List<String> ingredients,
        @ApiModelProperty(value = "The instructions", example = "1. Cook pasta\n2. Add tomato sauce\n3. Add cheese") String instructions
) {

    public Recipe {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }

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

    public Boolean isVegetarian() {
        return vegetarian;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public int getServings() {
        return servings;
    }

    public String getInstructions() {
        return instructions;
    }
}
