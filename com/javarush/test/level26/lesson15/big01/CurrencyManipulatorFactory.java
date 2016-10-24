package com.javarush.test.level26.lesson15.big01;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by barbudos on 25.08.2016.
 */
public class CurrencyManipulatorFactory {
    private static CurrencyManipulatorFactory ourInstance = new CurrencyManipulatorFactory();
    private static Map<String, CurrencyManipulator> manipulators = new HashMap<>();

    public static CurrencyManipulatorFactory getInstance() {
        return ourInstance;
    }

    private CurrencyManipulatorFactory() {}

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){

        CurrencyManipulator manipulator = manipulators.get(currencyCode);
        if (manipulator == null) {
            manipulator = new CurrencyManipulator(currencyCode);
            manipulators.put(currencyCode, manipulator);
        }
        return manipulator;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return manipulators.values();
    }
}


/*
2.1. В классе CurrencyManipulatorFactory создайте статический метод getAllCurrencyManipulators(), который вернет Collection всех манипуляторов.
У вас все манипуляторы хранятся в карте, не так ли? Если нет, то рефакторьте.
2.2. В InfoCommand в цикле выведите [код валюты - общая сумма денег для выбранной валюты]
Запустим прогу и пополним счет на EUR 100 2 и USD 20 6, и посмотрим на INFO.
Все работает правильно?
 EUR - 200
 USD - 120
Отлично!
 */