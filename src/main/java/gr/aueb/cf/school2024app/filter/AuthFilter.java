package gr.aueb.cf.school2024app.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/*Flow: User goes to register page, does successful registration, so record to the DB
 * After registration goes to login page, and Login Controller raise a session with this username in Session Object
 * and redirect it to the teachers page
 * Then client will send another request to go to another page
 * So after login, every new request should be checked if there is a session active with this login
 Every new request should be checked if there is state for that,  login has been done by filters (AuthFilter)/
 *
 * Client will receive a cookie (setCookie) and send another request (returning a JSESSIONID cookie too)
 * Every next request includes this JSESSIONID cookie, SO AuthFilter checks FOR every new request if this cookie exists
 *
 * AuthFilter checks if there is or not state for every request after successful login
 * There is ordering, chain
 * The order is configured into web.xml file
 *
 * //Middleware (node.js & csharp)
 */
public class AuthFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean authenticated = false;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    HttpSession session = request.getSession(false);

                    authenticated = (session != null) && (session.getAttribute("username") != null);
                }
            }
        }

        //if authentication fails back to login page
        if (!authenticated) {
            response.sendRedirect(request.getContextPath() + "/login");

            //else go to next filter checking
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
