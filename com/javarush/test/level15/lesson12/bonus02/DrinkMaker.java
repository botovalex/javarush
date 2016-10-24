package com.javarush.test.level15.lesson12.bonus02;

/**
 * Created by barbudos on 20.06.2016.
 */
public abstract class DrinkMaker {

    abstract void getRightCup();

    abstract void putIngredient();

    abstract void pour();

    public void makeDrink() {
        getRightCup();
        putIngredient();
        pour();
    }

}
