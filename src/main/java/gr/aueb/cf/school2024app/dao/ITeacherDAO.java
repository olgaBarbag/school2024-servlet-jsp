package gr.aueb.cf.school2024app.dao;

import gr.aueb.cf.school2024app.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.school2024app.model.Teacher;

import java.util.List;

public interface ITeacherDAO {

    Teacher insert(Teacher teacher) throws TeacherDAOException;

    Teacher update(Teacher teacher) throws TeacherDAOException;

    void delete(Integer id) throws TeacherDAOException;

    Teacher findById(int id) throws TeacherDAOException;

    List<Teacher> findByName(String name) throws TeacherDAOException;

    List<Teacher> filteredTeacher(String firstname, String lastname) throws TeacherDAOException;

    Teacher findByUsername(String username) throws TeacherDAOException;

    void deletedAtAll(Integer id) throws TeacherDAOException;
}
