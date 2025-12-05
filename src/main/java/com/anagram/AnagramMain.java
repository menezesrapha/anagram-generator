package com.anagram;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.anagram.config.ConfigReader;
import com.anagram.service.AnagramGeneratorService;
import com.anagram.service.impl.AnagramGeneratorServiceImpl;

public class AnagramMain {
    
    public static void main(String[] args) {
        AnagramGeneratorService generator = new AnagramGeneratorServiceImpl();
        ConfigReader configReader = new ConfigReader();
        Properties props = configReader.loadProperties();
        Integer resultConfig = Integer.valueOf(props.getProperty("result.limit"));
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Gerador de Anagramas ===" + resultConfig);
        System.out.println("Digite 'sair' para encerrar");
        System.out.println();

        System.out.print("Informe as letras distintas: ");
        String input = scanner.nextLine().trim();

        while (!input.equalsIgnoreCase("sair")) {

            try {
                System.out.println("\nGerando anagramas para: " + input);

                String result = generator.generateAnagramsAsString(input);

                if (result.length() > resultConfig) {
                	List<String> lista = Arrays.asList(result.split(", "));
                    System.out.println("Primeiros " + resultConfig + " anagramas:");
                    for (int i = 0; i < Math.min(resultConfig, lista.size()); i++) {
                        System.out.println("  " + lista.get(i));
                    }
                    System.out.println("... e mais " + (lista.size() - 5) + " anagramas");
                } else {
                    System.out.println("Anagramas: " + result);
                }
                
            } catch (IllegalArgumentException e) {
                System.out.println("Entrada inválida: " + e.getMessage());
                System.out.println("Dica: Use apenas letras distintas (ex: abc, xyz, CBA)");
            }

            System.out.println("\n" + "-".repeat(40));

            System.out.print("Informe as letras distintas: ");
            input = scanner.nextLine().trim();
        }

        System.out.println("Programa encerrado.");
        scanner.close();
    }
}
