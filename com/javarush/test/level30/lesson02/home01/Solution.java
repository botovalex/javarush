package com.javarush.test.level30.lesson02.home01;

import java.math.BigInteger;

/* Конвертер систем счислений
Реализуйте логику метода convertNumberToOtherNumerationSystem, который должен переводить число number.getDigit()
из одной системы счисления(numerationSystem) в другую (expectedNumerationSystem)
бросьте NumberFormatException, если переданное число некорректно, например, число "120" с системой счисления 2
Валидация для - number.getDigit() - целое не отрицательное
Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumerationSystemType._10, "1202");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);    //expected 110

        Number number2 = new Number(NumerationSystemType._10, "158");
        Number result2 = convertNumberToOtherNumerationSystem(number2, NumerationSystemType._16);
        System.out.println(result2);

    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) {
        int firstNumerationSystem = number.getNumerationSystem().getNumerationSystemIntValue();
        int secondNumerationSystem = expectedNumerationSystem.getNumerationSystemIntValue();
        BigInteger bigNumber = new BigInteger(number.getDigit(), firstNumerationSystem);
        if (bigNumber.compareTo(BigInteger.ZERO) < 0) throw new NumberFormatException();
        return new Number(expectedNumerationSystem, bigNumber.toString(secondNumerationSystem));
    }

}

/*
19F =  1*16^2 + 9*16^1 + F*16^0 = 1*256 + 9*16 + 15*1 = 415
 */

/*

    private static String converDecimalToAnotherNumerationSystem(String digitString, int numerationSystem) {
        String result = "";
        int digit = Integer.parseInt(digitString);
        String[] abcdef = {"A", "B", "C", "D", "E", "F"};
        int temp;
        while (digit != 0) {
            temp = digit % numerationSystem;
            result = (temp < 10 ? temp : abcdef[temp - 10]) + result;
            digit = digit / numerationSystem;
        }
        return result;
    }

    private static String converAnotherNumerationSystemToDemical(String digitString, int numerationSystem) {
        String result = "";
        int digit = Integer.parseInt(digitString);


        return result;
    }
 */