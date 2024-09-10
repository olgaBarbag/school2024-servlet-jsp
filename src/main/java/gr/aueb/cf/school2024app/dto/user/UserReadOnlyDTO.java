package gr.aueb.cf.school2024app.dto.user;

public class UserReadOnlyDTO extends BaseUserDTO {
    private Integer id;

    public UserReadOnlyDTO() {

    }

    public UserReadOnlyDTO(String username, String password, Integer id) {
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
