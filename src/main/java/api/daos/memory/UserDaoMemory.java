package api.daos.memory;

import api.daos.UserDao;
import api.entities.User;

import java.util.HashMap;

public class UserDaoMemory extends GenericDaoMemory<User> implements UserDao {

    public UserDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(User user) {
        return user.getId();
    }

    @Override
    public void setId(User user, String id) {
        user.setId(id);

    }
}
