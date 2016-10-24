package com.javarush.test.level08.lesson11.home09;

/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Не забудьте учесть первый день года.
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

import java.util.Date;

public class Solution
{
    public static void main(String[] args) {
        System.out.println(isDateOdd("JANUARY 1 2000"));
        System.out.println(isDateOdd("MARCH 1 2001"));
        System.out.println(isDateOdd("MARCH 1 2000"));
    }

    public static boolean isDateOdd(String date) {

        Date currentDate = new Date(date);
        Date yearStartTime = new Date(date);
        yearStartTime.setMonth(0);
        yearStartTime.setDate(1);
        long difTime = currentDate.getTime() - yearStartTime.getTime();
        difTime /= 1000 * 60 * 60 * 24;
        if (difTime == 0) return true;
        return difTime % 2 == 0;



        /*
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(date));
        int temp = Calendar.DAY_OF_YEAR;
        boolean isOdd = temp % 2 == 0;
        return isOdd;
        */
    }
}
