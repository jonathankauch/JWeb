package com.jweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RestrictionFilter implements Filter {
    public static final String ACCES_CONNEXION  = "/admin/connexion";
    public static final String SESSION_USER = "sessionAdmin";

    public void init( FilterConfig config ) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request_tmp = (HttpServletRequest) request;
        HttpServletResponse response_tmp = (HttpServletResponse) response;

        String path = request_tmp.getRequestURI().substring(request_tmp.getContextPath().length());
        if (path.startsWith( "/css" ) ) {
            chain.doFilter(request_tmp, response_tmp);
            return;
        }

        HttpSession session = request_tmp.getSession();

        if (session.getAttribute(SESSION_USER) == null ) {
        	request_tmp.getRequestDispatcher(ACCES_CONNEXION).forward(request_tmp, response_tmp);
        } else {
            chain.doFilter(request_tmp, response_tmp);
        }
    }

    public void destroy() {
    }
}