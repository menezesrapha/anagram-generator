package com.anagram.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	public Properties loadProperties() {
        Properties props = new Properties();
        
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("config.properties")) {
            
            if (input == null) {
                System.out.println("Arquivo config.properties não encontrado!");
                return props;
            }
            
            props.load(input);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return props;
    }
}
