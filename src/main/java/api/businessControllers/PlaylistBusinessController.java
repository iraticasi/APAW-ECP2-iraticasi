package api.businessControllers;

import api.daos.DaoFactory;
import api.dtos.PlaylistDto;
import api.dtos.UserDto;
import api.entities.Playlist;
import api.entities.User;
import api.exceptions.NotFoundException;

public class PlaylistBusinessController {

    public String create(PlaylistDto playlistDto) {
        User user = DaoFactory.getFactory().getUserDao().read(playlistDto.getUserId()).orElseThrow(
                () -> new NotFoundException("User (" + playlistDto.getUserId() + ")"));
        Playlist playlist = new Playlist(playlistDto.getName(), user);
        DaoFactory.getFactory().getPlaylistDao().save(playlist);
        return playlist.getId();
    }

}
