package api.apiControllers;

import api.businessControllers.UserBusinessController;
import api.dtos.UserDto;
import api.exceptions.ArgumentNotValidException;

public class UserApiController {

    public static final String USERS = "/users";

    private UserBusinessController userBusinessController = new UserBusinessController();

    public String create(UserDto userDto) {
        this.validate(userDto, "userDto");
        this.validate(userDto.getEmail(), "UserDto Email");
        return this.userBusinessController.create(userDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }
}
