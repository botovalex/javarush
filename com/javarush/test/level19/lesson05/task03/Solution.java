package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;
import java.util.HashSet;

public class Solution {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(br.readLine())));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(br.readLine())));
        br.close();

        while (reader.ready()) {
            String[] line = reader.readLine().split(" ");
            for (String s : line) {
                char[] chars = s.toCharArray();
                boolean isNumber = true;
                for (char aChar : chars) {
                    if (!Character.isDigit(aChar))
                        isNumber = false;
                }
                if (isNumber) {
                    writer.write(s + " ");
                }
            }
        }

        reader.close();
        writer.close();


    }
}

/*
d:\1.txt
d:\2.txt
 */
//  12 text var2 14 8v 1