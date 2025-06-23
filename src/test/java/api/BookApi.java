package api;

import models.AddBookRequestModel;
import models.DeleteBookRequestModel;

import static io.restassured.RestAssured.given;
import static specs.BookSpec.bookRequestSpec;
import static specs.BookSpec.bookResponseSpec;
import static tests.TestBase.bookEP;
import static tests.TestBase.booksEP;
import static tests.TestData.token;
import static tests.TestData.userId;

public class BookApi {
    public static void deleteAllBooks() {
        given(bookRequestSpec(token))
                .when()
                .delete(booksEP + "?UserId=" + userId)
                .then()
                .spec(bookResponseSpec(204));
    }

    public static void addBook(AddBookRequestModel addBookData) {
        given(bookRequestSpec(token))
                .body(addBookData)
                .when()
                .post(booksEP)
                .then()
                .spec(bookResponseSpec(201));
    }

    public static void deleteBook(DeleteBookRequestModel deleteBookData) {
        given(bookRequestSpec(token))
                .body(deleteBookData)
                .when()
                .delete(bookEP)
                .then()
                .spec(bookResponseSpec(204));
    }
}
