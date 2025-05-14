package org.example;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player extends Thread {
    private final String name;
    private final Bag bag;
    private final Board board;
    private final Dictionary dictionary;
    private final TurnManager turnManager;
    private final int playerIndex;
    private final AtomicBoolean gameOver;
    private List<Tile> tiles = new ArrayList<>();

    public Player(String name, Bag bag, Board board, Dictionary dictionary, TurnManager turnManager, int playerIndex, AtomicBoolean gameOver) {
        this.name = name;
        this.bag = bag;
        this.board = board;
        this.dictionary = dictionary;
        this.turnManager = turnManager;
        this.playerIndex = playerIndex;
        this.gameOver = gameOver;
        drawInitialTiles();
    }

    private void drawInitialTiles() {
        synchronized (bag) {
            tiles.addAll(bag.extractTiles(7));
        }
    }

    @Override
    public void run() {
        while (!gameOver.get()) {
            try {
                turnManager.waitForTurn(playerIndex);
                if (gameOver.get()) break;
                takeTurn();
                turnManager.nextTurn();
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void takeTurn() {
        Optional<String> bestWord = chooseBestWord();
        if (bestWord.isPresent()) {
            int score = calculateScore(bestWord.get());
            board.addScore(name, score);
            System.out.println(name + " submitted: " + bestWord.get() + " (" + score + " points)");
            refillTiles(bestWord.get().length());
        } else {
            discardTiles();
            System.out.println(name + " discarded tiles");
        }
    }

    private Optional<String> chooseBestWord() {
        return dictionary.getAllPossibleWords(tiles)
                .stream()
                .max(Comparator.comparingInt(this::calculateScore));
    }

    private int calculateScore(String word) {
        return word.chars()
                .map(c -> tiles.stream()
                        .filter(t -> t.getLetter() == Character.toUpperCase((char) c))
                        .findFirst()
                        .map(Tile::getPoints)
                        .orElse(0))
                .sum();
    }

    private void refillTiles(int k) {
        synchronized (bag) {
            List<Tile> newTiles = bag.extractTiles(k);
            tiles.addAll(newTiles);
        }
    }

    private void discardTiles() {
        synchronized (bag) {
            bag.addTiles(tiles);
            tiles.clear();
            tiles.addAll(bag.extractTiles(7));
        }
    }
}
