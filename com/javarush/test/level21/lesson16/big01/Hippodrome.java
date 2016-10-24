package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by barbudos on 17.06.2016.
 */
public class Hippodrome {
    private ArrayList<Horse> horses = new ArrayList<Horse>();
    public static Hippodrome game;

    public ArrayList<Horse> getHorses() {
        return horses;
    }

    public static void main(String[] args) {
        game = new Hippodrome();
        game.getHorses().add(new Horse("Тенегрив", 3, 0));
        game.getHorses().add(new Horse("Плотва", 3, 0));
        game.getHorses().add(new Horse("Мороз", 3, 0));

        game.run();
        game.printWinner();
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        System.out.println();
        System.out.println();
    }

    public Horse getWinner() {
        int winnerIndex = 0;
        double maxDistance = 0;
        for (int i = 0; i < horses.size(); i++) {
            double dist = horses.get(i).getDistance();
            if (dist > maxDistance) {
                maxDistance = dist;
                winnerIndex = i;
            }
        }
        return horses.get(winnerIndex);
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}

/*
Добавим определение победителя.
В классе Hippodrome сделаем два метода:
public Horse getWinner() и public void printWinner()

Метод getWinner должен возвращать лошадь пробежавшую самую большую дистанцию.
Метод printWinner выводит на экран имя победителя в виде:
Winner is <NAME>!
Пример:
Winner is Lucky!
 */