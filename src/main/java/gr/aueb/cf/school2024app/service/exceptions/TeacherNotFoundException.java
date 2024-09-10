package gr.aueb.cf.school2024app.service.exceptions;

import gr.aueb.cf.school2024app.model.Teacher;

import java.io.Serial;

public class TeacherNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public TeacherNotFoundException(Teacher teacher) {
        super("Teacher " + teacher.getId() + " not found" );
    }

    public TeacherNotFoundException(String message) {
        super(message);
    }
}
