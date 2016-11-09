package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawRect(getX() - getWidth() / 2, getY() - getHeight() / 2,
                getWidth(), getHeight());
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(getX() - getWidth() / 2, getY() - getHeight() / 2,
                getWidth(), getHeight());
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
}

/*
        graphics.setColor(Color.BLACK);
        graphics.drawLine(getX() - getWidth() / 2, getY() - getHeight() / 2,
                getX() + getWidth() / 2, getY() + getHeight() / 2);
        graphics.drawLine(getX() - getWidth() / 2, getY() + getHeight() / 2,
                getX() + getWidth() / 2, getY() - getHeight() / 2);
 */