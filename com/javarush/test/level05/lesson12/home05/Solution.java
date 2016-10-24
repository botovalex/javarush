package com.javarush.test.level05.lesson12.home05;

/* Вводить с клавиатуры числа и считать их сумму
Вводить с клавиатуры числа и считать их сумму, пока пользователь не введёт слово «сумма». Вывести на экран полученную сумму.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inpute = "";
        int summ = 0;

        while (true) {
            inpute = br.readLine();
            if (inpute.equals("сумма")) break;
            summ += Integer.parseInt(inpute);
        }
        System.out.println(summ);


    }
}
