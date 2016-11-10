package com.javarush.test.level20.lesson10.bonus01;

import java.util.ArrayList;

/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static int[] getNumbers(int N) {
        int[] result = null;
        return result;
    }

    public static void main(String[] args) {

        int N = 8209;


        int[][] powers = new int[11][11];
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                powers[i][j] = (int) Math.pow(i, j);
            }
        }

        ArrayList<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            resultList.add(i);
        }

        for (int i = 134; i < N; i++) {
            int count = (int) Math.ceil(Math.log10(Math.abs(i + 0.5)));
            int temp = 0;
            int copyNumber = i;
            int firstDigit;
            int secondDigit;

            for (int j = count; j >= 1; j--) {
                firstDigit = copyNumber / powers[10][j];
                temp += powers[firstDigit][count];

                copyNumber -= firstDigit * powers[10][j - 1];

                int blabla = j <= 2 ? copyNumber : copyNumber / powers[10][j - 2];

                if (firstDigit > blabla) {
                    temp = 0;
                    break;
                }
            }

            if (i == temp) {
                resultList.add(i);
            }
        }


        for (Integer integer : resultList) {
            System.out.print(integer + " ");
        }

    }
}
