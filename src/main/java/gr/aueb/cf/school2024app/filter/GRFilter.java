package gr.aueb.cf.school2024app.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class GRFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //In case that client sends data not in UTF-8
        request.setCharacterEncoding("UTF-8");
        filterChain.doFilter(request, servletResponse);
    }
}
