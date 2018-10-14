package api;

import api.apiControllers.PlaylistApiController;
import api.apiControllers.SongApiController;
import api.apiControllers.UserApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.PlaylistDto;
import api.dtos.SongDto;
import api.dtos.UserDto;
import api.entities.Playlist;
import api.entities.Song;
import api.exceptions.NotFoundException;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlaylistsIT {


    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    private String createUser() {
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).body(new UserDto("uno")).post();
        return (String) new Client().submit(request).getBody();
    }

    private String createPlaylist(String name) {
        String userId = this.createUser();
        HttpRequest request = HttpRequest.builder(PlaylistApiController.PLAYLISTS)
                .body(new PlaylistDto(name, userId)).post();
        return (String) new Client().submit(request).getBody();
    }

    private  String createSong(String name) {
        SongDto songDto = new SongDto(name, null, null, null, null);
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS)
                .body(songDto).post();
        return (String)new Client().submit(request).getBody();
    }

    @Test
    void testCreatePlaylist(){
        this.createPlaylist("playlist uno");
    }

    @Test
    void testPlaylistInvalidRequest() {
        HttpRequest request = HttpRequest.builder(PlaylistApiController.PLAYLISTS + "/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePlaylistWithoutPlaylistDto() {
        HttpRequest request = HttpRequest.builder(PlaylistApiController.PLAYLISTS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePlaylistWithoutPlaylistDtoName() {
        PlaylistDto playlistDto = new PlaylistDto(null, null);
        HttpRequest request = HttpRequest.builder(PlaylistApiController.PLAYLISTS).body(playlistDto).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }


    @Test
    void createPlaylistserIdNotFound() {
        HttpRequest request = HttpRequest.builder(PlaylistApiController.PLAYLISTS)
                .body(new PlaylistDto("Theme one", "invalid-id")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testDelete() {
        String id = this.createPlaylist("playlist uno");
        HttpRequest request1 = HttpRequest.builder(PlaylistApiController.PLAYLISTS + "/" + id).get();
        new Client().submit(request1);
        HttpRequest request2 = HttpRequest.builder(PlaylistApiController.PLAYLISTS).path(PlaylistApiController.ID_ID)
                .expandPath(id).delete();
        new Client().submit(request2);
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request1));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testAddSong() {
        String playlistId = this.createPlaylist("playlist uno");
        String songId = this.createSong("song uno");
        HttpRequest request = HttpRequest.builder(PlaylistApiController.PLAYLISTS).path(PlaylistApiController.ID_ID)
                .expandPath(playlistId).path(PlaylistApiController.SONGS).body(songId).patch();
        new Client().submit(request);
        Playlist playlist= DaoFactory.getFactory().getPlaylistDao().read(playlistId)
                .orElseThrow(() -> new NotFoundException("Playlist id: " + playlistId));
        Song song= DaoFactory.getFactory().getSongDao().read(songId)
                .orElseThrow(() -> new NotFoundException("Song id: " + songId));
        Set<Song> expected = new HashSet<>();
        expected.add(song);
        assertEquals(expected, playlist.getSongs());

    }

    @Test
    void testAddSongPlaylistNoFound(){
        String playlistId = "invalid";
        String songId = this.createSong("song uno");
        HttpRequest request = HttpRequest.builder(PlaylistApiController.PLAYLISTS).path(PlaylistApiController.ID_ID)
                .expandPath(playlistId).path(PlaylistApiController.SONGS).body(songId).patch();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testAddSongNoFound(){
        String playlistId = this.createPlaylist("playlist uno");
        String songId = "invalid";
        HttpRequest request = HttpRequest.builder(PlaylistApiController.PLAYLISTS).path(PlaylistApiController.ID_ID)
                .expandPath(playlistId).path(PlaylistApiController.SONGS).body(songId).patch();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }
}
