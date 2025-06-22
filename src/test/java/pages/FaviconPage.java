package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class FaviconPage {
    @Step("Открываем страницу favicon.ico для установки куки")
    public FaviconPage openPage() {
        open("/favicon.ico");
        return this;
    }

    @Step("Устанавливаем куки")
    public FaviconPage setCookie(String userId, String expires, String token) {
        getWebDriver().manage().addCookie(new Cookie("userID", userId));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));
        getWebDriver().manage().addCookie(new Cookie("token", token));
        return this;
    }
}
