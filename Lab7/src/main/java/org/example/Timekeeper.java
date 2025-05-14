package org.example;

import java.util.concurrent.atomic.AtomicBoolean;

public class Timekeeper extends Thread {
    private final long timeLimitMillis;
    private final AtomicBoolean gameOver;

    public Timekeeper(long timeLimitMillis, AtomicBoolean gameOver) {
        this.timeLimitMillis = timeLimitMillis;
        this.gameOver = gameOver;
        setDaemon(true);
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (!gameOver.get()) {
            try {
                Thread.sleep(1000);
                long elapsed = System.currentTimeMillis() - startTime;
                System.out.println("Elapsed time: " + elapsed / 1000 + "s");
                if (elapsed >= timeLimitMillis) {
                    gameOver.set(true);
                    System.out.println("Time limit reached!");
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
