package com.javarush.test.level15.lesson12.home04;

/**
 * Created by barbudos on 18.06.2016.
 */
public class Earth implements Planet {
    private static Earth instance = null;

    private Earth() {}

    public static synchronized Earth getInstance() {
        if (instance == null)
            instance = new Earth();
        return instance;
    }
}
