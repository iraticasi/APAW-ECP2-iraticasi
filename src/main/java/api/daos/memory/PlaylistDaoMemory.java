package api.daos.memory;

import api.daos.PlaylistDao;
import api.daos.UserDao;
import api.entities.Playlist;
import api.entities.User;

import java.util.HashMap;

public class PlaylistDaoMemory extends GenericDaoMemory<Playlist> implements PlaylistDao {

    public PlaylistDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(Playlist playlist) {
        return playlist.getId();
    }

    @Override
    public void setId(Playlist playlist, String id) {
        playlist.setId(id);

    }
}
