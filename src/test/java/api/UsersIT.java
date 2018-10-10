package api;

import api.apiControllers.UserApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.UserDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UsersIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }


    @Test
    void testCreateUser() {
        this.createUser();
    }

    @Test
    void testUserInvalidRequest() {
        HttpRequest request = HttpRequest.builder(UserApiController.USERS + "/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateUserWithoutUserDto() {
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateUserWithoutUserDtoEmail() {
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).body(new UserDto(null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
    @Test
    void testUpdateUser() {
        String id = this.createUser();
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).path(UserApiController.ID_ID)
                .expandPath(id).body(new UserDto("dos")).put();
        new Client().submit(request);
    }

    @Test
    void testUpdateUserWithoutUserDto() {
        String id = this.createUser();
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).path(UserApiController.ID_ID)
                .expandPath(id).body(null).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
    @Test
    void testUpdateUserWithoutUserDtoEmail() {
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).path(UserApiController.ID_ID)
                .expandPath("invalid-id").body(new UserDto(null)).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testUpdateUserNotFoundException() {
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).path(UserApiController.ID_ID)
                .expandPath("invalid-id").body(new UserDto("dos")).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    private String createUser() {
        HttpRequest request = HttpRequest.builder(UserApiController.USERS).body(new UserDto("uno")).post();
        return (String) new Client().submit(request).getBody();
    }
}
