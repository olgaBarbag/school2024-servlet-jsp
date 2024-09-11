package gr.aueb.cf.school2024app.validator;

import gr.aueb.cf.school2024app.dao.IUserDAO;
import gr.aueb.cf.school2024app.dao.UserDAOImpl;
import gr.aueb.cf.school2024app.dao.exceptions.UserDAOException;
import gr.aueb.cf.school2024app.dto.user.BaseUserDTO;
import gr.aueb.cf.school2024app.dto.user.InsertUserDTO;
import gr.aueb.cf.school2024app.service.IUserService;
import gr.aueb.cf.school2024app.service.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class UserValidator<T> {
    //This validator can be used for every CRUD DTO method like insert/update/delete etc

    private final static IUserDAO userDAO = new UserDAOImpl();
    private static final IUserService userService = new UserServiceImpl(userDAO);

    private UserValidator() {

    }

    //Typical parameter a T extends in BaseUserDTO
    //Returns a Map<>
    public static <T extends BaseUserDTO> Map<String, String> validate(T dto)
            throws UserDAOException {

        Map<String, String> errors = new HashMap<>();

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            errors.put("confirmedPassword", "Confirmed password does not match");
        }

        if (dto.getPassword().length() < 5 || dto.getPassword().length() > 16) {
            errors.put("password", "Password must be between 5 to 16 characters");
        }

        if (dto.getPassword().matches("^.*\\s+.*$")) {
            errors.put("password", "Password must not contain whitespace");
        }

        if (dto.getUsername().matches("^.*\\s+.*$")) {
            errors.put("username", "Username must not contain whitespace");
        }

        if (userService.isUsernameExist(dto.getUsername())) {
            errors.put("username", "Username already exists");
        }

        return errors;
    }
}
