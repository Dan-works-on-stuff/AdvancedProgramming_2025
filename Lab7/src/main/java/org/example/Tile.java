package org.example;

public class Tile {
    private final char letter;
    private final int points;

    public Tile(char letter, int points) {
        this.letter = Character.toUpperCase(letter);
        this.points = points;
    }

    public char getLetter() {
        return letter;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.valueOf(letter);
    }
}