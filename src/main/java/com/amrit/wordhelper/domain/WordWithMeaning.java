package com.amrit.wordhelper.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "WORD_COLLECTION")
public class WordWithMeaning {

    public WordWithMeaning(String id) {
        this.id = id;
    }

    @Id
    @Indexed(unique = true)
    private String id;

    private String meaningText;

    private String html;

    private List<String> synonyms;
}
