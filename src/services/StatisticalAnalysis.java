package services;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

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

                // Обновляем статистику
                charCountMap.put(charValue, charCountMap.getOrDefault(charValue, 0) + 1);
            }

            // Закрываем BufferedReader после использования
            reader.close();

            // Выводим статистику
            for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
                System.out.println("Символ '" + entry.getKey() + "': " + entry.getValue() + " раз");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
