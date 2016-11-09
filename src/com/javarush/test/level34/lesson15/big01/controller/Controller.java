package com.javarush.test.level34.lesson15.big01.controller;

import com.javarush.test.level34.lesson15.big01.model.Direction;
import com.javarush.test.level34.lesson15.big01.model.GameObjects;
import com.javarush.test.level34.lesson15.big01.model.Model;
import com.javarush.test.level34.lesson15.big01.view.View;

public class Controller implements EventListener{
    private Model model;
    private View view;

    public Controller() {
        model = new Model();
        view = new View(this);
        view.init();
        model.restart();
        model.setEventListener(this);
        view.setEventListener(this);
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}

/*
13.2.1.	move(Direction direction) – должен вызывать move(Direction direction) у модели
и update() у представления. Метода move() у модели еще нет, добавь для него
заглушку, мы его реализуем позже.
13.2.2.	restart() – должен перезапускать модель и обновлять представление.
13.2.3.	startNextLevel() – должен запускать у модели новый уровень и обновлять
представление.

13.4.	Реализуй в контроллере метод levelCompleted(int level), он должен вызвать
метод completed() у представления.
 */