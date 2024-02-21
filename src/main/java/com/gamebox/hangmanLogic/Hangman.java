package com.gamebox.hangmanLogic;

import java.util.ArrayList;
import java.util.List;

public class Hangman {
    private final String word;
    private String guessedWordState;

    private String playedLetters;
    private int incorrectGuesses;
    private int correctGuesses;

    public Hangman(String word) {
        this.word = word.toLowerCase();
        this.guessedWordState = "_".repeat(word.length());
        this.playedLetters = "";
        this.correctGuesses = 0;
        this.incorrectGuesses = 0;
    }

    public boolean isGameOver() {
        return isWinner() || incorrectGuesses > 5;
    }

    public boolean isWinner() {
        return guessedWordState.equals(word);
    }

    public boolean updateWordState(char letter) {
        if (!playedLetters.contains(String.valueOf(letter))) {
            playedLetters = playedLetters.isEmpty() || playedLetters.isBlank() ? String.valueOf(letter) : playedLetters + letter;

            if (getLetterIndex(letter).isEmpty()) {
                incorrectGuesses++;
                return false;
            } else {
                for (int index : getLetterIndex(letter)) {
                    guessedWordState = guessedWordState.substring(0, index) + letter + guessedWordState.substring(index + 1);
                }
                correctGuesses = getLetterIndex(letter).size();
                return true;
            }
        }
        return false;
    }

    public String getWord() {
        return word;
    }

    public String getGuessedWordState() {
        return guessedWordState;
    }

    public String getPlayedLetters() {
        return playedLetters;
    }

    public int getIncorrectGuesses() {
        return incorrectGuesses;
    }

    public int getCorrectGuesses() {
        return correctGuesses;
    }

    public String getHangman(int length) {

        String[] hang = {
                " ---------",
                " |        |",
                " 0        |",
                "/|\\       |",
                "/ \\       |",
                "          |"
        };

        StringBuilder str = new StringBuilder();

        for (int i = 0; i <= length; i++) {
            str.append(hang[i]).append('\n');
        }

        return str.toString();
    }

    private List<Integer> getLetterIndex(char letter) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                indexes.add(i);
            }
        }
        return indexes;
    }
}
