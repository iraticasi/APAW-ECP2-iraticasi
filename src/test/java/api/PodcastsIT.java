package api;

import api.apiControllers.PodcastApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.PodcastDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PodcastsIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void createPodcast() {
        PodcastDto podcastDto = new PodcastDto("nombre", null);
        HttpRequest request = HttpRequest.builder(PodcastApiController.PODCASTS).body(podcastDto).post();
        new Client().submit(request).getBody();
    }

    @Test
    void testPodcastInvalidRequest() {
        HttpRequest request = HttpRequest.builder(PodcastApiController.PODCASTS + "/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePodcastWithoutPodcastDto() {
        HttpRequest request = HttpRequest.builder(PodcastApiController.PODCASTS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePodcastWithoutPodcastDtoName() {
        PodcastDto podcastDto = new PodcastDto(null, null);
        HttpRequest request = HttpRequest.builder(PodcastApiController.PODCASTS).body(podcastDto).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

}
