package api.daos.memory;

import api.daos.DaoFactory;
import api.daos.PlaylistDao;
import api.daos.UserDao;
import api.entities.Playlist;

public class DaoMemoryFactory extends DaoFactory {

    private UserDao userDao;

    private PlaylistDao playlistDao;

    @Override
    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoMemory();
        }
        return userDao;
    }

    @Override
    public PlaylistDao getPlaylistDao() {
        if (playlistDao == null) {
            playlistDao = new PlaylistDaoMemory();
        }
        return playlistDao;
    }
}
