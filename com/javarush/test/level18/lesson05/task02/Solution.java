package com.javarush.test.level18.lesson05.task02;

/* Подсчет запятых
С консоли считать имя файла
Посчитать в файле количество символов ',', количество вывести на консоль
Закрыть потоки. Не использовать try-with-resources

Подсказка: нужно сравнивать с ascii-кодом символа ','
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(streamReader);
        FileInputStream inputStream = new FileInputStream(br.readLine());
        int comma = 0;
        while (inputStream.available() > 0) {
            if (inputStream.read() == ',') comma++;
        }
        inputStream.close();
        streamReader.close();
        br.close();
        System.out.println(comma);
    }
}
