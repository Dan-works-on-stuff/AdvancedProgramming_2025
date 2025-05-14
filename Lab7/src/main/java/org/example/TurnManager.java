package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TurnManager {
    private int currentPlayerIndex;
    private final int totalPlayers;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public TurnManager(int totalPlayers) {
        this.currentPlayerIndex = 0;
        this.totalPlayers = totalPlayers;
    }

    public void waitForTurn(int playerIndex) throws InterruptedException {
        lock.lock();
        try {
            while (playerIndex != currentPlayerIndex) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void nextTurn() {
        lock.lock();
        try {
            currentPlayerIndex = (currentPlayerIndex + 1) % totalPlayers;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
