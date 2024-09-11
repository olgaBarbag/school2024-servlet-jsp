package gr.aueb.cf.school2024app.authentication;

import gr.aueb.cf.school2024app.dao.IUserDAO;
import gr.aueb.cf.school2024app.dao.UserDAOImpl;
import gr.aueb.cf.school2024app.dao.exceptions.UserDAOException;
import gr.aueb.cf.school2024app.dto.user.UserLoginDTO;
import gr.aueb.cf.school2024app.service.IUserService;
import gr.aueb.cf.school2024app.service.UserServiceImpl;

public class AuthenticationProvider {

    //Wiring - Dependency Injection
    private final static IUserDAO userDAO = new UserDAOImpl();
    private final static IUserService userService = new UserServiceImpl(userDAO);

    private AuthenticationProvider() {}

    public static boolean authenticateA(UserLoginDTO loginDTO) throws UserDAOException {

        return userService.isUserValidA(loginDTO.getUsername(), loginDTO.getPassword());
    }

    public static boolean authenticateB(UserLoginDTO loginDTO) throws UserDAOException {

        return userService.isUserValidB(loginDTO.getUsername(), loginDTO.getPassword());
    }


}
