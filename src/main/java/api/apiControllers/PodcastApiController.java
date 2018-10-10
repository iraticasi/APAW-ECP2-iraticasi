package api.apiControllers;

import api.businessControllers.PodcastBusinessController;
import api.dtos.PodcastCreationDto;
import api.dtos.PodcastInfoToListDto;
import api.exceptions.ArgumentNotValidException;

import java.util.List;

public class PodcastApiController {

    public static final String PODCASTS = "/podcasts";

    private PodcastBusinessController podcastBusinessController = new PodcastBusinessController();

    public String create(PodcastCreationDto podcastCreationDto) {
        this.validate(podcastCreationDto, "podcastCreationDto");
        this.validate(podcastCreationDto.getName(), "podcastCreationDto name");
        return this.podcastBusinessController.create(podcastCreationDto);
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
