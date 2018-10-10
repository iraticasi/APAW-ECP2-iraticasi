package api.businessControllers;

import api.daos.DaoFactory;
import api.dtos.PlaylistDto;
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

    public PlaylistDto read(String id) {
        Playlist playlist = DaoFactory.getFactory().getPlaylistDao().read(id).orElseThrow(
                () -> new NotFoundException("Playlist (" + id + ")"));
        return new PlaylistDto(playlist);
    }

    public void delete(String id) {
        DaoFactory.getFactory().getPlaylistDao().deleteById(id);
    }
}
