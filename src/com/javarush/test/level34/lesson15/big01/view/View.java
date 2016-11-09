package com.javarush.test.level34.lesson15.big01.view;

import com.javarush.test.level34.lesson15.big01.controller.Controller;
import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.Direction;
import com.javarush.test.level34.lesson15.big01.model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_R;

public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);
    }

    public void init() {
        field = new Field(this);
        add(field);

        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_RELEASED) {
                    switch (e.getKeyCode()) {
                        case VK_LEFT:
                            field.getEventListener().move(Direction.LEFT);
                            break;
                        case VK_RIGHT:
                            field.getEventListener().move(Direction.RIGHT);
                            break;
                        case VK_UP:
                            field.getEventListener().move(Direction.UP);
                            break;
                        case VK_DOWN:
                            field.getEventListener().move(Direction.DOWN);
                            break;
                        case VK_R:
                            field.getEventListener().restart();
                            break;
                    }
                }
                return false;
            }
        };
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
    }

    public void update() {
        field.repaint();
    }

    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }

    public void completed(int level) {
        update();
        JOptionPane.showMessageDialog(null, String.format("Поздравляем! Вы прошли %d уровень.", level),
                "Уровень пройден", JOptionPane.INFORMATION_MESSAGE);
        controller.startNextLevel();
    }
}

/*
13.3.	Добавь в представление метод completed(int level), который будет сообщать
пользователю, что уровень level пройден. Метод должен:
13.3.1.	Обновлять представление.
13.3.2.	Показывать диалоговое окно с информацией о том, что пользователь прошел
какой-то уровень. Подсказка: используй JOptionPane.showMessageDialog.
13.3.3.	Просить контроллер запустить следующий уровень.
 */