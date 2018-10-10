package api.daos.memory;

import api.daos.*;

public class DaoMemoryFactory extends DaoFactory {

    private UserDao userDao;

    private PlaylistDao playlistDao;

    private SongDao songDao;

    private PodcastDao podcastDao;

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

    @Override
    public PodcastDao getPodcastDao() {
        if (podcastDao == null) {
            podcastDao = new PodcastDaoMemory();
        }
        return podcastDao;
    }
}
