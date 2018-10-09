package api.dtos;

public class UserDto {


    private String email;

    public UserDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                '}';
    }
}
