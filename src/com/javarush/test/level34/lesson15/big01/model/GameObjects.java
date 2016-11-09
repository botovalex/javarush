package com.javarush.test.level34.lesson15.big01.model;

import java.util.HashSet;
import java.util.Set;

public class GameObjects {
    private Set<Wall> walls;
    private Set<Box> boxes;
    private Set<Home> homes;
    private Player player;

    public Set<Wall> getWalls() {
        return walls;
    }
    public Set<Box> getBoxes() {
        return boxes;
    }
    public Set<Home> getHomes() {
        return homes;
    }
    public Player getPlayer() {
        return player;
    }

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player) {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }

    public Set<GameObject> getAll() {
        Set<GameObject> result = new HashSet<>();
        result.addAll(walls);
        result.addAll(boxes);
        result.addAll(homes);
        result.add(player);
        return result;
    }
}


/*
8.2.	Добавь в него:
8.2.1.	Поля Set<Wall> walls, Set<Box> boxes, Set<Home> homes и Player player.
8.2.2.	Геттеры для этих полей.
8.2.3.	Конструктор класса, принимающий Set<Wall> walls, Set<Box> boxes, Set<Home>
homes, Player player и инициализирующий поля класса.
8.2.4.	Метод Set<GameObject> getAll(). Он должен возвращать множество, содержащее
все объекты, хранящиеся внутри класса.
 */