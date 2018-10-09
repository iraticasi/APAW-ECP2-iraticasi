package api;

import api.apiControllers.PlaylistApiController;
import api.apiControllers.UserApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.PlaylistDto;
import api.dtos.UserDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlaylistsIT {


    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    private String createUser() {
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).body(new UserDto("uno")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void createPlaylist() {
        String userId = this.createUser();
        HttpRequest request = HttpRequest.builder(PlaylistApiController.PLAYLIST)
                .body(new PlaylistDto("Playlist one", userId)).post();
        new Client().submit(request);
    }

    @Test
    void createPlaylistserIdNotFound() {
        HttpRequest request = HttpRequest.builder(PlaylistApiController.PLAYLIST)
                .body(new PlaylistDto("Theme one", "invalid-id")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

}
