package gr.aueb.cf.school2024app.dao;

import gr.aueb.cf.school2024app.dao.exceptions.UserDAOException;
import gr.aueb.cf.school2024app.model.Teacher;
import gr.aueb.cf.school2024app.model.User;

import java.util.List;

public interface IUserDAO {
    User insert(User user) throws UserDAOException;

    User update(User user) throws UserDAOException;

    //void delete(String username) throws UserDAOException;

    List<User> findAll() throws UserDAOException;

    User findByUsername(String username) throws UserDAOException;

    boolean isUsernameExist(String username) throws UserDAOException;

    //We will use this method in registration of user
    boolean isEmailExist(String email) throws UserDAOException;

    boolean isUserValidA(String username, String password) throws UserDAOException;

    boolean isUserValidB(String username, String password) throws UserDAOException;
}
