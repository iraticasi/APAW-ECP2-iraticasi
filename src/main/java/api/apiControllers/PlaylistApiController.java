package api.apiControllers;

import api.businessControllers.PlaylistBusinessController;
import api.dtos.PlaylistDto;
import api.exceptions.ArgumentNotValidException;

public class PlaylistApiController {

    public static final String PLAYLISTS = "/playlist";

    private PlaylistBusinessController playlistBusinessController = new PlaylistBusinessController();

    public String create(PlaylistDto playlistDto) {
        this.validate(playlistDto, "playlistDto");
        this.validate(playlistDto.getName(), "PlaylistDto name");
        this.validate(playlistDto.getUserId(), "PlaylistDto userId");
        return this.playlistBusinessController.create(playlistDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }
}
