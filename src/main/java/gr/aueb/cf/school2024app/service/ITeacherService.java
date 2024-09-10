package gr.aueb.cf.school2024app.service;

import gr.aueb.cf.school2024app.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.school2024app.dto.FiltersDTO;
import gr.aueb.cf.school2024app.dto.TeacherInsertDTO;
import gr.aueb.cf.school2024app.dto.TeacherUpdateDTO;
import gr.aueb.cf.school2024app.model.Teacher;
import gr.aueb.cf.school2024app.service.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {
    Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDAOException;
    Teacher updateTeacher(TeacherUpdateDTO dto) throws TeacherDAOException, TeacherNotFoundException;
    void deleteTeacher(Integer id) throws TeacherDAOException, TeacherNotFoundException;
    Teacher getTeacherById(Integer id) throws TeacherDAOException, TeacherNotFoundException;
    List<Teacher> getTeacherByName(String name) throws TeacherDAOException;
    List<Teacher> getFilteredTeachers(FiltersDTO filtersDTO) throws TeacherDAOException;
    Teacher getTeacherByUsername(String username) throws TeacherDAOException, TeacherNotFoundException;

}
