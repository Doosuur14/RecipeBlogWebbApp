package Repositories;

import Models.Recipe;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeRepositoryImpl implements RecipeRepository {

    private Connection connection;


    public RecipeRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe(1, "Spaghetti Bolognese", "Classic Italian pasta dish with meat sauce.", "Chef Luigi"));
        recipes.add(new Recipe(2, "Chicken Alfredo", "Creamy Alfredo sauce with grilled chicken.", "Chef Maria"));
        recipes.add(new Recipe(3, "Vegetarian Pizza", "Delicious pizza with assorted vegetables.", "Chef Antonio"));
        recipes.add(new Recipe(4, "Chocolate Cake", "Moist and decadent chocolate cake.", "Chef Isabella"));
        recipes.add(new Recipe(5, "Grilled Salmon", "Healthy grilled salmon with lemon and herbs.", "Chef Giovanni"));

        return recipes;
    }

    @Override
    public Recipe getRecipeById(int id) {
        return null;
    }

    @Override
    public void addRecipe(Recipe recipe) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO recipes (name, description, created_by) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, recipe.getTitle());
            preparedStatement.setString(2, recipe.getContent());
            preparedStatement.setString(3, recipe.getAuthor());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM recipes WHERE id = ?")) {
            preparedStatement.setInt(1,recipe.getId() );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    }

