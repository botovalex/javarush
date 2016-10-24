package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class ConsoleHelper {
    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private ConsoleHelper() {}

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        writeMessage(Dish.allDishesToString());
        String answer;
        while (true) {
            answer = readString();
            if (answer.equalsIgnoreCase("exit")) break;
            try {
                dishes.add(Dish.valueOf(answer.trim()));
            } catch (IllegalArgumentException e) {
                writeMessage(answer + " is not detected");
            }
        }
        return dishes;
    }
}