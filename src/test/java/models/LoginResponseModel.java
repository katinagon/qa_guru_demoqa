package models;

import lombok.Data;

@Data
public class LoginResponseModel {
    String username, password, userId, token, expires, created_date, isActive;
}
