package com.example.webapp;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter("/SimpleFilter")
public class SimpleFilter implements Filter {
    private FilterConfig filterConfig;
    private static ArrayList<String> pages;

    public SimpleFilter()
    {
        if (pages == null)
            pages = new ArrayList<String>();
    }

    @Override
    public void destroy()
    {
        filterConfig = null;
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException
    {
        filterConfig = fConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException
    {
        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
            HttpServletRequest req = (HttpServletRequest)request;
            String[] list = req.getRequestURI().split("/");
            String page = null;
            if (list[list.length - 1].indexOf(".jsp") > 0) {
                page = list[list.length - 1];
            }
            if ((page != null) && page.equalsIgnoreCase("index.jsp")) {
                if (pages.contains("customerinfo.jsp") || pages.contains("managerPahe.jsp")) {
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    ServletContext ctx = filterConfig.getServletContext();
                    RequestDispatcher dispatcher = ctx.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            } else if (page != null) {
                if (!pages.contains(page)) {
                    pages.add(page);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
