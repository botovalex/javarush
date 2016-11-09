package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileReader;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader(args[0]);
        float countAll = 0;
        float countSpace = 0;

        while (reader.ready()) {
            if (reader.read() == ' ') countSpace++;
            countAll++;
        }
        float var = countSpace / countAll * 100;
        System.out.println(String.format(Locale.ENGLISH, "%.2f", var));

        reader.close();
    }
}
