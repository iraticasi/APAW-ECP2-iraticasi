package api.businessControllers;

import api.daos.DaoFactory;
import api.dtos.PodcastDto;
import api.entities.Podcast;

public class PodcastBusinessController {

    public String create(PodcastDto podcastDto) {
        Podcast podcast = new Podcast(podcastDto.getName(), podcastDto.getDescription());
        DaoFactory.getFactory().getPodcastDao().save(podcast);
        return podcast.getId();
    }

}
