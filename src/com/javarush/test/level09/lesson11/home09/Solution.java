package com.javarush.test.level09.lesson11.home09;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* Десять котов
Создать класс кот – Cat, с полем «имя» (String).
Создать словарь Map(<String, Cat>) и добавить туда 10 котов в виде «Имя»-«Кот».
Получить из Map множество(Set) всех имен и вывести его на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap()
    {
        Map<String, Cat> cats = new HashMap<String, Cat>();
        Cat cat1 = new Cat("Васька");
        cats.put(cat1.name, cat1);
        Cat cat2 = new Cat("Васька1");
        cats.put(cat2.name, cat2);
        Cat cat3 = new Cat("Васька2");
        cats.put(cat3.name, cat3);
        Cat cat4 = new Cat("Васька3");
        cats.put(cat4.name, cat4);
        Cat cat5 = new Cat("Васька4");
        cats.put(cat5.name, cat5);
        Cat cat6 = new Cat("Васька5");
        cats.put(cat6.name, cat6);
        Cat cat7 = new Cat("Машка");
        cats.put(cat7.name, cat7);
        Cat cat8 = new Cat("Васька7");
        cats.put(cat8.name, cat8);
        Cat cat9 = new Cat("Васька8");
        cats.put(cat9.name, cat9);
        Cat cat10 = new Cat("Васька9");
        cats.put(cat10.name, cat10);
        

        return cats;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map)
    {
        Set<Cat> catSet = new HashSet<Cat>();
        for (Cat x : map.values())
            catSet.add(x);

        return catSet;
    }

    public static void printCatSet(Set<Cat> set)
    {
        for (Cat cat:set)
        {
            System.out.println(cat);
        }
    }

    public static class Cat
    {
        private String name;

        public Cat(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return "Cat "+this.name;
        }
    }


}
