package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Model {
    public final static int FIELD_SELL_SIZE = 20;

    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader
            = new LevelLoader(Paths.get(".\\src\\com\\javarush\\test\\level34\\lesson15\\big01\\res\\levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        if (gameObjects == null) {
            gameObjects = levelLoader.getLevel(currentLevel);
        }
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) return true;
        }
        return false;
    }

    public boolean checkBoxCollision(Direction direction) {
        for (Box box : gameObjects.getBoxes()) {
            if (gameObjects.getPlayer().isCollision(box, direction)) {
                if (checkWallCollision(box, direction)) return true;
                for (Box box1 : gameObjects.getBoxes()) {
                    if (box.isCollision(box1, direction)) return true;
                }
                switch (direction) {
                    case LEFT:
                        box.move(- Model.FIELD_SELL_SIZE, 0);
                        break;
                    case RIGHT:
                        box.move(Model.FIELD_SELL_SIZE, 0);
                        break;
                    case UP :
                        box.move(0, - Model.FIELD_SELL_SIZE);
                        break;
                    case DOWN:
                        box.move(0, Model.FIELD_SELL_SIZE);
                        break;
                }
            }
        }
        return false;
    }

    public void checkCompletion() {
        Set<Home> homes = new HashSet<>(gameObjects.getHomes());

        Iterator<Home> it = homes.iterator();
        while (it.hasNext()) {
            Home home = it.next();
            for (Box box : gameObjects.getBoxes()) {
                if (home.getX() == box.getX() && home.getY() == box.getY()) {
                    it.remove();
                    break;
                }
            }
        }

        if (homes.isEmpty()) eventListener.levelCompleted(currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction) || checkBoxCollision(direction)) return;

        switch (direction) {
            case LEFT:
                player.move(- Model.FIELD_SELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(Model.FIELD_SELL_SIZE, 0);
                break;
            case UP :
                player.move(0, - Model.FIELD_SELL_SIZE);
                break;
            case DOWN:
                player.move(0, Model.FIELD_SELL_SIZE);
                break;
        }

        checkCompletion();
    }
}

/*
15.4.	void move(Direction direction). Метод должен:
15.4.1.	Проверить столкновение со стеной (метод checkWallCollision()), если есть
столкновение – выйти из метода.
15.4.2.	Проверить столкновение с ящиками (метод checkBoxCollision()), если есть
столкновение – выйти из метода.
15.4.3.	Передвинуть игрока в направлении direction.
15.4.4.	Проверить завершен ли уровень.
 */