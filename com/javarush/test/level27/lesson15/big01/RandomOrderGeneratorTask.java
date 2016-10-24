package com.javarush.test.level27.lesson15.big01;

import java.util.List;

/**
 * Created by barbudos on 05.09.2016.
 */
public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        if (tablets != null && !tablets.isEmpty()) {
            while (true) {
                Tablet tablet = tablets.get((int) Math.floor(Math.random() * tablets.size()));
                tablet.createTestOrder();
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    break;
                }
            }

        }
    }
}


//