package gr.aueb.cf.school2024app.service;

import gr.aueb.cf.school2024app.dao.IUserDAO;
import gr.aueb.cf.school2024app.dao.exceptions.UserDAOException;
import gr.aueb.cf.school2024app.dto.user.InsertUserDTO;
import gr.aueb.cf.school2024app.dto.user.UserReadOnlyDTO;
import gr.aueb.cf.school2024app.dto.user.UserUpdateDTO;
import gr.aueb.cf.school2024app.model.User;
import gr.aueb.cf.school2024app.service.exceptions.UserNotFoundException;

import java.util.List;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }



    @Override
    public User insertUser(InsertUserDTO dto) throws UserDAOException {
        User user;

        try {
            user = mapDtoToUser(dto);
            return userDAO.insert(user);

        } catch (UserDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public User updateUser(InsertUserDTO dto) throws UserDAOException, UserNotFoundException {
        User user;

        try {
            user = mapDtoToUser(dto);
            
            return userDAO.update(user);

        } catch (UserDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public List<User> getAll(UserReadOnlyDTO dto) throws UserDAOException {
        return List.of();
    }

    @Override
    public List<User> getAll() throws UserDAOException {
        List<User> users;

        try {
            users = userDAO.findAll();
            return users;
        } catch (UserDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public User getUserByUsername(String username) throws UserDAOException, UserNotFoundException {
        User user;

        try {
            user = userDAO.findByUsername(username);
            if (user == null) {
                throw new UserNotFoundException(user);
            }
            return user;

        } catch (UserDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public boolean isUserValidA(String username, String password) throws UserDAOException {
        try {
           return userDAO.isUserValidA(username, password);

        } catch (UserDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public boolean isUserValidB(String username, String password) throws UserDAOException {
        try {
            return userDAO.isUserValidB(username, password);

        } catch (UserDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public boolean isUsernameExist(String username) throws UserDAOException {
        try {
            return userDAO.isUsernameExist(username);

        } catch (UserDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public boolean isEmailExist(String email) throws UserDAOException {
        try {
            return userDAO.isEmailExist(email);

        } catch (UserDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    private User mapDtoToUser(InsertUserDTO dto) {
        return new User(null, dto.getUsername(), dto.getPassword());
    }

    private User mapDtoToUser(UserUpdateDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword());
    }
}
