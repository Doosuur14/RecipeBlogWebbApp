package Listeners;

import Models.User;
import Repositories.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.UUID;

@WebListener
public class CustomServletContextListener implements ServletContextListener {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Users";
    private static final String DB_DRIVER = "org.postgresql.Driver";


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        ServletContext servletContext = servletContextEvent.getServletContext();

        FileRepository filesRepository = new FileRepositoryJbdcImpl(dataSource);
        try {
            AccountRepository accountRepository = new AccountRepositoryJbdcImpl(dataSource.getConnection());
            servletContext.setAttribute("accountRep", accountRepository);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        FileService fileService = new FileServiceImpl(filesRepository);
        servletContext.setAttribute("filesRepository", filesRepository);
        servletContext.setAttribute("filesUploadService", fileService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
