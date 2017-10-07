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

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getRequestURI().contains("/quit")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        if (!uri.contains("/login") || !uri.contains("/index")) {
            HttpSession session = req.getSession();
            if (!(Boolean) session.getAttribute("logged")) {
                if (session.getAttribute("user") != null) {
                    if (!uri.contains("/main") || uri.contains("/index")) {
                        req.getRequestDispatcher(String.format("%s/main.html", req.getContextPath())).forward(req, resp);
                        return;
                    }
                }
            } else {
                req.getRequestDispatcher(String.format("%s/index.html", req.getContextPath())).forward(req, resp);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
