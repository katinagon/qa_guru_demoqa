package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.BaseConfig;
import config.WebConfig;
import config.WebProvider;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    public static final WebConfig webConfig = WebProvider.getWebConfig();

    @BeforeAll
    public static void setupBaseTestConfiguration() {
        BaseConfig baseConfig = new BaseConfig(webConfig);
        baseConfig.setConfig();
    }

    @BeforeEach
    public void setupAllureListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void shutDown() {
        Attach.screenshotAs("Скриншот результата");
        Attach.pageSource();
        if (!Configuration.browser.equals("firefox"))
            Attach.browserConsoleLogs();
        if (webConfig.isRemote()) {
            Attach.addVideo();
        }
        closeWebDriver();
    }
}
