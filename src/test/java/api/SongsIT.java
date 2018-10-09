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

    @Test
    void createSong() {
        SongDto songDto = new SongDto("cancion uno", null, null, null, null);
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS)
                .body(songDto).post();
        new Client().submit(request);
    }

    @Test
    void createSongComplete() {
        SongDto songDto = new SongDto("cancion uno", "cantante", new Integer(253), "1999/12/11", Genre.CLASSIC);
        HttpRequest request = HttpRequest.builder(SongApiController.SONGS)
                .body(songDto).post();
        new Client().submit(request);
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
