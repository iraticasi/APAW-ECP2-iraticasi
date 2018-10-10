package api.daos.memory;

import api.daos.PodcastDao;
import api.entities.Podcast;

import java.util.HashMap;

public class PodcastDaoMemory extends GenericDaoMemory<Podcast> implements PodcastDao {

    public PodcastDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(Podcast podcast) {
        return podcast.getId();
    }

    @Override
    public void setId(Podcast podcast, String id) {
        podcast.setId(id);

    }
}
