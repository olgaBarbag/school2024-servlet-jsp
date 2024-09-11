package gr.aueb.cf.school2024app.service;

import gr.aueb.cf.school2024app.dao.exceptions.UserDAOException;
import gr.aueb.cf.school2024app.dto.user.InsertUserDTO;
import gr.aueb.cf.school2024app.dto.user.UserReadOnlyDTO;
import gr.aueb.cf.school2024app.model.User;
import gr.aueb.cf.school2024app.service.exceptions.UserNotFoundException;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public interface IUserService {

    User insertUser(InsertUserDTO dto) throws UserDAOException;

    User updateUser(InsertUserDTO dto) throws UserDAOException, UserNotFoundException;

    List<User> getAll(UserReadOnlyDTO dto) throws UserDAOException;

    List<User> getAll() throws UserDAOException;

    User getUserByUsername(String username) throws UserDAOException, UserNotFoundException;

    boolean isUserValidA(String username, String password) throws UserDAOException;

    boolean isUserValidB(String username, String password) throws UserDAOException;

    boolean isUsernameExist(String username) throws UserDAOException;

    boolean isEmailExist(String email) throws UserDAOException;
}
