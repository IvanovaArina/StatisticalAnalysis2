import java.io.*;
import java.util.Map;
import java.util.Scanner;

import static services.EncodeForStatisticalAnalysis.convertFileToLowerCase;
import static services.EncodeForStatisticalAnalysis.encodeForStatisticalAnalysis;
import static services.StatisticalAnalysis.*;


public class Main {
    public static void main(String[] args) {
        //File file = new File("C:\\Users\\arina\\IdeaProjects\\StatisticalAnalysis2\\src\\dictionary.txt");
        File input = new File("C:\\Users\\arina\\IdeaProjects\\StatisticalAnalysis2\\src\\text.txt");
        File encoded = new File("C:\\Users\\arina\\IdeaProjects\\StatisticalAnalysis2\\src\\encodedForStatisticalAnalysis.txt");
        File dictionary = new File("C:\\Users\\arina\\IdeaProjects\\StatisticalAnalysis2\\src\\dictionary.txt");
        File decoded = new File("C:\\Users\\arina\\IdeaProjects\\StatisticalAnalysis2\\src\\output.txt");
        convertFileToLowerCase(dictionary, dictionary);
        encodeForStatisticalAnalysis(input, encoded, 3);

        System.out.println("Encoded file : ");
        Map<Character, Integer> encodedStatistics = countCharactersInEncodedFile(encoded);
        System.out.println("Dictionary : ");
        Map<Character, Integer> dictionaryStatistics = countCharactersInDecodedFile(encodedStatistics, dictionary);
        System.out.println("  ");
        makeMapsEqual(dictionaryStatistics, encodedStatistics);
        replaceCharactersFromStatistics(encoded, decoded, encodedStatistics,dictionaryStatistics);
//        swapCharactersInFile(decoded, 'n', 'i');
//        swapCharactersInFile(decoded, 'y', '1');
//        swapCharactersInFile(decoded, 'o', 'a');
//        swapCharactersInFile(decoded, 'i', 'o');
//        swapCharactersInFile(decoded, 'm', 'u');
        System.out.println("Enter the characters you want to swap or enter 'exit' to quit:");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Source Character: ");
            String sourceInput = scanner.nextLine();

            if (sourceInput.equalsIgnoreCase("exit")) {
                break;
            }

            if (sourceInput.length() != 1) {
                System.out.println("Invalid input. Please enter a single character.");
                continue;
            }

            char sourceChar = sourceInput.charAt(0);

            System.out.print("Target Character: ");
            String targetInput = scanner.nextLine();

            if (targetInput.equalsIgnoreCase("exit")) {
                break;
            }
            if (targetInput.length() != 1) {
                System.out.println("Invalid input. Please enter a single character.");
                continue;
            }

            char targetChar = targetInput.charAt(0);

            swapCharactersInFile(decoded, sourceChar, targetChar);
            System.out.println("Characters swapped successfully.");
        }

    }
}
