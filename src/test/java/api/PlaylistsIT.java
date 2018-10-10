package api;

import api.apiControllers.PlaylistApiController;
import api.apiControllers.UserApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.PlaylistDto;
import api.dtos.UserDto;
import api.entities.Playlist;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

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
}
