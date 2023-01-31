package org.example;

import org.example.model.Input;
import org.example.util.WordTransformerUtil;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class WordTransformerTest {

    static Input correctInput;
    static Input incorrectInput;

    @org.junit.jupiter.api.BeforeAll
    static void setUp() {
        correctInput = WordTransformerUtil.readWordList("src/test/resources/WordTransformer.txt");
        incorrectInput = WordTransformerUtil.readWordList("src/test/resources/WordTransformerError.txt");

    }

    @org.junit.jupiter.api.AfterAll
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void findTransformationsForSuccessfulTransformation() {
        WordTransformer wordTransformer = new WordTransformer();
        String actualOutput = wordTransformer.findTransformations(correctInput.getStartWord(), correctInput.getEndWord(), correctInput.getWordList());
        assertEquals("cat,cot,dot,dog",actualOutput);
    }

    @org.junit.jupiter.api.Test
    void findTransformationsForUnsuccessfulTransformation() {
        WordTransformer wordTransformer = new WordTransformer();
        String actualOutput = wordTransformer.findTransformations(incorrectInput.getStartWord(), incorrectInput.getEndWord(), incorrectInput.getWordList());
        assertEquals("Cannot transform cat to pig",actualOutput);
    }


    @org.junit.jupiter.api.Test
    void findTransformationsForFileNotFound() {
        assertThrows(RuntimeException.class,
                () ->WordTransformerUtil.readWordList("WorldTransformerNotFound.txt"));

    }
}