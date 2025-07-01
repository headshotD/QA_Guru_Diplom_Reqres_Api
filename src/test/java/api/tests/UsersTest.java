package api.tests;

import api.config.WebDriverConfig;
import api.models.LoginBodyModel;
import api.models.UsersBodyModel;
import api.specs.Specs;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
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
@Epic("API Юзеры")
@Owner("ONamozov")
@DisplayName("Проверка юзеров")
public class UsersTest extends TestBase {

    WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class);

    @Test
    @DisplayName("Получение списка юзеров и проверка одного из них")
    void getUserList() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail(config.email());
        authData.setPassword(config.password());

        UsersBodyModel response = step("Получаем список юзеров и проверяем наличие юзера Lawson Michael", () ->
                given(RequestSpec)
                        .when()
                        .queryParam("page", "2")
                        .get("/users")

                        .then()
                        .spec(responseSpec200)
                        .body("data.last_name", hasItem(config.dataLastName()))
                        .body("data.first_name", hasItem(config.dataFirstName()))
                        .extract().as(UsersBodyModel.class));

        step("Проверка ответа", () ->
                assertEquals("2", response.getPage()));
    }

    @Test
    @DisplayName("Получение списка юзеров и проверка текста у саппорта")
    void getUserListAndCheckText() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail(config.email());
        authData.setPassword(config.password());

        UsersBodyModel response = step("Проверка текста в разделе Саппорт", () ->
                given(RequestSpec)
                        .when()
                        .queryParam("page", "2")
                        .get("/users")

                        .then()
                        .spec(responseSpec200)
                        .body("support.text", is(config.supportText()))
                        .extract().as(UsersBodyModel.class));

        step("Проверка текста", () ->
                assertEquals("2", response.getPage()));
    }

    @Test
    @DisplayName("Удаление юзера")
    void deleteUser() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail(config.email());
        authData.setPassword(config.password());

        step("Удаление юзера", () ->
                given(RequestSpec)
                        .when()
                        .delete("/users/2")

                        .then()
                        .spec(Specs.responseSpec204)
        );
    }
}
