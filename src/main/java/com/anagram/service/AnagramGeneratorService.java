package com.anagram.service;

import java.util.List;

public interface AnagramGeneratorService {

    public String generateAnagramsAsString(String input);
    
    public List<String> generateAnagrams(String input);
}