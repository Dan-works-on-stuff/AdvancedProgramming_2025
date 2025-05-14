package org.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Board {
    private final Map<String, Integer> scores = new ConcurrentHashMap<>();

    public void addScore(String playerName, int points) {
        scores.merge(playerName, points, Integer::sum);
    }

    public Map<String, Integer> getScores() {
        return new HashMap<>(scores);
    }

    public void printWinner() {
        Map<String, Integer> scores = getScores();
        String winner = Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("Winner: " + winner + " with " + scores.get(winner) + " points!");
    }
}