package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Bag {
    private final List<Tile> tiles = new ArrayList<>();
    private final Random random = new Random();

    public Bag() {
        initializeTiles();
        Collections.shuffle(tiles);
    }

    private void initializeTiles() {
        for (char c = 'A'; c <= 'Z'; c++) {
            int points = random.nextInt(10) + 1;
            for (int i = 0; i < 10; i++) {
                tiles.add(new Tile(c, points));
            }
        }
    }

    public synchronized List<Tile> extractTiles(int count) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < count && !tiles.isEmpty(); i++) {
            extracted.add(tiles.remove(0));
        }
        return extracted;
    }

    public synchronized void addTiles(List<Tile> tilesToAdd) {
        tiles.addAll(tilesToAdd);
        Collections.shuffle(tiles);
    }

    public synchronized boolean isEmpty() {
        return tiles.isEmpty();
    }
}
