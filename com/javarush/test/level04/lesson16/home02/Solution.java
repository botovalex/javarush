package com.javarush.test.level04.lesson16.home02;

import java.io.*;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = new int[3];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = Integer.parseInt(br.readLine());
        if (numbers[0] < numbers[1] && numbers[1] < numbers[2] || numbers[0] > numbers[1] && numbers[1] > numbers[2])
            System.out.println(numbers[1]);
        else if (numbers[1] < numbers[2] && numbers[2] < numbers[0] || numbers[1] > numbers[2] && numbers[2] > numbers[0])
            System.out.println(numbers[2]);
        else if (numbers[2] < numbers[0] && numbers[0] < numbers[1] || numbers[2] > numbers[0] && numbers[0] > numbers[1])
            System.out.println(numbers[0]);

    }
/*
    public static int maxOrMinOfArray(int[] num, boolean isMax)
    {
        int maxOrMin = num[0];
        for (int i = 1; i < num.length; i++) {
            if (isMax){
                if (num[i] > maxOrMin) maxOrMin = num[i];
            }
            else if (num[i] < maxOrMin) maxOrMin = num[i];

        }
        return maxOrMin;
    }
*/
}
