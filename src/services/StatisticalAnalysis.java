package services;

import java.io.*;
import java.util.*;
import java.util.Map;
import java.util.Scanner;


public class StatisticalAnalysis {
    //� �������������� ������� ������� ���� ������� ����������� ��������� ������� ������� � ����� � �������� �����
    private static Map<Character, Integer> countCharactersInEncodedFile(File file) {
        // ������� LinkedHashMap, ������� ��������� ������� �������
        Map<Character, Integer> sortedCharCountMap = new LinkedHashMap<>();

        try {
            // ������� BufferedReader ��� ������ �����
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int currentChar;
            // ������� Map ��� �������� ���������� ��������
            Map<Character, Integer> charCountMap = new HashMap<>();
            while ((currentChar = reader.read()) != -1) {
                char charValue = (char) currentChar;
                if (charValue != '\n')
                    // ��������� ����������
                    charCountMap.put(charValue, charCountMap.getOrDefault(charValue, 0) + 1);
            }

            // ��������� BufferedReader ����� �������������
            reader.close();

            // ������������ Map � ������
            List<Map.Entry<Character, Integer>> list = new ArrayList<>(charCountMap.entrySet());

            // ��������� ������ �� �������� ��������
            list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            // ��������� ��������������� �������� � LinkedHashMap
            for (Map.Entry<Character, Integer> entry : list) {
                sortedCharCountMap.put(entry.getKey(), entry.getValue());
            }


            // ���������� ��������������� Map
            return sortedCharCountMap;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<Character, Integer> countCharactersInDecodedFile(Map<Character, Integer> StatisticsInEncodedFile, File file) {
        // ������� LinkedHashMap, ������� ��������� ������� �������
        Map<Character, Integer> sortedCharCountMap = new LinkedHashMap<>();

        try {
            // ������� BufferedReader ��� ������ �����
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int currentChar;
            // ������� Map ��� �������� ���������� ��������
            Map<Character, Integer> charCountMap = new HashMap<>();
            while ((currentChar = reader.read()) != -1) {
                char charValue = (char) currentChar;
                if (charValue != '\n' && StatisticsInEncodedFile.containsKey(charValue))
                    // ��������� ����������
                    charCountMap.put(charValue, charCountMap.getOrDefault(charValue, 0) + 1);
            }

            // ��������� BufferedReader ����� �������������
            reader.close();

            // ������������ Map � ������
            List<Map.Entry<Character, Integer>> list = new ArrayList<>(charCountMap.entrySet());

            // ��������� ������ �� �������� ��������
            list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            // ��������� ��������������� �������� � LinkedHashMap
            for (Map.Entry<Character, Integer> entry : list) {
                sortedCharCountMap.put(entry.getKey(), entry.getValue());
            }
//            // ������� ��������������� ������ � ��������
//            for (int i = 0; i < list.size(); i++) {
//                Map.Entry<Character, Integer> entry = list.get(i);
//                System.out.println((i + 1) + ". " + entry.getKey() + " " + entry.getValue());
//            }

            // ���������� ��������������� Map
            return sortedCharCountMap;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void makeMapsEqual(Map<Character, Integer> dictionaryStatistics, Map<Character, Integer> encodedStatistics) {

        // ������� ����� �����
        Map<Character, Integer> commonInDictionary = new HashMap<>(dictionaryStatistics);
        Map<Character, Integer> commonInEncoded = new HashMap<>(encodedStatistics);

        // ������� �� ������ ����� ��������, ������� ����������� � ������ ����
        commonInDictionary.keySet().retainAll(encodedStatistics.keySet());
        commonInEncoded.keySet().retainAll(dictionaryStatistics.keySet());

        // ������ ��������� �������� ����
        dictionaryStatistics.keySet().retainAll(commonInDictionary.keySet());
        encodedStatistics.keySet().retainAll(commonInEncoded.keySet());
        // ������� ��� ����
        System.out.println("Dictionary Map: ");
        int i = 1;
        for (Map.Entry<Character, Integer> entry : dictionaryStatistics.entrySet()) {
            System.out.println(i + ". " + entry.getKey() + ": " + entry.getValue());
            i++;
        }
        System.out.println("Encoded Map: ");
        int l = 1;
        for (Map.Entry<Character, Integer> entry : encodedStatistics.entrySet()) {
            System.out.println(l + ". " + entry.getKey() + ": " + entry.getValue());
            l++;
        }

    }
    private static void replaceCharactersFromStatistics(File encoded, File output, Map<Character, Integer> sourceMap, Map<Character, Integer> targetMap) {
        try {
            // ������� BufferedReader ��� ������ �����
            BufferedReader reader = new BufferedReader(new FileReader(encoded));

            // ������ ����������� �����
            StringBuilder content = new StringBuilder();
            int currentChar;
            while ((currentChar = reader.read()) != -1) {
                char charValue = (char) currentChar;
                content.append(charValue);
            }

            // ��������� BufferedReader ����� �������������
            reader.close();

            char tmp = 0;
            char sourceChar = sourceMap.keySet().iterator().next();
            char targetChar = targetMap.keySet().iterator().next();
            while (!sourceMap.isEmpty() && !targetMap.isEmpty()) {

                //content = replaceCharacter(content, sourceChar, targetChar);
                if (tmp == 0) {
                    for (int i = 0; i < content.length(); i++) {
                        if (content.charAt(i) == sourceChar) {
                            content.setCharAt(i, targetChar);
                        } else if (content.charAt(i) == targetChar) {
                            content.setCharAt(i, '$');
                        }
                    }
                    tmp = targetChar;
                    sourceMap.remove(sourceChar);
                    targetMap.remove(targetChar);
                } else if (tmp != 0) {
                    //������� ������ tmp � ���������� �������
                    targetChar = findKeyByIndex(targetMap, findIndexByKey(sourceMap, tmp));
                    if (targetChar != 0) {
                        sourceChar = tmp;
                        for (int i = 0; i < content.length(); i++) {
                            if (content.charAt(i) == '$') {
                                content.setCharAt(i, targetChar);
                            } else if (content.charAt(i) == targetChar) {
                                content.setCharAt(i, '$');
                            }
                            tmp = targetChar;
                            sourceMap.remove(sourceChar);
                            targetMap.remove(targetChar);
                        }
                    } else {//���� ����� ������� ������ ��� � ���������� ��������������� ����� - ������ �� ����� ������ ������� ������, �� ����� �������� ������
                        for (int i = 0; i < content.length(); i++) {
                            if (content.charAt(i) == '$') {
                                content.setCharAt(i, tmp);
                            }

                        }
                        sourceMap.remove(sourceChar);
                        targetMap.remove(tmp);
                        sourceChar = sourceMap.keySet().iterator().next();
                        targetChar = targetMap.keySet().iterator().next();
                        tmp = 0;
                    }
                }

            }

            // ������ ��������� ������� � ����
            FileWriter fileWriter = new FileWriter(output);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content.toString());

            // ��������� BufferedWriter ����� �������������
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ������� ������ �������� �� �����
    private static int findIndexByKey(Map<Character, Integer> map, char key) {
        Set<Character> keySet = map.keySet();
        int index = 0;
        for (char mapKey : keySet) {
            if (mapKey == key) {
                return index;
            }
            index++;
        }
        return -1; // ���������� -1, ���� ���� �� ������
    }

    // ������� ���� �� �������
    private static char findKeyByIndex(Map<Character, Integer> map, int index) {
        Set<Character> keySet = map.keySet();
        int currentIndex = 0;
        for (char key : keySet) {
            if (currentIndex == index) {
                return key;
            }
            currentIndex++;
        }
        return '\0'; // ���������� '\0', ���� ������ ��������������
    }

    private static void swapCharactersInFile(File file, char one, char another) {
        try {
            // ������� BufferedReader ��� ������ �����
            BufferedReader reader = new BufferedReader(new FileReader(file));

            // ������ ����������� �����
            StringBuilder content = new StringBuilder();
            int currentChar;
            while ((currentChar = reader.read()) != -1) {
                char charValue = (char) currentChar;
                if (charValue == one) {
                    content.append(another);
                } else if (charValue == another) {
                    content.append(one);
                } else content.append(charValue);
            }

            // ��������� BufferedReader ����� �������������
            reader.close();
            // ������ ��������� ������� � ����
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content.toString());

            // ��������� BufferedWriter ����� �������������
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void statisticalAnalysis( File encoded, File decoded, File dictionary){


        System.out.println("Encoded file : ");
        Map<Character, Integer> encodedStatistics = countCharactersInEncodedFile(encoded);
        System.out.println("Dictionary : ");
        Map<Character, Integer> dictionaryStatistics = countCharactersInDecodedFile(encodedStatistics, dictionary);
        System.out.println("  ");
        makeMapsEqual(dictionaryStatistics, encodedStatistics);
        replaceCharactersFromStatistics(encoded, decoded, encodedStatistics,dictionaryStatistics);
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
