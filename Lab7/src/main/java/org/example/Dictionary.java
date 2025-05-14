package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Dictionary {
    private final Map<String, Set<String>> wordMap = new HashMap<>();

    public Dictionary(Path dictionaryFile) throws IOException {
        loadDictionary(dictionaryFile);
    }

    private void loadDictionary(Path dictionaryFile) throws IOException {
        Files.lines(dictionaryFile)
                .forEach(word -> {
                    String sorted = sortString(word);
                    wordMap.computeIfAbsent(sorted, k -> new HashSet<>()).add(word.toUpperCase());
                });
    }

    private String sortString(String word) {
        char[] chars = word.toUpperCase().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public List<String> getAllPossibleWords(List<Tile> tiles) {
        List<Character> letters = new ArrayList<>();
        for (Tile tile : tiles) {
            letters.add(tile.getLetter());
        }
        String sortedLetters = sortLetters(letters);

        List<String> possibleWords = new ArrayList<>();
        generateCombinations(sortedLetters.toCharArray(), new StringBuilder(), 0, possibleWords);
        return possibleWords;
    }

    private void generateCombinations(char[] chars, StringBuilder current, int start, List<String> possibleWords) {
        if (current.length() >= 2) {
            String key = sortString(current.toString());
            Set<String> words = wordMap.get(key);
            if (words != null) {
                possibleWords.addAll(words);
            }
        }

        for (int i = start; i < chars.length; i++) {
            current.append(chars[i]);
            generateCombinations(chars, current, i + 1, possibleWords);
            current.deleteCharAt(current.length() - 1);
        }
    }

    private String sortLetters(List<Character> letters) {
        char[] chars = new char[letters.size()];
        for (int i = 0; i < letters.size(); i++) {
            chars[i] = letters.get(i);
        }
        Arrays.sort(chars);
        return new String(chars);
    }
}
