package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.concurrent.LinkedBlockingQueue;

public class Cook implements Runnable {
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public Cook(String name) {
        this.name = name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order) {
        busy = true;
        System.out.println(String.format("Start cooking - %s, cooking time %dmin", order, order.getTotalCookingTime()));
        StatisticEventManager.getInstance().register(
                new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes()));
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException ignored) {
        }
        busy = false;
    }

    @Override
    public void run() {
        Order order = null;
        try {
            while (true) {
                if (!busy) order = queue.take();
                if (order != null) startCookingOrder(order);
                Thread.sleep(10);
            }

        } catch (InterruptedException e) {
            return;
        }
    }
}

/*
1. В классе Cook создай поле boolean busy с геттером.
2. В начале метода startCookingOrder поставьте busy в true, а в конце метода в false.
3. В методе startCookingOrder сымитируй задержку при приготовлении блюда, поставь слип в 10-кратном размере от времени
приготовления заказа, т.е. если заказ готовится 30 минут, то задержка будет 30*10 миллисекунд
 */