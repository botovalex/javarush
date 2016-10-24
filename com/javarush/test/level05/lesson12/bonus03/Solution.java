package com.javarush.test.level05.lesson12.bonus03;

import java.io.*;
import java.util.ArrayList;

/* Задача по алгоритмам
Написать программу, которая:
1. вводит с консоли число N > 0
2. потом вводит N чисел с консоли
3. выводит на экран максимальное из введенных N чисел.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int maxLines = Integer.parseInt(reader.readLine());
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < maxLines; i++) {
            numbers.add(Integer.parseInt(reader.readLine()));
        }
        int maximum = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            maximum = max(maximum, numbers.get(i));
        }

        System.out.println(maximum);
    }

    public static int max(int a, int b) { return (a > b) ? a : b; }
}
