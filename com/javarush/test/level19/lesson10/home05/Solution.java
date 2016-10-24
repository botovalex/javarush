package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со слов, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));

        while (reader.ready()) {
            String[] buffer = reader.readLine().split(" ");
            for (String s : buffer) {
                char[] temp = s.toCharArray();
                boolean isContainsADigit = false;
                for (char c : temp)
                    if (Character.isDigit(c))
                        isContainsADigit = true;
                if (isContainsADigit) {
                    writer.write(s);
                    writer.write(" ");
                }
            }
        }
        reader.close();
        writer.close();
    }
}
