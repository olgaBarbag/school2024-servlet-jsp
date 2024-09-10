package gr.aueb.cf.school2024app.dto.teacher;

public class TeacherUpdateDTO extends BaseDTO {
    private Integer id;

    public TeacherUpdateDTO() {

    }

    public TeacherUpdateDTO(Integer id, String firstName, String lastName) {
        super(firstName, lastName);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
