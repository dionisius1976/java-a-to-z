package ru.dionisius.service;

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

    /**
     * Pass logged user to all URIs.
     * Forbids 'guest' to go to create or update pages.
     * Obliges unlogged guest to log in.
     * @param servletRequest user's request.
     * @param servletResponse response to user.
     * @param filterChain chain of filters.
     * @throws IOException if occurs.
     * @throws ServletException if occurs.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();
        if (!(uri.contains("/quit") || uri.contains("/index") || (uri.length() == 1))) {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            HttpSession session = req.getSession();
            if (session.getAttribute("user") == null) {
                if (uri.contains("/create") || uri.contains("/setSold")) {
                    req.getRequestDispatcher(String.format("%s/main.html", req.getContextPath())).forward(req, resp);
                    return;
                }
                if (session.getAttribute("logged") == null) {
                    if (uri.contains("/main")) {
                        req.getRequestDispatcher(String.format("%s/", req.getContextPath())).forward(req, resp);
                    }
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
