package com.javarush.test.level14.lesson08.bonus01;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.NoSuchElementException;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }

    private static void initExceptions()
    {   //it's first exception
        try
        {
            float i = 1 / 0;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        //Add your code here
        try {
            throw new IllegalArgumentException("IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            exceptions.add(e);
        }

        try {
            throw new RuntimeException("RuntimeException");
        } catch (RuntimeException e) {
            exceptions.add(e);
        }

        try {
            throw new NullPointerException("NullPointerException");
        } catch (NullPointerException e) {
            exceptions.add(e);
        }

        try {
            throw new NoSuchElementException("NoSuchElementException");
        } catch (NoSuchElementException e) {
            exceptions.add(e);
        }

        try {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            exceptions.add(e);
        }

        try {
            throw new ClassCastException("ClassCastException");
        } catch (ClassCastException e) {
            exceptions.add(e);
        }

        try {
            throw new FileNotFoundException("FileNotFoundException");
        } catch (FileNotFoundException e) {
            exceptions.add(e);
        }

        try {
            throw new EOFException("EOFException");
        } catch (EOFException e) {
            exceptions.add(e);
        }

        try {
            throw new IllegalFormatCodePointException(1);
        } catch (IllegalFormatCodePointException e) {
            exceptions.add(e);
        }
    }
}
