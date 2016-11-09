package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by barbudos on 07.09.2016.
 */
public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 9; i++) {
                System.out.format("Элемент 'ShareItem-%d' добавлен\n", i);
                queue.offer(new ShareItem(String.format("ShareItem-%d", i), i));
                Thread.sleep(100);
                if (queue.hasWaitingConsumer()) System.out.println("Consumer в ожидании!");
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}