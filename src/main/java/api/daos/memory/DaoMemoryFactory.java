package api.daos.memory;

import api.daos.DaoFactory;
import api.daos.UserDao;

public class DaoMemoryFactory extends DaoFactory {

    private UserDao userDao;

    @Override
    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoMemory();
        }
        return userDao;
    }
}
