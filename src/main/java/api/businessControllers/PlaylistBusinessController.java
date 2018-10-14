package api.businessControllers;

import api.daos.DaoFactory;
import api.dtos.PlaylistDto;
import api.entities.Playlist;
import api.entities.Song;
import api.entities.User;
import api.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class PlaylistBusinessController {

    public String create(PlaylistDto playlistDto) {
        User user = DaoFactory.getFactory().getUserDao().read(playlistDto.getUserId()).orElseThrow(
                () -> new NotFoundException("User (" + playlistDto.getUserId() + ")"));
        Playlist playlist = new Playlist(playlistDto.getName(), user);
        DaoFactory.getFactory().getPlaylistDao().save(playlist);
        return playlist.getId();
    }

    public void delete(String id) {
        DaoFactory.getFactory().getPlaylistDao().deleteById(id);
    }

    public void addSong(String playlistId, String songId) {
        Playlist playlist = DaoFactory.getFactory().getPlaylistDao().read(playlistId).orElseThrow(
                () -> new NotFoundException("Playlist (" + playlistId + ")"));
        Song song= DaoFactory.getFactory().getSongDao().read(songId).orElseThrow(
                () -> new NotFoundException("Song (" + songId + ")"));
        playlist.add(song);
    }

    public List<PlaylistDto> findByUser(String userId) {
        User user = DaoFactory.getFactory().getUserDao().read(userId).orElseThrow(
                () -> new NotFoundException("User (" + userId + ")"));
        List<Playlist> playlists = DaoFactory.getFactory().getPlaylistDao().findAll();
        List<PlaylistDto> playlistDtos = new ArrayList<>();
        for (Playlist playlist: playlists) {
            if (playlist.getUser().equals(user))
                playlistDtos.add(new PlaylistDto(playlist));
        }
        return playlistDtos;
    }
}
