package com.javarush.test.level14.lesson08.bonus03;

/**
 * Created by barbudos on 18.06.2016.
 */
public class Singleton {
    private static final Singleton sin = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return sin;
    }
}
