package gr.aueb.cf.school2024app.service;

import gr.aueb.cf.school2024app.dao.ITeacherDAO;
import gr.aueb.cf.school2024app.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.school2024app.dto.FiltersDTO;
import gr.aueb.cf.school2024app.dto.TeacherInsertDTO;
import gr.aueb.cf.school2024app.dto.TeacherUpdateDTO;
import gr.aueb.cf.school2024app.model.Teacher;
import gr.aueb.cf.school2024app.service.exceptions.TeacherNotFoundException;

import java.util.List;

public class TeacherServiceImpl implements ITeacherService {

    private ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDAOException {
        Teacher teacher;

        try {
            teacher = mapDTOtoTeacher(dto);
            return teacherDAO.insert(teacher);
        } catch (TeacherDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public Teacher updateTeacher(TeacherUpdateDTO dto) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher;

        try {
            teacher = mapDTOtoTeacher(dto);

            if (teacherDAO.findById(teacher.getId()) == null) {
                throw new TeacherNotFoundException("Teacher not found");
            }
            return teacherDAO.update(teacher);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public void deleteTeacher(Integer id) throws TeacherDAOException, TeacherNotFoundException {
        try {

            if (teacherDAO.findById(id) == null) {
                throw new TeacherNotFoundException("Teacher not found");
            }
            teacherDAO.delete(id);

        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public Teacher getTeacherById(Integer id) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher;
        try {
            teacher = teacherDAO.findById(id);

            if (teacher == null) {
                throw new TeacherNotFoundException("Teacher not found");
            }

            return teacher;

        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    public List<Teacher> getTeacherByName(String name) throws TeacherDAOException {
        List<Teacher> teachers;

        try {
            teachers = teacherDAO.findByName(name);
            return teachers;

        } catch (TeacherDAOException e) {
            e.printStackTrace();
            throw e;
        }

    }
    @Override
    public List<Teacher> getFilteredTeachers(FiltersDTO filtersDTO) throws TeacherDAOException {
        List<Teacher> teachers;
        try {
            teachers = teacherDAO.filteredTeacher(filtersDTO.getFirstName(), filtersDTO.getLastName());
            //it does not need to check if there is any teacher with this filter because the list can be empty.
            return teachers;

        } catch (TeacherDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    @Override
    public Teacher getTeacherByUsername(String username) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher;

        try {
            teacher = teacherDAO.findByUsername(username);
            //it does not need to check if there is any teacher with this filter because the list can be empty.
            return teacher;

        } catch (TeacherDAOException e) {
            e.printStackTrace();
            //logging
            //rollback
            throw e;
        }
    }

    private Teacher mapDTOtoTeacher(TeacherInsertDTO dto) {
        return new Teacher(null, dto.getFirstName(), dto.getLastName());
    }

    private Teacher mapDTOtoTeacher(TeacherUpdateDTO dto) {
        return new Teacher(dto.getId(), dto.getFirstName(), dto.getLastName());
    }
}
