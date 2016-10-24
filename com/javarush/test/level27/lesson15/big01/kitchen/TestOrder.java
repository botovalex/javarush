package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by barbudos on 05.09.2016.
 */
public class TestOrder extends Order {
    private final static Random random = new Random(System.currentTimeMillis());

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        dishes = new ArrayList<>();
        Dish[] values = Dish.values();
        for (int i = 0; i < random.nextInt(values.length) + 1; i++) {
            dishes.add(values[random.nextInt(values.length)]);
        }
    }
}
