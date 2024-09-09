package gr.aueb.cf.school2024app.dao;

import gr.aueb.cf.school2024app.dao.exceptions.UserDAOException;
import gr.aueb.cf.school2024app.model.User;
import gr.aueb.cf.school2024app.security.SecurityUtil;
import gr.aueb.cf.school2024app.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {

    @Override
    public User insert(User user) throws UserDAOException {

        String sqlInsert = "INSERT INTO users (username, password) VALUES (?,?)";

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlInsert)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, SecurityUtil.encryptPassword(user.getPassword()));
            ps.executeUpdate();

            //logging

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new UserDAOException("Insert user failed");
        }
    }

    @Override
    public User update(User user) throws UserDAOException {
        String sqlUpdate = "UPDATE users SET password = ? WHERE id = ?";


        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlUpdate)) {

            ps.setString(1, SecurityUtil.encryptPassword(user.getPassword()));
            ps.setInt(2, user.getId());
            ps.executeUpdate();

            //logging

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new UserDAOException("Update the password of " + user.getUsername() + " failed");
        }

    }

    @Override
    public void delete(String username) throws UserDAOException {
        String sqlDelete = "DELETE FROM users WHERE username = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlDelete)) {

            ps.setString(1, username);
            ps.executeUpdate();

            //logging

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new UserDAOException("Delete the user with username: " + username + " failed");
        }

    }

    @Override
    public List<User> findAll() throws UserDAOException {
        String sqlFindAll = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlFindAll)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("username")));
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new UserDAOException("Finding all users failed");
        }
    }

    @Override
    public User findByUsername(String username) throws UserDAOException {
        String sqlFindByUsername = "SELECT * FROM users WHERE username = ?";
        User user = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlFindByUsername)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"));
            }
            return user;

            //logging

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new UserDAOException("Finding the user with username: " + username + " failed");
        }
    }

    @Override
    public boolean isUsernameExist(String username) throws UserDAOException {
        String sqlFindByUsername = "SELECT * FROM users WHERE username = ?";
        boolean isUsernameExist = false;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlFindByUsername)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isUsernameExist = true;
            }
            return isUsernameExist;
            //logging

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new UserDAOException("Finding the user with username: " + username + " failed");
        }
    }

    @Override
    public boolean isEmailExist(String email) throws UserDAOException {
        return false;
    }

    @Override
    public boolean isUserValid(String username, String password) throws UserDAOException {
        String sqlFindByUsername = "SELECT * FROM users WHERE username = ?";
        boolean isValid = false;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlFindByUsername)){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (password.equals(rs.getString("password"))) {
                    isValid = true;
                }
            }

            return isValid;
            //logging
        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new UserDAOException("Finding the user with username: " + username + " failed");
        }
    }
}
