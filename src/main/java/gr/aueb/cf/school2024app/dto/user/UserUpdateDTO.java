package gr.aueb.cf.school2024app.dto.user;

import gr.aueb.cf.school2024app.dto.teacher.BaseDTO;

public class UserUpdateDTO extends BaseUserDTO {

    private Integer id;

    public UserUpdateDTO() {

    }

    public UserUpdateDTO(String username, String password, Integer id) {
        super(username, password);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
