package tests;

import helpers.WithLogin;
import io.qameta.allure.Owner;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import java.util.List;

import static api.BookApi.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static tests.TestData.*;

@Owner("goncharova-ek")
@Tag("delete_books")
@DisplayName("Тесты на удаление книг")
public class DeleteBookTests extends TestBase {

    @DisplayName("Успешное удаление книги из корзины")
    @WithLogin
    @Test
    public void successDeleteBookTest() {
        ProfilePage profilePage = new ProfilePage();

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

        profilePage.openPage()
                .checkEmptyTableWithoutBooks();
    }
}
