package api.apiControllers;

import api.businessControllers.PlaylistBusinessController;
import api.dtos.PlaylistDto;
import api.exceptions.ArgumentNotValidException;

public class PlaylistApiController {

    public static final String PLAYLISTS = "/playlists";

    public static final String ID_ID = "/{id}";
    public static final String SONGS = "/songs";

    private PlaylistBusinessController playlistBusinessController = new PlaylistBusinessController();

    public String create(PlaylistDto playlistDto) {
        this.validate(playlistDto, "playlistDto");
        this.validate(playlistDto.getName(), "PlaylistDto name");
        this.validate(playlistDto.getUserId(), "PlaylistDto userId");
        return this.playlistBusinessController.create(playlistDto);
    }

    public PlaylistDto read(String id) {
        return this.playlistBusinessController.read(id);
    }

    public void delete(String id) {
        this.playlistBusinessController.delete(id);
    }

    public void addSong(String playlistId, String songId) {
        this.playlistBusinessController.addSong(playlistId, songId);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }
}
