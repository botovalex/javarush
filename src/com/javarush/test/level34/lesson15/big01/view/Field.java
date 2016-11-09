package com.javarush.test.level34.lesson15.big01.view;


import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.Direction;
import com.javarush.test.level34.lesson15.big01.model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        addKeyListener(new KeyHandler());
        view.setFocusable(true);
    }

    public EventListener getEventListener() {
        return eventListener;
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(this.getX(), this.getY(), getWidth(), getHeight());

        for (GameObject obj : view.getGameObjects().getAll()) {
            obj.draw(g);
        }
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
            switch (e.getKeyCode()) {
                case VK_LEFT:
                    Field.this.eventListener.move(Direction.LEFT);
                    break;
                case VK_RIGHT:
                    Field.this.eventListener.move(Direction.RIGHT);
                    break;
                case VK_UP:
                    Field.this.eventListener.move(Direction.UP);
                    break;
                case VK_DOWN:
                    Field.this.eventListener.move(Direction.DOWN);
                    break;
                case VK_R:
                    Field.this.eventListener.restart();
                    break;
            }
        }
    }
}

/*
14.3.	В конструкторе класса Field:
14.3.1.	Создай объект класса KeyHandler.
14.3.2.	Установи его слушателем с помощью метода addKeyListener().
14.3.3.	Установи focusable в true (метод setFocusable()).

 */