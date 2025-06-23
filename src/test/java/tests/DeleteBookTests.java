package tests;

import api.BookApi;
import helpers.WithLogin;
import io.qameta.allure.Owner;
import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.FaviconPage;
import pages.ProfilePage;

import java.util.List;

import static api.BookApi.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static io.restassured.RestAssured.given;
import static specs.BookSpec.bookRequestSpec;
import static specs.BookSpec.bookResponseSpec;
import static specs.LoginSpec.authRequestSpec;
import static specs.LoginSpec.authResponseSpec;
import static tests.TestData.*;

@Owner("goncharova-ek")
@Tag("delete_books")
@DisplayName("Тесты на удаление книг")
public class DeleteBookTests extends TestBase {
    //String userId, token, expires;

    /*@BeforeEach
    public void authorizationTest() {
        LoginRequestModel authData = new LoginRequestModel();
        authData.setUserName(login);
        authData.setPassword(password);

        LoginResponseModel response = step("Авторизация", () ->
                given(authRequestSpec)
                        .body(authData)
                        .when()
                        .post(loginEP)
                        .then()
                        .spec(authResponseSpec(200))
                        .extract().as(LoginResponseModel.class)
        );
        userId = response.getUserId();
        token = response.getToken();
        expires = response.getExpires();
    }*/

    @DisplayName("Успешное удаление книги из корзины")
    @WithLogin
    @Test
    public void successDeleteBookTest() {
        ProfilePage profilePage = new ProfilePage();
        //FaviconPage faviconPage = new FaviconPage();

        IsbnModel isbn = new IsbnModel();
        isbn.setIsbn(bookISBN);
        List<IsbnModel> isbns = List.of(isbn);

        AddBookRequestModel addBookData = new AddBookRequestModel();
        addBookData.setUserId(userId);
        addBookData.setCollectionOfIsbns(isbns);

        DeleteBookRequestModel deleteBookData = new DeleteBookRequestModel();
        deleteBookData.setUserId(userId);
        deleteBookData.setIsbn(bookISBN);

        step("Удаление всех имеющихся в корзине книг", () ->
                deleteAllBooks());

        step("Добавление книги в корзину", () ->
                addBook(addBookData));

        step("Удаление добавленной книги из корзины", () ->
                deleteBook(deleteBookData));

        /*faviconPage.openPage()
                .setCookie(userId, expires, token);*/
        profilePage.openPage()
                .checkEmptyTableWithoutBooks();
    }
}
