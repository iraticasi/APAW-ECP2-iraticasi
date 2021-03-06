package api;

import api.apiControllers.SongApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.SongDto;
import api.entities.Genre;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SongsIT {


    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    private String createSong(String name) {
        SongDto songDto = new SongDto(name, null, null, null, null);
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS)
                .body(songDto).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testCreateSong() {
        this.createSong("cancion uno");
    }

    @Test
    void createSongComplete() {
        SongDto songDto = new SongDto("cancion uno", "cantante", new Integer(253), "1999/12/11", Genre.CLASSIC);
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS)
                .body(songDto).post();
        new Client().submit(request);
    }

    @Test
    void testSongInvalidRequest() {
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS + "/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateSongWithoutSongDto() {
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateSongWithoutSongDtoName() {
        SongDto songDto = new SongDto(null, null, null, null, null);
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS).body(songDto).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createSongWrongDateFormat() {
        SongDto songDto = new SongDto("cancion uno", "cantante", new Integer(253), "99/12/11", Genre.CLASSIC);
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS)
                .body(songDto).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createSongNegativeSeconds() {
        SongDto songDto = new SongDto("cancion uno", "cantante", new Integer(-4), "99/12/11", Genre.CLASSIC);
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS)
                .body(songDto).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

}
