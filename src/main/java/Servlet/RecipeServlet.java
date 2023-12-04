package Servlet;

import Models.Recipe;
import Repositories.RecipeRepository;
import Repositories.RecipeRepositoryImpl;
import Repositories.RecipeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/recipe")
public class RecipeServlet extends HttpServlet {
    private Connection connection;
    private RecipeService recipeService;
    private DataSource dataSource;

    private RecipeRepository recipeRepository;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            this.connection = dataSource.getConnection();
            RecipeRepository recipeRepository = new RecipeRepositoryImpl(connection);
            this.recipeService = new RecipeService(recipeRepository);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Recipe> recipes = recipeService.getAllRecipes();
            request.setAttribute("recipes", recipes);
            request.getRequestDispatcher("/jsp/recipe.jsp").forward(request, response);
        } catch (SQLException e) {
            // Handle the exception (log it, show an error page, etc.)
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching recipes");
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
