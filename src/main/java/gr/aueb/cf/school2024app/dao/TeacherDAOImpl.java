package gr.aueb.cf.school2024app.dao;

import gr.aueb.cf.school2024app.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.school2024app.model.Teacher;
import gr.aueb.cf.school2024app.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {

    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {

        String sqlInsert = "INSERT INTO teachers (firstname, lastname) values(?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlInsert)
        ){
            ps.setString(1, teacher.getFirstname());
            ps.setString(2, teacher.getLastname());
            ps.executeUpdate();

            //logging

            return teacher;

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new TeacherDAOException("Insert teacher failed");
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String sqlUpdate = "UPDATE teachers SET firstname = ?, lastname = ? WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlUpdate)
        ){
            ps.setString(1, teacher.getFirstname());
            ps.setString(2, teacher.getLastname());
            ps.setInt(3, teacher.getId());
            ps.executeUpdate();

            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new TeacherDAOException("Update teacher with id: " + teacher.getId() + " failed");
        }
    }

    @Override
    public void delete(Integer id) throws TeacherDAOException {
        String sqlDelete = "DELETE FROM teachers WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlDelete)
        ){
            ps.setInt(1, id);
            ps.executeUpdate();
            //logging

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new TeacherDAOException("Delete teacher with id: " + id + " failed");
        }

    }

    @Override
    public Teacher findById(int id) throws TeacherDAOException {
        String sqlFindById = "SELECT * FROM teachers WHERE id = ?";
        Teacher teacher = null;

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlFindById)
        ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) teacher = new Teacher(id, rs.getString("firstname"), rs.getString("lastname"));
            return teacher;

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new TeacherDAOException("Finding teacher with id: " + id + " failed");
        }
    }

    @Override
    public List<Teacher> findByName(String name) throws TeacherDAOException {
        String sqlFindByName = "SELECT * FROM teachers WHERE lastname LIKE ? || firstname LIKE ?";
        List<Teacher> teachers = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlFindByName)){
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                teachers.add(new Teacher
                        (rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"))
                );
            }
            return teachers;

        } catch (SQLException e) {
            e.printStackTrace();
            //logging
            throw new TeacherDAOException("Finding teacher with: " + name + " failed");
        }
    }
}
