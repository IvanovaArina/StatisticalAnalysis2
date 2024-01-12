import java.io.File;

import static services.EncodeForStatisticalAnalysis.convertFileToLowerCase;
import static services.EncodeForStatisticalAnalysis.encodeForStatisticalAnalysis;
import static services.StatisticalAnalysis.countCharacters;


public class Main {
    public static void main(String[] args) {
        //File file = new File("C:\\Users\\arina\\IdeaProjects\\StatisticalAnalysis2\\src\\dictionary.txt");
        File input = new File("C:\\Users\\arina\\IdeaProjects\\StatisticalAnalysis2\\src\\text.txt");
        File encoded = new File("C:\\Users\\arina\\IdeaProjects\\StatisticalAnalysis2\\src\\encodedForStatisticalAnalysis.txt");
        File dictionary = new File("C:\\Users\\arina\\IdeaProjects\\StatisticalAnalysis2\\src\\dictionary.txt");
        convertFileToLowerCase(dictionary, dictionary);
        encodeForStatisticalAnalysis(input, encoded, 3);
        System.out.println("Dictionary : ");
        countCharacters(dictionary);
        System.out.println("Encoded file : ");
        countCharacters(encoded);
        System.out.println("  ");

    }
}