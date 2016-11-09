package com.javarush.test.level18.lesson03.task05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;




/* Сортировка байт
Ввести с консоли имя файла
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран
Закрыть поток ввода-вывода

Пример байт входного файла
44 83 44

Пример вывода
44 83
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(br.readLine());
        HashSet<Integer> bytes = new HashSet<Integer>();
        while (inputStream.available() > 0) {
            bytes.add(inputStream.read());
        }
        inputStream.close();
        int[] arrayOfBytes = new int[bytes.size()];
        Iterator<Integer> iter = bytes.iterator();
        for (int i = 0; i < bytes.size(); i++) {
            arrayOfBytes[i] = iter.next();
        }
        Arrays.sort(arrayOfBytes);
        for (int aByte : arrayOfBytes) {
            System.out.print(aByte + " ");
        }
    }
}

//  d:\1.txt