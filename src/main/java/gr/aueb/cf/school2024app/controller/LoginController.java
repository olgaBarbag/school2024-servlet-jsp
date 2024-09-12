package gr.aueb.cf.school2024app.controller;

import gr.aueb.cf.school2024app.authentication.AuthenticationProvider;
import gr.aueb.cf.school2024app.dao.exceptions.UserDAOException;
import gr.aueb.cf.school2024app.dto.user.UserLoginDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serial;

/*Flow: User goes to register page, does successful registration, so record to the DB
 * After registration goes to login page, and
 * Login Controller raise a session, writing in Session Object a username
 * and redirect it to the teachers page
 * Then client will receive a cookie(setCookie) and send another request  to go to another page
 * So after login, every new request should be checked if there is a session active with this login
 Every new request should be checked if there is state for that,  login has been done by filters (AuthFilter)/
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /*doGET*/
    //1. Receives a request to login and send a response, go to the page - dispatch
    //2. error management. If user insert wrong credentials return to login page so we have another ONE GET
    //   after login error
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*Query String*/
        // "/login?isError=true"
        //So after unsuccessful POST, providing wrong credentials that did NOT VALIDATE
        //It will turn back in login page with this Query String: isError=true
        //And then GET will take with GET Parameter the value of isError, which is true
        //then pass this in jsp

        //Takes some data of isError condition
        //Data Binding
        String isError = request.getParameter("isError");

        //if "/login" then isError == null
        //if "/login?isError=true" then isError == isError
        //request.setAttribute("isError", isError == null ? "false" : "true");
        request.setAttribute("isError", isError == null ? "false" : isError);


        /*Basic role of doGet --> go to the page*/
        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //doPost is the action that activated after user's credentials submission
        //Takes some data
        //.getParameter("username") == name="username" from the input of the form in login.jsp file
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //Principle is the login user
        boolean principleIsAuthenticated = false;
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);

        try {
            principleIsAuthenticated = AuthenticationProvider.authenticateB(userLoginDTO);

            if (principleIsAuthenticated) {
                HttpSession session = request.getSession(false);
                session.setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/teachers");
            } else {
                response.sendRedirect(request.getContextPath() + "/login?isError=true");
            }
        } catch (UserDAOException e) {
            response.sendRedirect(request.getContextPath() + "/login?isError=true");
        }

    }
}
