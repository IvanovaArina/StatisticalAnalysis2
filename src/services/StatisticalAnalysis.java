package services;

import java.io.*;
import java.util.*;

public class StatisticalAnalysis {
    //в статистическом анализе сначала надо сделать статитстику вхождения каждого символа в текст в заданном файле
    public static void countCharacters(File file) {
        try {
            // Создаем BufferedReader для чтения файла
            BufferedReader reader = new BufferedReader(new FileReader(file));

            // Создаем Map для хранения статистики символов
            Map<Character, Integer> charCountMap = new HashMap<>();

            int currentChar;
            while ((currentChar = reader.read()) != -1) {
                char charValue = (char) currentChar;
                if (charValue != '\n')
                // Обновляем статистику
                charCountMap.put(charValue, charCountMap.getOrDefault(charValue, 0) + 1);
            }

            // Закрываем BufferedReader после использования
            reader.close();

            // Конвертируем Map в список
            List<Map.Entry<Character, Integer>> list = new ArrayList<>(charCountMap.entrySet());

            // Сортируем список по убыванию значений
            list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            // Выводим отсортированный список с номерами
            for (int i = 0; i < list.size(); i++) {
                Map.Entry<Character, Integer> entry = list.get(i);
                System.out.println((i + 1) + ". " + entry.getKey() + " " + entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
