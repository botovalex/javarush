package com.javarush.test.level25.lesson05.home01;

public class LoggingStateThread extends Thread {
    private Thread target;
    private Thread.State state;

    public LoggingStateThread(Thread target) {
        this.target = target;
        state = target.getState();
        this.setDaemon(true);
    }

    @Override
    public void run() {
        Thread.State temp;
        System.out.println(state);
        while (state != State.TERMINATED) {
            temp = target.getState();
            if (!temp.equals(state)) {
                System.out.println(temp);
                state = temp;
            }
        }
    }
}
