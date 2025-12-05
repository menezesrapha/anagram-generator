package com.anagram.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.anagram.service.impl.AnagramGeneratorServiceImpl;
import com.anagram.validator.InputValidator;

class AnagramGeneratorServiceTest {
    
    private AnagramGeneratorService generator;
    
    @BeforeEach
    void setUp() {
        generator = new AnagramGeneratorServiceImpl();
    }
    
    @Test
    @DisplayName("Test with three distinct letters 'abc'")
    void testThreeLetterAnagram() {
        String input = "abc";
        
        List<String> result = generator.generateAnagrams(input);
        
        assertEquals(6, result.size(), "Should generate 6 anagrams for 3 distinct letters");
        assertTrue(result.containsAll(Arrays.asList("abc", "acb", "bac", "bca", "cab", "cba")),
                   "Should contain all permutations of 'abc'");
    }
    
    @Test
    @DisplayName("Test validation for single letter 'x'")
    void testSingleLetter() {
        String input = "x";
        
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> generator.generateAnagrams(input),
                "Should throw IllegalArgumentException for empty input"
            );
        
        assertTrue(exception.getMessage().contains("tem que ter mais de uma letra"));
    }
    
    @Test
    @DisplayName("Test validation for empty string")
    void testEmptyString() {
        String input = "";
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> generator.generateAnagrams(input),
            "Should throw IllegalArgumentException for empty input"
        );
        
        assertTrue(exception.getMessage().contains("não pode ser nula ou vazia"));
    }
    
    @Test
    @DisplayName("Test validation for null input")
    void testNullInput() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> generator.generateAnagrams(null),
            "Should throw IllegalArgumentException for null input"
        );
        
        assertTrue(exception.getMessage().contains("não pode ser nula ou vazia"));
    }
    
    @Test
    @DisplayName("Test validation for input with non-letter characters")
    void testNonLetterInput() {
        String input = "ab1c";
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> generator.generateAnagrams(input),
            "Should throw IllegalArgumentException for non-letter input"
        );
        
        assertTrue(exception.getMessage().contains("deve conter apenas letras"));
    }
    
    @Test
    @DisplayName("Test validation for input with duplicate letters")
    void testDuplicateLettersInput() {
        String input = "aab";
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> generator.generateAnagrams(input),
            "Should throw IllegalArgumentException for duplicate letters"
        );
        
        assertTrue(exception.getMessage().contains("devem ser distintas"));
    }
    
    @Test
    @DisplayName("Test InputValidator methods")
    void testInputValidator() {
    	assertFalse(InputValidator.isValidInput("a"));
    	assertFalse(InputValidator.isValidInput(null));
        assertFalse(InputValidator.isValidInput(""));
        assertFalse(InputValidator.isValidInput("   "));
        assertTrue(InputValidator.isValidInput("abc"));
        
        assertFalse(InputValidator.containsOnlyLetters("ab1c"));
        assertFalse(InputValidator.containsOnlyLetters("abc!"));
        assertFalse(InputValidator.containsOnlyLetters(""));
        assertTrue(InputValidator.containsOnlyLetters("abc"));
        assertTrue(InputValidator.containsOnlyLetters("ABC"));
        assertTrue(InputValidator.containsOnlyLetters("AbCd"));
        
        assertFalse(InputValidator.hasDistinctLetters("aa"));
        assertFalse(InputValidator.hasDistinctLetters("aba"));
        assertTrue(InputValidator.hasDistinctLetters("abc"));
        assertTrue(InputValidator.hasDistinctLetters("ABCD"));
    }
    
    @Test
    @DisplayName("Test that anagrams maintain original character case")
    void testCaseSensitivity() {
        String input = "AbC";
        
        List<String> result = generator.generateAnagrams(input);
        
        assertTrue(result.contains("AbC"));
        assertTrue(result.contains("ACb"));
        assertTrue(result.contains("bAC"));
     
        result.forEach(anagram -> {
            assertEquals(3, anagram.length());
            assertTrue(anagram.contains("A") || anagram.contains("b") || anagram.contains("C"));
        });
    }
}