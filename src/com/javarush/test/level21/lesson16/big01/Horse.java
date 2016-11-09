package com.javarush.test.level21.lesson16.big01;

/**
 * Created by barbudos on 17.06.2016.
 */
public class Horse {
    private String name;
    private double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        distance += speed * Math.random();
    }

    public void print() {
        for (int i = 0; i < distance; i++) {
            System.out.print(".");
        }
        System.out.println(name);
    }

}

/*
Теперь вернемся к методу print класса Horse.
Т.к. мы работаем с консолью, то все лошади на бегах будут выглядеть примерно так:
........Sleven                       <- лошадь Слевин
....Lucky                            <- лошадь Лаки
..........Gomer                      <- лошадь Гомер

Другими словами, в методе print надо вывести на экран строку состоящую из точек и имени лошади.
Количество точек равно distance, округленному до целого числа.
 */

