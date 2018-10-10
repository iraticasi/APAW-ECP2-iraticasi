package api.apiControllers;

import api.businessControllers.PodcastBusinessController;
import api.dtos.PodcastDto;
import api.dtos.PodcastInfoToListDto;
import api.exceptions.ArgumentNotValidException;

import java.util.List;

public class PodcastApiController {

    public static final String PODCASTS = "/podcasts";

    private PodcastBusinessController podcastBusinessController = new PodcastBusinessController();

    public String create(PodcastDto podcastDto) {
        this.validate(podcastDto, "podcastDto");
        this.validate(podcastDto.getName(), "podcastDto name");
        return this.podcastBusinessController.create(podcastDto);
    }

    public List<PodcastInfoToListDto> readAll() {
        return podcastBusinessController.readAll();
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }
}
