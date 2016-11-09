package com.javarush.test.level09.lesson11.home05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


/* Гласные и согласные буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа должна вывести на экран две строки:
1. первая строка содержит только гласные буквы
2. вторая - только согласные буквы и знаки препинания из введённой строки.
Буквы соединять пробелом, каждая строка должна заканчиваться пробелом.

Пример ввода:
Мама мыла раму.
Пример вывода:
а а ы а а у
М м м л р м .
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = br.readLine().toCharArray();
        ArrayList<Character> vowelsChars = new ArrayList<Character>();
        ArrayList<Character> otherChars = new ArrayList<Character>();


        for (char x : chars)
            if (isVowel(x)) vowelsChars.add(x);
            else if (!(Character.isLetterOrDigit(x) || Character.isSpaceChar(x)) || Character.isLetter(x))
                otherChars.add(x);

        for (Character vowelsChar : vowelsChars)
            System.out.print(vowelsChar + " ");
        System.out.println();
        for (Character otherChar : otherChars) {
            System.out.print(otherChar + " ");
        }
    }


    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};

    //метод проверяет, гласная ли буква
    public static boolean isVowel(char c)
    {
        c = Character.toLowerCase(c);  //приводим символ в нижний регистр - от заглавных к строчным буквам

        for (char d : vowels)   //ищем среди массива гласных
        {
            if (c == d)
                return true;
        }
        return false;
    }
}
