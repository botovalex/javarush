package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader(args[0]);
        int count = 0;
        while (reader.ready()) {
            int c = reader.read();
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
                count++;
        }
        System.out.println(count);
        reader.close();
    }
}
