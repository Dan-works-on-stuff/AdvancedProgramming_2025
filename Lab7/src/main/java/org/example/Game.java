package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    public static void main(String[] args) throws Exception {
        int numPlayers = 3;
        Path dictionaryPath = Paths.get("C:\\Users\\radud\\Documents\\GitHub\\AdvancedProgramming_2025\\Lab7\\src\\main\\resources\\dictionary.txt");
        long timeLimitSeconds = 60;

        Bag bag = new Bag();
        Board board = new Board();
        Dictionary dictionary = new Dictionary(dictionaryPath);
        TurnManager turnManager = new TurnManager(numPlayers);
        AtomicBoolean gameOver = new AtomicBoolean(false);

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player - " + (i+1), bag, board, dictionary, turnManager, i, gameOver));
        }

        Timekeeper timekeeper = new Timekeeper(timeLimitSeconds * 1000, gameOver);
        timekeeper.start();

        players.forEach(Thread::start);
        players.forEach(player -> {
            try {
                player.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        board.printWinner();
    }
}