package gr.aueb.cf.school2024app.service.exceptions;

import gr.aueb.cf.school2024app.model.User;

import java.io.Serial;

public class UserNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(User user) {

        super("User " + user.getUsername() + "not found");
    }

    public UserNotFoundException(String message) {

        super(message);
    }
}
