package gr.aueb.cf.school2024app.dto.teacher;

public abstract class BaseDTO {
    private String firstName;
    private String lastName;

    public BaseDTO() {
    }

    public BaseDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
