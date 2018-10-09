package api.daos.memory;

import api.daos.DaoFactory;
import api.daos.PlaylistDao;
import api.daos.SongDao;
import api.daos.UserDao;

public class DaoMemoryFactory extends DaoFactory {

    private UserDao userDao;

    private PlaylistDao playlistDao;

    private SongDao songDao;

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

    @Override
    public SongDao getSongDao() {
        if (songDao == null) {
            songDao = new SongDaoMemory();
        }
        return songDao;
    }
}
