package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class AddBookRequestModel {
    private String userId;
    private List<IsbnModel> collectionOfIsbns;
}

