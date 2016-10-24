package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waitor;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private final static int ORDER_CREATING_INTERVAL = 100;
    private final static LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Cook cook1 = new Cook("Amigo");
        cook1.setQueue(queue);
        Cook cook2 = new Cook("Bilaabo");
        cook2.setQueue(queue);
        Waitor waitor = new Waitor();
        DirectorTablet directorTablet = new DirectorTablet();

        Thread cook1Thread = new Thread(cook1);
        cook1Thread.start();
        Thread cook2Thread = new Thread(cook2);
        cook2Thread.start();

        ArrayList<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(queue);
            tablets.add(tablet);
        }

        RandomOrderGeneratorTask randomGen = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(randomGen);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        thread.interrupt();
    }
}

/*
В методе main:
1. Удали создание хардкоженного планшета и вызова его метода createOrder()
2. Создай второго повара
3. Зарегистрируй поваров используя класс StatisticEventManager
4. Для второго повара и всех планшетов расставь зависимость Observer-Observable
5. Создай список объектов-планшетов 5 штук, инициализируйте его в цикле
6. Создай и запустим трэд на основе объекта RandomOrderGeneratorTask
7. Через секунду прерви его и посмотри на вывод в консоль
 */