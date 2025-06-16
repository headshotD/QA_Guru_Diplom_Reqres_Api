package api.tests;

import api.config.WebDriverConfig;
import api.models.LoginBodyModel;
import api.models.UsersBodyModel;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.specs.Specs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("AllApi")
public class UsersTest extends TestBase {

    WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class);

    @Test
    @DisplayName("Получение списка юзеров и проверка одного из них")
    void getUserList() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail(config.email());
        authData.setPassword(config.password());

        UsersBodyModel response = step("Check pages and Lawson Michael", () ->
                given(RequestSpec)
                        .when()
                        .queryParam("page", "2")
                        .get("/users")

                        .then()
                        .spec(getUsersPageResponseSpec)
                        .body("data.last_name", hasItem(config.dataLastName()))
                        .body("data.first_name", hasItem(config.dataFirstName()))
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
                        .queryParam("page", "2")
                        .get("/users")

                        .then()
                        .spec(getUsersPageResponseSpec)
                        .body("support.text", is(config.supportText()))
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
