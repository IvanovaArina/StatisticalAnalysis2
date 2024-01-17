import java.io.*;
import static services.EncodeForStatisticalAnalysis.convertFileToLowerCase;
import static services.EncodeForStatisticalAnalysis.encodeForStatisticalAnalysis;
import static services.StatisticalAnalysis.*;


public class Main {
    public static void main(String[] args) {
        File input = new File("C:\\Users\\arina\\IdeaProjects\\StatisticalAnalysis2\\src\\text.txt");
        File encoded = new File("C:\\Users\\arina\\IdeaProjects\\StatisticalAnalysis2\\src\\encodedForStatisticalAnalysis.txt");
        File dictionary = new File("C:\\Users\\arina\\IdeaProjects\\StatisticalAnalysis2\\src\\dictionary.txt");
        File decoded = new File("C:\\Users\\arina\\IdeaProjects\\StatisticalAnalysis2\\src\\output.txt");
        convertFileToLowerCase(dictionary, dictionary);
        encodeForStatisticalAnalysis(input, encoded, 3);

        statisticalAnalysis(encoded,decoded,dictionary);

    }
}
