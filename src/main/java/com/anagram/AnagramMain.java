package com.anagram;

import java.util.Scanner;

import com.anagram.service.AnagramGeneratorService;
import com.anagram.service.impl.AnagramGeneratorServiceImpl;

public class AnagramMain {
    
    public static void main(String[] args) {
        AnagramGeneratorService generator = new AnagramGeneratorServiceImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Gerador de Anagramas ===");
        System.out.println("Digite 'sair' para encerrar");
        System.out.println();

        System.out.print("Informe as letras distintas: ");
        String input = scanner.nextLine().trim();

        while (!input.equalsIgnoreCase("sair")) {

            try {
                System.out.println("\nGerando anagramas para: " + input);

                String result = generator.generateAnagramsAsString(input);

                System.out.println("Anagramas: " + result);

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
