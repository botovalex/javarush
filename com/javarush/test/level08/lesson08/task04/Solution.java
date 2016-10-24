package com.javarush.test.level08.lesson08.task04;

import java.util.*;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{
    public static HashMap<String, Date> createMap()
    {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", new Date("JUNE 1 1980"));
        map.put("Stallone1", new Date("SEPTEMBER 2 1990"));
        map.put("Stallone2", new Date("JANUARY 3 1977"));
        map.put("Stallone3", new Date("FEBRUARY 4 1987"));
        map.put("Stallone4", new Date("AUGUST 5 1989"));
        map.put("Stallone5", new Date("APRIL 6 1993"));
        map.put("Stallone6", new Date("DECEMBER 6 1967"));
        map.put("Stallone7", new Date("JULY 6 1998"));
        map.put("Stallone8", new Date("AUGUST 7 1936"));
        map.put("Stallone9", new Date("JUNE 9 1954"));

        return map;

    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        ArrayList<String> summerPeople = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        for (Map.Entry x : map.entrySet()) {
            calendar.setTime((Date) x.getValue());
            if (calendar.get(Calendar.MONTH) == Calendar.JUNE ||
                    calendar.get(Calendar.MONTH) == Calendar.JULY ||
                    calendar.get(Calendar.MONTH) == Calendar.AUGUST)
                summerPeople.add((String) x.getKey());
        }
        for (String x : summerPeople)
            map.remove(x);


    }
}
