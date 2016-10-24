package com.javarush.test.level21.lesson02.task02;

import java.lang.reflect.*;

/* Сравниваем модификаторы
Реализовать логику метода isAllModifiersContainSpecificModifier, который проверяет,
содержит ли переданный параметр allModifiers значение конкретного модификатора specificModifier
*/
public class Solution {
    public static void main(String[] args) {
        int modifiersOfThisClass = Solution.class.getModifiers();
        System.out.println(isAllModifiersContainSpecificModifier(modifiersOfThisClass, Modifier.PUBLIC));   //true
        System.out.println(isAllModifiersContainSpecificModifier(modifiersOfThisClass, Modifier.STATIC));   //false

        int modifiersOfMethod = getMainMethod().getModifiers();
        System.out.println(isAllModifiersContainSpecificModifier(modifiersOfMethod, Modifier.STATIC));      //true

    }

    public static boolean isAllModifiersContainSpecificModifier(int allModifiers, int specificModifier) {
        return  (allModifiers & specificModifier) == specificModifier;//allModifiers == specificModifier & ;
    }

    private static Method getMainMethod() {
        Method[] methods = Solution.class.getDeclaredMethods();
        methods[1].getModifiers();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase("main")) return method;
        }

        return null;
    }
}


/*
00000000  0
00000001  1  00000001  1
00000010  2  00000010  2
00000011  3
00000100  4
00000101  5
00000110  6
00000111  7
00001000  8  00001000  8
00001001  9
 */