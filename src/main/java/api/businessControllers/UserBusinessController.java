package api.businessControllers;

import api.daos.DaoFactory;
import api.dtos.UserDto;
import api.entities.User;
import api.exceptions.NotFoundException;

public class UserBusinessController {

    public String create(UserDto userDto) {
        User user = new User(userDto.getEmail());
        DaoFactory.getFactory().getUserDao().save(user);
        return user.getId();
    }

    public void updateEmail(String id, UserDto userDto) {
        User user = DaoFactory.getFactory().getUserDao().read(id).orElseThrow(() -> new NotFoundException("User id: " + id));
        user.setEmail(userDto.getEmail());
        DaoFactory.getFactory().getUserDao().save(user);
    }
}
