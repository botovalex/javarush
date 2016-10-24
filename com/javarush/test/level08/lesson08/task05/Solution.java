package com.javarush.test.level08.lesson08.task05;

import java.util.*;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Botov0", "Sasha");
        map.put("Botov1", "Sasha1");
        map.put("Botov2", "Sasha");
        map.put("Botov3", "Sasha2");
        map.put("Botov4", "Sasha");
        map.put("Botov5", "Sasha3");
        map.put("Botov6", "Sasha");
        map.put("Botov7", "Sasha4");
        map.put("Botov8", "Sasha");
        map.put("Botov9", "Sasha5");

        return map;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        ArrayList<String> names = new ArrayList<>();
        for (String temp: map.values())
            if (Collections.frequency(map.values(), temp) >= 2)
                names.add(temp);
        for (String temp: names)
            removeItemFromMapByValue(map, temp);


    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }
}
