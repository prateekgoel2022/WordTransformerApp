package org.example.util;

import org.example.model.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordTransformerUtil {

    public static Input readWordList(String fileName){


        Input input = null;
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> inputList = stream.collect(Collectors.toList());
            String[] names = inputList.get(0).split(",");
            String commaSeparated= inputList.get(1);
            List<String> wordList = Stream.of(commaSeparated.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
            input = new Input(names[0].trim(), names[1].trim(),wordList);
            return input;
        } catch (IOException e) {
            throw new RuntimeException("File Not Found");
        }

        //return input;
    }
}
