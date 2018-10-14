package api.apiControllers;

import api.businessControllers.PlaylistBusinessController;
import api.dtos.PlaylistDto;
import api.exceptions.ArgumentNotValidException;

import java.util.List;

public class PlaylistApiController {

    public static final String PLAYLISTS = "/playlists";

    public static final String ID_ID = "/{id}";

    public static final String SONGS = "/songs";

    public static final String SEARCH = "/search";

    private PlaylistBusinessController playlistBusinessController = new PlaylistBusinessController();

    public String create(PlaylistDto playlistDto) {
        this.validate(playlistDto, "playlistDto");
        this.validate(playlistDto.getName(), "PlaylistDto name");
        this.validate(playlistDto.getUserId(), "PlaylistDto userId");
        return this.playlistBusinessController.create(playlistDto);
    }

    public void delete(String id) {
        this.playlistBusinessController.delete(id);
    }

    public void addSong(String playlistId, String songId) {
        this.validate(playlistId, "playlistId");
        this.validate(songId, "songId");
        this.playlistBusinessController.addSong(playlistId, songId);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + " is NULL");
        }
    }

    public List<PlaylistDto> findByUser(String userId) {
        this.validate(userId, "query param user");
        return playlistBusinessController.findByUser(userId);
    }
}
