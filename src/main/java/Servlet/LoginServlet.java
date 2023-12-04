package Servlet;

import Models.User;
import Repositories.AccountRepository;
import Repositories.AccountRepositoryJbdcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Users";

    private AccountRepository accountRepository;

    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            accountRepository = new AccountRepositoryJbdcImpl(connection);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountUsername = request.getParameter("username");
        String accountPassword = request.getParameter("password");

        User user = User.builder()
                .username(accountUsername)
                .password(accountPassword)
                .build();

        try {
            if(accountRepository.login(accountUsername, accountPassword, user)) {
                HttpSession httpSession = request.getSession(true);
                httpSession.setAttribute("authenticated",true);
                httpSession.setAttribute("user", user);
                response.sendRedirect("/profile");
                System.out.println("redirecting to profile");
            }else {
                response.sendRedirect("/login?error=1");
                System.out.println("error found");
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }
}
