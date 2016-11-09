package com.javarush.test.level34.lesson15.big01.controller;

import com.javarush.test.level34.lesson15.big01.model.Direction;

public interface EventListener {
    void move(Direction direction);
    void restart();
    void startNextLevel();
    void levelCompleted(int level);
}

/*
9.2.	Добавь в интерфейс методы:
9.2.1.	move(Direction direction) – передвинуть объект в определенном направлении.
9.2.2.	restart() – начать заново текущий уровень.
9.2.3.	startNextLevel() – начать следующий уровень.
9.2.4.	levelCompleted(int level) – уровень с номером level завершён
 */