package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        testString.printSomething();
        String output = outputStream.toString();
        System.setOut(consoleStream);

        String[] splitString = output.split(" ");
        int num1 = Integer.parseInt(splitString[0]);
        String digit = splitString[1];
        int num2 = Integer.parseInt(splitString[2]);
        int result;
        switch (digit) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            default: result = 000000000;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(splitString[0]).append(" ").append(splitString[1]).append(" ")
                .append(splitString[2]).append(" ").append(splitString[3]).append(" ").append(result);
        System.out.println(builder.toString());


    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

