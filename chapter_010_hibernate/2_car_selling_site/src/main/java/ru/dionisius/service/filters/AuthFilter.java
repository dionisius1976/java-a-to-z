package ru.dionisius.service.filters;

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
     * Pass logged client "user" to actions.
     * Forbids  client "guest" to to create, edit or update advertisements.
     * Obliges client "unregistered" to log in or to sign in.
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
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String client = (String) session.getAttribute("client");
        if (client == null) {
            client = "unregistered";
        }
        String uri = req.getRequestURI();
        if (!(uri.contains("/quit"))) {
            if (client.equals("user") || client.equals("guest")) {
                if (uri.contains("/sign_up.html") || uri.contains("/index") || (uri.length() == 1)) {
                    req.getRequestDispatcher(String.format("%s/quit", req.getContextPath())).forward(req, resp);
                }
            }
            if (client.equals("guest")) {
                if (uri.contains("/create.html")) {
                    req.getRequestDispatcher(String.format("%s/main.html", req.getContextPath())).forward(req, resp);
                }
            }
            if ( client.equals("unregistered")) {
                if (uri.contains("/main.html") || uri.contains("/create.html") || uri.contains("/show")) {
                    req.getRequestDispatcher(String.format("%s/", req.getContextPath())).forward(req, resp);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
