package ru.dionisius.servlets;

import ru.dionisius.models.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dionisius on 16.09.2017.
 */
public class AuthFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getRequestURI().contains("/quit")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        HttpSession session = req.getSession();
        if (session.getAttribute("role") != null) {
            String userRole = session.getAttribute("role").toString();
            if(userRole.equalsIgnoreCase((Role.USER).name()) && !(uri.contains("/user")
                    || uri.contains("/create"))) {
                resp.sendRedirect(String.format("%s/user.html", req.getContextPath()));
                return;
            }
            if (userRole.equalsIgnoreCase((Role.MANDATOR).name()) && !(uri.contains("/mandator")
                    || uri.contains("/create"))) {
                resp.sendRedirect(String.format("%s/mandator.html", req.getContextPath()));
                return;
            }
            if (userRole.equalsIgnoreCase((Role.ADMIN).name()) && !(uri.contains("/admin") ||
                    uri.contains("/create") || uri.contains("/update") || uri.contains("/delete"))) {
                resp.sendRedirect(String.format("%s/admin.html", req.getContextPath()));
                return;
            }
        }
        else {
                if (uri.contains("/admin") || uri.contains("/create") || uri.contains("/update") ||
                        uri.contains("/mandator") || uri.contains("/user")) {
                resp.sendRedirect(String.format("%s/index.html", req.getContextPath()));
                return;
            }

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
