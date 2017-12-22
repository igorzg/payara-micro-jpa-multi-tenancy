package icoder.filters;


import icoder.helpers.Strings;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "010CorsFilter", urlPatterns = "/*")
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;

        String requestOrigin = request1.getHeader("Origin");
        String allowedOrigin = Strings.isNullOrEmpty(requestOrigin) ? "*" : requestOrigin;

        response1.setHeader("Access-Control-Allow-Origin", allowedOrigin);
        response1.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD, PATCH");
        response1.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, X-CSRF-Token, Accept-Encoding, Authorization, X-Requested-With, Accept, Accept-Version, Content-Length, X-Api-Version, X-File-Name, Cookie");
        response1.setHeader("Access-Control-Allow-Credentials", "true");

        Boolean isOptionsMethod = "OPTIONS".equals(request1.getMethod());
        if (isOptionsMethod) {
            response1.resetBuffer();
        }

        chain.doFilter(request1, response1);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {
        // Do nothing
    }
}