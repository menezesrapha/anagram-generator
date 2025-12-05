package com.anagram.validator;

import java.util.Properties;

import com.anagram.config.ConfigReader;

public class InputValidator {
    
    private static ConfigReader configReader = new ConfigReader();
    private static Properties props = configReader.loadProperties();
    private static Integer requestConfig = Integer.valueOf(props.getProperty("request.limit"));
    /**
     * Validates the input string for anagram generation.
     * 
     * @param input the string to validate
     * @return true if the input is valid, false otherwise
     */
    public static boolean isValidInput(String input) {
        return input != null && !input.trim().isEmpty() && input.length() > 1 && input.length() <= requestConfig;
    }
    
    /**
     * Validates that the input contains only letters.
     * 
     * @param input the string to validate
     * @return true if the input contains only letters, false otherwise
     */
    public static boolean containsOnlyLetters(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        
        for (char c : input.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Validates that the input contains only distinct letters.
     * 
     * @param input the string to validate
     * @return true if all letters in the input are distinct, false otherwise
     */
    public static boolean hasDistinctLetters(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        
        boolean[] seen = new boolean[256];
        
        for (char c : input.toCharArray()) {
            if (seen[c]) {
                return false;
            }
            seen[c] = true;
        }
        return true;
    }
    
    /**
     * Comprehensive validation method that checks all requirements.
     * 
     * @param input the string to validate
     * @throws IllegalArgumentException if the input is invalid
     */
    public static void validateInput(String input) {
        if (!isValidInput(input)) {
            throw new IllegalArgumentException("A entrada tem que ter mais de uma letra, ser menor ou igual a " + requestConfig + " e não pode ser nula ou vazia");
        }
        
        if (!containsOnlyLetters(input)) {
            throw new IllegalArgumentException("A entrada deve conter apenas letras (a-z, A-Z)");
        }
        
        if (!hasDistinctLetters(input)) {
            throw new IllegalArgumentException("Todas as letras na entrada devem ser distintas");
        }
    }
}