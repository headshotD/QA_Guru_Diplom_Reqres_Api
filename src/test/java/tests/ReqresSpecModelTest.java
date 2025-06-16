package tests;

import config.WebDriverConfig;
import models.ErrorBodyModel;
import models.LoginBodyModel;
import models.LoginResponseModel;
import models.UsersBodyModel;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static specs.Specs.*;

@Tag("AllApi")
public class ReqresSpecModelTest extends TestBase {

    WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class);

    @Test
    @DisplayName("Проверка авторизации и получения токена")
    void successAuthTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail(config.email());
        authData.setPassword(config.password());

        LoginResponseModel response = step("Check auth and give token", () ->
                given(RequestSpec)
                        .body(authData)

                        .when()
                        .post("/login")

                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class));

        step("Check response", () ->
                assertNotNull(response.getToken()));
                assertFalse(response.getToken().isEmpty());
                assertFalse(response.getToken().trim().isEmpty());
    }

    @Test
    @DisplayName("Авторизация с невалидным емейлом")
    void unSuccessWithBadEmailAuthTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail(config.invalidEmail());
        authData.setPassword(config.password());

        ErrorBodyModel response = step("Auth with bad email", () ->
                given(RequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")

                        .then()
                        .spec(badEmailResponseSpec)
                        .extract().as(ErrorBodyModel.class));

        step("Check response", () ->
                assertEquals("user not found", response.getError()));
    }

    @Test
    @DisplayName("Проверка авторизации без пароля")
    void unSuccessWithMissingPasswordAuthTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail(config.email());

        ErrorBodyModel response = step("Auth with missing password", () ->
                given(RequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")

                        .then()
                        .spec(missingPasswordResponseSpec)
                        .extract().as(ErrorBodyModel.class));

        step("Check response", () ->
                assertEquals("Missing password", response.getError()));
    }

    @Test
    @DisplayName("Проверка авторизации без емейла")
    void registrationUserWithOutEmailTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setPassword(config.password());

        ErrorBodyModel response = step("Auth with missing email", () ->
                given(RequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")

                        .then()
                        .spec(badEmailResponseSpec)
                        .extract().as(ErrorBodyModel.class));

        step("Check response", () ->
                assertEquals("Missing email or username", response.getError()));
    }

    @Test
    @DisplayName("Проверка авторизации без емейла и пароля")
    void registrationUserWithOutEmailAndPasswordTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("");
        authData.setPassword("");

        ErrorBodyModel response = step("Auth with out data", () ->
                given(RequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")

                        .then()
                        .spec(badEmailResponseSpec)
                        .extract().as(ErrorBodyModel.class));

        step("Check response", () ->
                assertEquals("Missing email or username", response.getError()));
    }

    @Test
    @DisplayName("Получение списка юзеров и проверка одного из них")
    void getUserList() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail(config.email());
        authData.setPassword(config.password());

        UsersBodyModel response = step("Check pages and Lawson Michael", () ->
                given(RequestSpec)

                        .when()
                        .queryParam("page","2")
                        .get("/users")

                        .then()
                        .spec(getUsersPageResponseSpec)
                        .body("data.last_name", hasItem("Lawson"))
                        .body("data.first_name", hasItem("Michael"))
                        .extract().as(UsersBodyModel.class));

        step("Check response", () ->
                assertEquals("2", response.getPage()));
    }

    @Test
    @DisplayName("Получение списка юзеров и проверка текста у саппорта")
    void getUserListAndCheckText() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail(config.email());
        authData.setPassword(config.password());

        UsersBodyModel response = step("Check users and text in support", () ->
                given(RequestSpec)

                        .when()
                        .queryParam("page","2")
                        .get("/users")

                        .then()
                        .spec(getUsersPageResponseSpec)
                        .body("support.text", is("Tired of writing endless social media content? Let Content Caddy generate it for you."))
                        .extract().as(UsersBodyModel.class));

        step("Check response", () ->
                assertEquals("2", response.getPage()));
    }

    @Test
    @DisplayName("Удаление юзера")
    void deleteUser() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail(config.email());
        authData.setPassword(config.password());

        step("Delete user", () ->
                given(RequestSpec)

                        .when()
                        .delete("/users/2")

                        .then()
                        .spec(deleteUsersPageResponseSpec)
        );
    }
}
