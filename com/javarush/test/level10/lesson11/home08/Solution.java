package com.javarush.test.level10.lesson11.home08;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Массив списков строк
Создать массив, элементами которого будут списки строк. Заполнить массив любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<String>[] arrayOfStringList =  createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList()
    {
        //напишите тут ваш код

        ArrayList<String>[] array = new ArrayList[5];
        for (int i = 0; i < array.length; i++)
            array[i] = new ArrayList<String>();



        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 2; j++)
                array[i].add(Integer.toString(j) + j + j + j + j + j);



        return array;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList)
    {
        for (ArrayList<String> list: arrayOfStringList)
        {
            for (String s : list)
            {
                System.out.println(s);
            }
        }
    }
}