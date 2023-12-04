package Filters;

import Models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/login")
public class AuthFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        Boolean isAuthenticated = false;
        Boolean sessionExists = session != null;
        Boolean isLoginPage = request.getRequestURI().equals("/login");
        Boolean excludeFromAuth = request.getRequestURI().startsWith("/css/") || request.getRequestURI().startsWith("/js/");
        User user = (User) session.getAttribute("user");


        if (!excludeFromAuth && sessionExists) {
            isAuthenticated = (Boolean) session.getAttribute("authenticated");
            if (isAuthenticated == null) {
                isAuthenticated = false;
            }
        }


        if (isAuthenticated || isLoginPage) {
            filterChain.doFilter(request, response);
            System.out.println("to profile");
        } else {
            response.sendRedirect("/login");
            System.out.println("to login");
        }

//        if (isAuthenticated && !isLoginPage) {
//            filterChain.doFilter(request, response);
//        } else if (isAuthenticated && isLoginPage) {
//            response.sendRedirect("/profile");
//        } else if (!isAuthenticated && !isLoginPage) {
//            response.sendRedirect("/login");
//        } else {
//            // If isAuthenticated is false and isLoginPage is true, do nothing to avoid a redirect loop
//            filterChain.doFilter(request, response);
//        }


    }

    @Override
    public void destroy() {

    }
}
