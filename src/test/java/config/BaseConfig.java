package config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.UUID;

public class BaseConfig {
    static String SELENOID_URL = System.getProperty("selenoid.url");
    static String SELENOID_LOGIN = System.getProperty("selenoid.login");
    static String SELENOID_PASSWORD = System.getProperty("selenoid.password");
    public static String loginEP = "/Account/v1/Login";
    public static String booksEP = "/BookStore/v1/Books";
    public static String bookEP = "/BookStore/v1/Book";
    private final WebConfig webConfig;

    public BaseConfig(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void setConfig() {
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = webConfig.getBaseUrl();
        Configuration.browser = webConfig.getBrowser().toString();
        Configuration.browserVersion = webConfig.getBrowserVersion();
        Configuration.browserSize = webConfig.getBrowserSize();

        if (webConfig.isRemote()) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true,
                    "name", "Test: " + UUID.randomUUID()
            ));
            Configuration.remote = webConfig.getRemoteUrl();
            Configuration.browserCapabilities = capabilities;
        }
    }
}
