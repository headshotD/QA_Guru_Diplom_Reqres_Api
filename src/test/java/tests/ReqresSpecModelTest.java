package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.*;

@Tag("AllApi")
public class ReqresSpecModelTest {
@BeforeAll
static void browserConfiguration() {

    Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
    Configuration.browser = System.getProperty("browser", "chrome");
    Configuration.browserVersion = System.getProperty("browserVersion", "127.0");
    Configuration.timeout = 2000;
    Configuration.remote = System.getProperty("browserRemote", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

    DesiredCapabilities capabilities = new DesiredCapabilities();

    capabilities.setCapability("selenoid:options", Map.<String, Object>of(
            "enableVNC", true,
            "enableVideo", true
    ));
    Configuration.browserCapabilities = capabilities;
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

}
    @Test
    @DisplayName("Проверка авторизации и получения токена")
    void successAuthTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel response = step("Check auth and give token", () ->
                given(RequestSpec)
                        .body(authData)

                        .when()
                        .post("/api/login")

                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class));

        step("Check response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }


    @Test
    @DisplayName("Авторизация с невалидным емейлом")
    void unSuccessWithBadEmailAuthTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.h1234olt@reqres.in");
        authData.setPassword("cityslicka");

        ErrorBodyModel response = step("Auth with bad email", () ->
                given(RequestSpec)
                        .body(authData)
                        .when()
                        .post("/api/login")

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
        authData.setEmail("eve.holt@reqres.in");

        ErrorBodyModel response = step("Auth with missing password", () ->
                given(RequestSpec)
                        .body(authData)
                        .when()
                        .post("/api/login")

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
        authData.setPassword("cityslicka");

        ErrorBodyModel response = step("Auth with missing email", () ->
                given(RequestSpec)
                        .body(authData)
                        .when()
                        .post("/api/login")

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
                        .post("/api/login")

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
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        UsersBodyModel response = step("Check pages and Lawson Michael", () ->
                given(RequestSpec)

                        .when()
                        .get("/api/users?page=2")

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
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        UsersBodyModel response = step("Check users and text in support", () ->
                given(RequestSpec)

                        .when()
                        .get("/api/users?page=2")

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
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        step("Delete user", () ->
                given(RequestSpec)

                        .when()
                        .delete("/api/users/2")

                        .then()
                        .spec(deleteUsersPageResponseSpec)
        );


    }
}
