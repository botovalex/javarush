package com.javarush.test.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    //private static int[] num = new int[5];

    public static void main(String[] args) throws Exception
    {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
        int[] num = new int[5];
        for (int i = 0; i < num.length; i++) {
            num[i] = Integer.parseInt(reader.readLine());
        }
        //bubbleSortInc(num);
/*
        int temp = num[0];
        boolean isChange = true;
        while (isChange) {
            for (int i = 0; i < num.length - 1; i++) {
                isChange = false;
                for (int j = i + 1; j < num.length; j++) {
                    if (num[j] < num[j - 1]) {
                        temp = num[j - 1];
                        num[j - 1] = num[j];
                        num[j] = temp;
                        isChange = true;
                    }
                }
            }
        }
*/
        for (int x : num) {
            System.out.println(x);
        }
    }

    public void bubbleSortInc (int[] array) {
        int temp;
        for (int i = array.length-1; i > 0; i--)
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j+1]) {
                    temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
    }

}
