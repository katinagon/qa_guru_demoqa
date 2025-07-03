package api;

import models.LoginRequestModel;
import models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static specs.LoginSpec.authRequestSpec;
import static specs.LoginSpec.authResponseSpec;
import static config.BaseConfig.loginEP;
import static tests.TestData.login;
import static tests.TestData.password;

public class AccountApiRequests {

    public static LoginResponseModel loginRequest() {
        LoginRequestModel authData = new LoginRequestModel(login, password);

        return given(authRequestSpec)
                .body(authData)
                .when()
                .post(loginEP)
                .then()
                .spec(authResponseSpec(200))
                .extract().as(LoginResponseModel.class);
    }
}
