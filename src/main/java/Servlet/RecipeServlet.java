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

    private RecipeService recipeService;
    private RecipeRepository recipeRepository;


    public void init(ServletConfig config) throws  ServletException {
        this.recipeService = (RecipeService) config.getServletContext().getAttribute("recipes");
        this.recipeRepository = (RecipeRepository) config.getServletContext().getAttribute("recipeService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Recipe> recipes = recipeService.getAllRecipes();
            request.getRequestDispatcher("/jsp/recipe.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching recipes");
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
