package api.tests;

import api.config.WebDriverConfig;
import api.models.ErrorBodyModel;
import api.models.LoginBodyModel;
import api.models.LoginResponseModel;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@Tag("AllApi")
public class LoginTest extends TestBase {
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

}
