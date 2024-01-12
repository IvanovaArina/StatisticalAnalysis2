package constants;

public class CryptoAlphabet {
    private static final String lettersUpperCase = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
    private static final String lettersLowerCase = "abcdefghijklmnopqrstuvwxyz";
    private static final String numbers = "0123456789";
    private static final String symbols = ".,\"\';:-!?—”“ ";
    public static final String ALPHABET = lettersUpperCase + lettersLowerCase + numbers + symbols;
    public static final String ALPHABET_FOR_STATISTICAL_ANALYSIS = lettersLowerCase + numbers + symbols;
    public static final int lengthOfAlphabet = CryptoAlphabet.ALPHABET.length();
    public static final int lengthOfAlphabetForStatisticalAnalysis = CryptoAlphabet.ALPHABET_FOR_STATISTICAL_ANALYSIS.length();
}
