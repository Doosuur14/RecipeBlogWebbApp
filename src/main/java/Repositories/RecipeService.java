package Repositories;

import Models.Recipe;

import java.sql.SQLException;
import java.util.List;

public class RecipeService {
    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAllRecipes() throws SQLException {
        return recipeRepository.getAllRecipes();
    }

    public Recipe getRecipeById(int id) throws SQLException {
        return recipeRepository.getRecipeById(id);
    }

    public void addRecipe(Recipe recipe) throws SQLException {
        recipeRepository.addRecipe(recipe);
    }

    public void deleteRecipe(Recipe recipe) throws SQLException {
        recipeRepository.deleteRecipe(recipe);
    }
}
