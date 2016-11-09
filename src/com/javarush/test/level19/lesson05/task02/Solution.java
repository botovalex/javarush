package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileName = br.readLine();
        br.close();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int countOfWorlds = 0;
        while (reader.ready()) {
            String[] buffer = reader.readLine().replaceAll("\\p{Punct}", " ").split(" ");
            for (String s : buffer) {
                if (s.equals("world"))
                    countOfWorlds++;
            }
        }
        System.out.println(countOfWorlds);
        reader.close();
    }
}


/*
world,world world.11world,
world worldworld ,world,!world
6
 */