package com.anagram.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.anagram.service.AnagramGeneratorService;
import com.anagram.validator.InputValidator;

public class AnagramGeneratorServiceImpl implements AnagramGeneratorService {
    
    /**
     * Generates all possible anagrams (permutations) of the input string.
     * The input must contain only distinct letters.
     * 
     * @param input the string containing distinct letters to generate anagrams from
     * @return a list containing all possible anagrams of the input string
     * @throws IllegalArgumentException if the input is invalid (null, empty,
     *         contains non-letters, or has duplicate characters)
     */
    public List<String> generateAnagrams(String input) {
        InputValidator.validateInput(input);
        
        List<String> result = new ArrayList<>();
        char[] characters = input.toCharArray();
        
        backtrack(characters, 0, result);
        
        return result;
    }
    
    /**
     * Convenience method that returns anagrams as a comma-separated string.
     * 
     * @param input the string containing distinct letters
     * @return a comma-separated string of all anagrams
     */
    public String generateAnagramsAsString(String input) {
        List<String> anagrams = generateAnagrams(input);
        return String.join(", ", anagrams);
    }
    
    /**
     * Recursive helper method that generates permutations using backtracking.
     * 
     * @param chars the character array to permute
     * @param index the current index being processed
     * @param result the list to store generated permutations
     */
    private void backtrack(char[] chars, int index, List<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }
        
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            
            backtrack(chars, index + 1, result);
            
            swap(chars, index, i);
        }
    }
    
    /**
     * Utility method to swap two characters in a character array.
     * 
     * @param chars the character array
     * @param i the index of the first character
     * @param j the index of the second character
     */
    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}