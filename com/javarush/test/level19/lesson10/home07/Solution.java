package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        RandomAccessFile writer = new RandomAccessFile(args[1], "rw");

        while (reader.ready()) {
            String[] buffer = reader.readLine().split(" ");
            for (String s : buffer) {
                if (s.length() > 6) {
                    writer.write(s.getBytes());
                    writer.writeBytes(",");
                }
            }
        }
        writer.setLength(writer.length() - 1);
        reader.close();
        writer.close();
    }
}
