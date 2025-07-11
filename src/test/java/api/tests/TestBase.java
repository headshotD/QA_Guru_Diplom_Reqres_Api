package api.tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void configuration() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
        Configuration.remote = System.getProperty("browserRemote");
    }
}