package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ReqresTest {
    String baseUrl = "https://reqres.in";
    String accessToken = "reqres-free-v1";

    @Test
    @DisplayName("Проверка авторизации и получения токена")
    void successAuthTest() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
        given()
                .header("x-api-key", accessToken)
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post(baseUrl + "/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Авторизация с невалидным емейлом")
    void unSuccessWithBadEmailAuthTest() {
        String authData = "{\"email\": \"eve.h123olt@reqres.in\", \"password\": \"cityslicka\"}";
        given()
                .header("x-api-key", accessToken)
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post(baseUrl + "/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("user not found"));
    }

    @Test
    @DisplayName("Проверка авторизации без пароля")
    void unSuccessWithMissingPasswordAuthTest() {
        String authData = "{\"email\": \"eve.h123olt@reqres.in\"}";
        given()
                .header("x-api-key", accessToken)
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post(baseUrl + "/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
    @Test
    @DisplayName("Проверка авторизации без емейла")
    void registrationUserWithOutEmailTest() {
        String authData = "{\n" +
                "    \"password\": \"kakiw\"\n" +
                "}";
        given()
                .header("x-api-key", accessToken)
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post(baseUrl + "/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }
    @Test
    @DisplayName("Проверка авторизации без емейла и пароля")
    void registrationUserWithOutEmailAndPasswordTest() {
        String authData = "{}";
        given()
                .header("x-api-key", accessToken)
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post(baseUrl + "/api/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }
    @Test
    @DisplayName("Получение списка юзеров и проверка одного из них")
    void getUserList() {
        given()
                .header("x-api-key", accessToken)
                .contentType(JSON)
                .log().uri()

                .when()
                .get(baseUrl + "/api/users?page=2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(2))
                .body("data.last_name", hasItem("Lawson"))
                .body("data.first_name", hasItem("Michael"))
                .body("support.text", is("Tired of writing endless social media content? Let Content Caddy generate it for you."));
    }
    @Test
    @DisplayName("Получение списка юзеров и проверка текста у саппорта")
    void getUserListAndCheckText() {
        given()
                .header("x-api-key", accessToken)
                .contentType(JSON)
                .log().uri()

                .when()
                .get(baseUrl + "/api/users?page=2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(2))
                .body("support.text", is("Tired of writing endless social media content? Let Content Caddy generate it for you."));
    }
        @Test
        @DisplayName("Удаление юзера")
        void deleteUser() {
            given()
                    .header("x-api-key", accessToken)
                    .contentType(JSON)
                    .log().uri()

                    .when()
                    .delete(baseUrl + "/api/users/2")

                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(204);
        }
    }
