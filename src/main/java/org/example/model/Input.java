package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Input {
    private String startWord;
    private String endWord;
    private List<String> wordList;

}
