package Repositories;

import Models.Recipe;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> getAllRecipes();
    Recipe getRecipeById(int id);
    void addRecipe(Recipe recipe);
    void deleteRecipe(Recipe recipe);
}
