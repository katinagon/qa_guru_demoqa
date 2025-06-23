package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    private static final String SELENOID_URL = System.getProperty("selenoid.url");
    private static final String SELENOID_LOGIN = System.getProperty("selenoid.login");;
    private static final String SELENOID_PASSWORD = System.getProperty("selenoid.password");
    public static String loginEP = "/Account/v1/Login";
    public static String booksEP = "/BookStore/v1/Books";
    public static String bookEP = "/BookStore/v1/Book";

    @BeforeAll
    public static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browser.version", "");
        Configuration.browserSize = System.getProperty("browser.size", "1920x1080");
        System.setProperty("selenide.driverManagerEnabled", "false");
    }

    @BeforeEach
    public void beforeEach() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true,
                "name", "Test: " + UUID.randomUUID()
        ));
        Configuration.remote = "https://" + SELENOID_LOGIN + ":" + SELENOID_PASSWORD + "@" + SELENOID_URL + "/wd/hub";
        Configuration.browserCapabilities = capabilities;
        Configuration.holdBrowserOpen = false;
    }

    @AfterEach
    void shutDown() {
        Attach.screenshotAs("Скриншот результата");
        Attach.pageSource();
        if (!Configuration.browser.equals("firefox"))
            Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
