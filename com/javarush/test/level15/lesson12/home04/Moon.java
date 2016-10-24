package com.javarush.test.level15.lesson12.home04;

/**
 * Created by barbudos on 18.06.2016.
 */
public class Moon implements Planet {
    private static Moon instance = null;

    private Moon() {}

    public static synchronized Moon getInstance() {
        if (instance == null)
            instance = new Moon();
        return instance;
    }
}
