package services;

import java.io.*;
import java.util.*;

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

            // ������� ��������������� ������ � ��������
            for (int i = 0; i < list.size(); i++) {
                Map.Entry<Character, Integer> entry = list.get(i);
                System.out.println((i + 1) + ". " + entry.getKey() + " " + entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
