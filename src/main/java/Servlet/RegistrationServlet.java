package Servlet;

import Models.User;
import Repositories.AccountRepository;
import Repositories.AccountRepositoryJbdcImpl;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Users";

    private AccountRepository accountRepository;

    public static String getHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

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
        Cookie[] cookies = request.getCookies();
        UUID userUUID = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("id".equals(cookie.getName())) {
                    userUUID = UUID.fromString(cookie.getValue());
                    break;
                }
            }
        }
        if (userUUID != null) {
            try {
                if (accountRepository.findUUID(userUUID)) {
                    response.sendRedirect("/recipe");
                    return;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        request.getRequestDispatcher("/jsp/register.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountName = request.getParameter("firstname");
        String accountSurname = request.getParameter("surname");
        String accountUsername = request.getParameter("username");
        String accountPassword = request.getParameter("password");
        String hashedPassword = getHashedPassword(accountPassword);

        User user = User.builder()
                .firstName(accountName)
                .lastName(accountSurname)
                .username(accountUsername)
                .password(hashedPassword)
                .build();

        try {
            accountRepository.save(user);
            response.sendRedirect("/login");
        }catch (SQLException e){
            response.sendRedirect("/registration");
            throw new RuntimeException(e);
        }
    }
}
