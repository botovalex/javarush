package com.javarush.test.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by barbudos on 23.08.2016.
 */
public class MyThread extends Thread {
    private static AtomicInteger staticCount = new AtomicInteger(0);
    private AtomicInteger count = new AtomicInteger(staticCount.getAndIncrement());

    public MyThread() {
        super();
        this.setPriority(count.get() % 10 + 1);
    }

    public MyThread(Runnable target) {
        super(target);
        this.setPriority(count.get() % 10 + 1);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        int maxPriority = group.getMaxPriority();
        int priority = count.get() % 10 + 1;
        this.setPriority(priority > maxPriority ? maxPriority : priority);
    }

    public MyThread(String name) {
        super(name);
        this.setPriority(count.get() % 10 + 1);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        int maxPriority = group.getMaxPriority();
        int priority = count.get() % 10 + 1;
        this.setPriority(priority > maxPriority ? maxPriority : priority);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        this.setPriority(count.get() % 10 + 1);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        int maxPriority = group.getMaxPriority();
        int priority = count.get() % 10 + 1;
        this.setPriority(priority > maxPriority ? maxPriority : priority);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        int maxPriority = group.getMaxPriority();
        int priority = count.get() % 10 + 1;
        this.setPriority(priority > maxPriority ? maxPriority : priority);
    }


}
