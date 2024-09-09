package gr.aueb.cf.school2024app.dao.exceptions;

import java.io.Serial;

public class TeacherDAOException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public TeacherDAOException( String message ) {
        super( message );
    }
}
