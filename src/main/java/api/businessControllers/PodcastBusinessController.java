package api.businessControllers;

import api.daos.DaoFactory;
import api.dtos.PodcastDto;
import api.dtos.PodcastInfoToListDto;
import api.entities.Podcast;

import java.util.ArrayList;
import java.util.List;

public class PodcastBusinessController {

    public String create(PodcastDto podcastDto) {
        Podcast podcast = new Podcast(podcastDto.getName(), podcastDto.getDescription());
        DaoFactory.getFactory().getPodcastDao().save(podcast);
        return podcast.getId();
    }

    public List<PodcastInfoToListDto> readAll() {
        List<Podcast> podcasts = DaoFactory.getFactory().getPodcastDao().findAll();
        List<PodcastInfoToListDto> podcastInfoToListDtos = new ArrayList<>();
        for (Podcast podcast : podcasts) {
            podcastInfoToListDtos.add(new PodcastInfoToListDto(podcast));
        }
        return podcastInfoToListDtos;
    }
}
