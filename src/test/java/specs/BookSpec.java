package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class BookSpec {
    public static RequestSpecification bookRequestSpec(String token) {
        return with()
                .filter(withCustomTemplates())
                .log().uri()
                .log().method()
                .log().body()
                .header("Authorization", "Bearer " + token)
                .log().headers()
                .contentType(JSON);
    }

    public static ResponseSpecification bookResponseSpec(int expectedStatusCode) {
        return new ResponseSpecBuilder()
                .log(STATUS)
                .log(BODY)
                .expectStatusCode(expectedStatusCode)
                .build();
    }
}
