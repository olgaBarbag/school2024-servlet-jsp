package gr.aueb.cf.school2024app.model;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer tid;

    public User() {
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tid = tid;
    }

    public User(int id, String username) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public Integer getTeacherId() {return tid;}

    public void setTeacherId(Integer teacherId) {this.tid = teacherId;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", teacherId=" + tid +
                '}';
    }
}
