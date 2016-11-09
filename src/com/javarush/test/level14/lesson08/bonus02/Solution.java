package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        br.close();

        ArrayList<Integer> list = new ArrayList<Integer>();
        if (a >= b) {
            list.add(a);
            list.add(b);
        } else {
            list.add(b);
            list.add(a);
        }

        while (true) {
            int nextArg = list.get(list.size() - 2) % list.get(list.size() - 1);
            if (nextArg == 0) break;
            list.add(nextArg);
        }

        System.out.println(list.get(list.size() - 1));
    }
}
