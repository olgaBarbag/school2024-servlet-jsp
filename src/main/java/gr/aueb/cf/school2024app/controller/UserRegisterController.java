package gr.aueb.cf.school2024app.controller;

import gr.aueb.cf.school2024app.dao.IUserDAO;
import gr.aueb.cf.school2024app.dao.UserDAOImpl;
import gr.aueb.cf.school2024app.dao.exceptions.UserDAOException;
import gr.aueb.cf.school2024app.dto.user.InsertUserDTO;
import gr.aueb.cf.school2024app.dto.user.UserReadOnlyDTO;
import gr.aueb.cf.school2024app.model.User;
import gr.aueb.cf.school2024app.service.IUserService;
import gr.aueb.cf.school2024app.service.UserServiceImpl;
import gr.aueb.cf.school2024app.validator.UserValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/*Flow: User goes to register page, does successful registration, so record to the DB
 * After registration goes to login page, and Login Controller raise a session with this username in Session Object
 * and redirect it to the teachers page
 * Then client will send another request
 * So after login, every new request should be checked if there is a session active with this login
 * Every new request should be checked if there is state for that,  login has been done by filters (AuthFilter)

 */

@WebServlet("/users/register")
public class UserRegisterController extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //return the registration page back to client
        request.getRequestDispatcher("/WEB-INF/jsp/user-register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Data Binding
        InsertUserDTO dto;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        User user;

        //General error message for validation failure
        String errorMessage = "";

        //Specific error message for every failed input
        Map<String, String> errors;

        String usernameMessage;
        String passwordMessage;
        String confirmPasswordMessage;

        try {
            //DTO Populate
            dto = new InsertUserDTO(username, password, confirmPassword);
            errors = UserValidator.validate(dto);

            if (!errors.isEmpty()) {
                usernameMessage = errors.getOrDefault("username", "");
                passwordMessage = errors.getOrDefault("password", "");
                confirmPasswordMessage = errors.getOrDefault("confirmPassword", "");

                request.setAttribute("usernameMessage", usernameMessage);
                request.setAttribute("passwordMessage", passwordMessage);
                request.setAttribute("confirmPasswordMessage", confirmPasswordMessage);

                request.setAttribute("userRegisterDTO", dto);

                request.getRequestDispatcher("/WEB-INF/jsp/user-register.jsp").forward(request, response);
                return;
            }

            user = userService.insertUser(dto);
            UserReadOnlyDTO userReadOnlyDTO = mapUserToUserReadOnlyDTO(user);

            request.setAttribute("userInfo", userReadOnlyDTO);
            request.getRequestDispatcher("/WEB-INF/jsp/user-registered.jsp").forward(request, response);

        } catch (UserDAOException e) {
            errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/jsp/user-register.jsp").forward(request, response);
        }

    }

    private UserReadOnlyDTO mapUserToUserReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(), user.getUsername(), user.getPassword());
    }
}
