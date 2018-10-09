package api.daos.memory;

import api.daos.PlaylistDao;
import api.daos.SongDao;
import api.entities.Playlist;
import api.entities.Song;

import java.util.HashMap;

public class SongDaoMemory extends GenericDaoMemory<Song> implements SongDao {

    public SongDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(Song song) {
        return song.getId();
    }

    @Override
    public void setId(Song song, String id) {
        song.setId(id);

    }
}
