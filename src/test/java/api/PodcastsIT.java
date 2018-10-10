package api;

import api.apiControllers.PodcastApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.PodcastCreationDto;
import api.dtos.PodcastInfoToListDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PodcastsIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreatePodcast() {
        this.createPodcast("podcast one");
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
        PodcastCreationDto podcastCreationDto = new PodcastCreationDto(null, null);
        HttpRequest request = HttpRequest.builder(PodcastApiController.PODCASTS).body(podcastCreationDto).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testReadAll() {
        for (int i = 0; i < 5; i++) {
            this.createPodcast("podcast" + i);
        }
        HttpRequest request = HttpRequest.builder(PodcastApiController.PODCASTS).get();
        List<PodcastInfoToListDto> podcastInfoToListDtos = (List<PodcastInfoToListDto>) new Client().submit(request).getBody();
        assertTrue(podcastInfoToListDtos.size() >= 5);
    }

    @Test
    private String createPodcast(String name) {
        PodcastCreationDto podcastCreationDto = new PodcastCreationDto(name, null);
        HttpRequest request = HttpRequest.builder(PodcastApiController.PODCASTS).body(podcastCreationDto).post();
        return (String) new Client().submit(request).getBody();
    }
}
