package services;

import java.io.*;



import static constants.CryptoAlphabet.ALPHABET_FOR_STATISTICAL_ANALYSIS;
import static services.FileIO.*;


public class EncodeForStatisticalAnalysis {


    public static void convertFileToLowerCase(File inputFile, File outputFile) {
        try {
            String content = readTextFromFile(inputFile);
            String convertedContent = content.toLowerCase();
            rewriteTextToFile(outputFile, convertedContent);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static char encryptChar(char ch, int key) {
        int index = ALPHABET_FOR_STATISTICAL_ANALYSIS.indexOf(ch);
        if (index != -1) {
            int newIndex = (index + key) % ALPHABET_FOR_STATISTICAL_ANALYSIS.length();
            return ALPHABET_FOR_STATISTICAL_ANALYSIS.charAt(newIndex);
        } else {
            return ch; // Символ не найден в алфавите, оставляем его без изменений
        }
    }

    public static void encodeForStatisticalAnalysis(File inputFile, File outputFile, int key) {
        convertFileToLowerCase(inputFile, outputFile);
        try {
            String content = readTextFromFile(outputFile);
            StringBuilder encryptedText = new StringBuilder();

            for (char character : content.toCharArray()) {
                encryptedText.append(encryptChar(character, key));
            }
            rewriteTextToFile(outputFile, encryptedText.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
