package api;

import api.apiControllers.PlaylistApiController;
import api.apiControllers.PodcastApiController;
import api.apiControllers.SongApiController;
import api.apiControllers.UserApiController;
import api.dtos.PlaylistDto;
import api.dtos.PodcastCreationDto;
import api.dtos.SongDto;
import api.dtos.UserDto;
import api.exceptions.ArgumentNotValidException;
import api.exceptions.NotFoundException;
import api.exceptions.RequestInvalidException;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

public class Dispatcher {

    private UserApiController userApiController = new UserApiController();
    private PlaylistApiController playlistApiController = new PlaylistApiController();
    private SongApiController songApiController = new SongApiController();
    private PodcastApiController podcastApiController = new PodcastApiController();

    public void submit(HttpRequest request, HttpResponse response) {
        String ERROR_MESSAGE = "{'error':'%S'}";
        try {
            switch (request.getMethod()) {
                case POST:
                    this.doPost(request, response);
                    break;
                case GET:
                    this.doGet(request, response);
                    break;
                case PUT:
                    this.doPut(request, response);
                    break;
                case PATCH:
                    throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
                case DELETE:
                    throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
                default:
                    throw new RequestInvalidException("method error: " + request.getMethod());
            }
        } catch (ArgumentNotValidException | RequestInvalidException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {  // Unexpected
            exception.printStackTrace();
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(UserApiController.USERS)) {
            response.setBody(this.userApiController.create((UserDto) request.getBody()));
        } else if (request.isEqualsPath(PlaylistApiController.PLAYLISTS)) {
            response.setBody(this.playlistApiController.create((PlaylistDto) request.getBody()));
        } else if (request.isEqualsPath(SongApiController.SONGS)) {
            response.setBody(this.songApiController.create((SongDto) request.getBody()));
        } else if (request.isEqualsPath(PodcastApiController.PODCASTS)) {
            response.setBody(this.podcastApiController.create((PodcastCreationDto) request.getBody()));
        } else {
            throw new RequestInvalidException("method error: " + request.getMethod());
        }
    }

    private void doGet(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(PodcastApiController.PODCASTS)) {
            response.setBody(this.podcastApiController.readAll());
        } else {
            throw new RequestInvalidException("method error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doPut(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(UserApiController.USERS + UserApiController.ID_ID)) {
            this.userApiController.update(request.getPath(1), (UserDto) request.getBody());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

}
