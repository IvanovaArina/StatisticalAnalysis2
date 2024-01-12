package services;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StatisticalAnalysis {
    //� �������������� ������� ������� ���� ������� ����������� ��������� ������� ������� � ����� � �������� �����
    public static void countCharacters(File file) {
        try {
            // ������� BufferedReader ��� ������ �����
            BufferedReader reader = new BufferedReader(new FileReader(file));

            // ������� Map ��� �������� ���������� ��������
            Map<Character, Integer> charCountMap = new HashMap<>();

            int currentChar;
            while ((currentChar = reader.read()) != -1) {
                char charValue = (char) currentChar;

                // ��������� ����������
                charCountMap.put(charValue, charCountMap.getOrDefault(charValue, 0) + 1);
            }

            // ��������� BufferedReader ����� �������������
            reader.close();

            // ������� ����������
            for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
                System.out.println("������ '" + entry.getKey() + "': " + entry.getValue() + " ���");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
