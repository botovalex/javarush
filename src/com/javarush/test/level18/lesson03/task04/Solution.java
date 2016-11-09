package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байт или байты с минимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(br.readLine());
        HashMap<Integer, Integer> bytes = new HashMap<Integer, Integer>();
        while (inputStream.available() > 0) {
            int data = inputStream.read();
            if (bytes.containsKey(data))
                bytes.put(data, bytes.get(data) + 1);
            else bytes.put(data, 1);
        }
        inputStream.close();

        int min = 2147483647;
        for (Integer value : bytes.values()) {
            if (value < min) min = value;
        }



        for (Integer key : bytes.keySet()) {
            if (bytes.get(key) == min)
                System.out.print(key + " ");
        }
    }
}

//  System.out.println(bytes);
//  d:\1.txt