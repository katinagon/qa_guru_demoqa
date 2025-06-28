package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponseModel {
    String username, password, userId, token, expires, isActive;

    @JsonProperty("created_date")
    String createdDate;
}
