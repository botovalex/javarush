package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Solution
{
    public static void main(String[] args) throws IOException
    {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(consoleReader.readLine())));
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        String s;
        while ((s = fileReader.readLine()) != null) {
            Integer n = Integer.parseInt(s);
            if (n%2 == 0) numbers.add(n);
        }


        Integer[] numArray = numbers.toArray(new Integer[numbers.size()]);
        Arrays.sort(numArray);
        for (Integer i : numArray) {
            System.out.println(i);
        }

        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d:\\1.txt")));
        ArrayList<String> list = new ArrayList<String>();
        String line;
        while ((line = br.readLine()) != null)
            list.add(line);
        for (String s : list) {
            System.out.println(s);
        }
        */
    }
}
