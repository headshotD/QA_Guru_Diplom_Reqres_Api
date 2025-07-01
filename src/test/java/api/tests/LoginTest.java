package api.tests;

import api.config.WebDriverConfig;
import api.models.ErrorBodyModel;
import api.models.LoginBodyModel;
import api.models.LoginResponseModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@Tag("AllApi")
@Epic("API Авторизация")
@Owner("ONamozov")
@DisplayName("Проверка авторизации")
public class LoginTest extends TestBase {
    WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class);

    @Test
    @DisplayName("Проверка авторизации и получения токена")
    void successAuthTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail(config.email());
        authData.setPassword(config.password());

        LoginResponseModel response = step("Проверка авторизации и получения токена", () ->
                given(RequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")

                        .then()
                        .spec(responseSpec200)
                        .extract().as(LoginResponseModel.class));

        step("Проверка ответа", () ->
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

        ErrorBodyModel response = step("Авторизация с невалидным емейлом", () ->
                given(RequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")

                        .then()
                        .spec(responseSpec400)
                        .extract().as(ErrorBodyModel.class));

        step("Проверка ответа", () ->
                assertEquals("user not found", response.getError()));
    }

    @Test
    @DisplayName("Проверка авторизации без пароля")
    void unSuccessWithMissingPasswordAuthTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail(config.email());

        ErrorBodyModel response = step("Авторизация без пароля", () ->
                given(RequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")

                        .then()
                        .spec(responseSpec400)
                        .extract().as(ErrorBodyModel.class));

        step("Проверка ответа", () ->
                assertEquals("Missing password", response.getError()));
    }

    @Test
    @DisplayName("Проверка авторизации без емейла")
    void registrationUserWithOutEmailTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setPassword(config.password());

        ErrorBodyModel response = step("Проверка авторизации без емейла", () ->
                given(RequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")

                        .then()
                        .spec(responseSpec400)
                        .extract().as(ErrorBodyModel.class));

        step("Проверка ответа", () ->
                assertEquals("Missing email or username", response.getError()));
    }

    @Test
    @DisplayName("Проверка авторизации без емейла и пароля")
    void registrationUserWithOutEmailAndPasswordTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("");
        authData.setPassword("");

        ErrorBodyModel response = step("Авторизация без емейла и пароля", () ->
                given(RequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")

                        .then()
                        .spec(responseSpec400)
                        .extract().as(ErrorBodyModel.class));

        step("Проверка ответа", () ->
                assertEquals("Missing email or username", response.getError()));
    }

}