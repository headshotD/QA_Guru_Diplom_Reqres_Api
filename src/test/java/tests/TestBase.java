package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void configuration() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
        Configuration.remote = System.getProperty("browserRemote", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}
