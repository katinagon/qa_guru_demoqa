package models;

import lombok.Data;

@Data
public class DeleteBookRequestModel {
    String userId, isbn;
}
