package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException{
        //add your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        br.close();

        line = line.substring(line.indexOf('?') + 1);
        String[] lineArray = line.split("&");
        ArrayList<String> list = new ArrayList<String>();
        for (String s : lineArray) {
            if (s.startsWith("obj="))
                list.add(s.substring(s.indexOf('=') + 1));
            if (!s.isEmpty()) {
                if (s.contains("="))
                    System.out.print(s.substring(0, s.indexOf('=')) + " ");
                else System.out.print(s + " ");
            }

        }
        System.out.println();

        for (String s : list) {
            try {
                alert(Double.parseDouble(s));
            } catch (Exception e) {
                alert(s);
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
