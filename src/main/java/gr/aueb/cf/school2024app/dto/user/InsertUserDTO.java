package gr.aueb.cf.school2024app.dto.user;


public class InsertUserDTO extends BaseUserDTO {

    public InsertUserDTO() {

    }

    public InsertUserDTO(String username, String password, String confirmPassword) {
        super(username, password, confirmPassword);

    }


}
