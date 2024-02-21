package com.gamebox.game;

import com.gamebox.hangmanLogic.Hangman;
import com.gamebox.hangmanLogic.HangmanDictionary;
import com.gamebox.ui.HangmanUI;

import java.util.Random;

public class HangmanGame implements Game {
    private final HangmanUI ui;
    private final int numberOfTour;
    private final HangmanDictionary dictionary;
    private Hangman hangman;
    private int totalPositiveScore = 0;
    private int totalNegativeScore = 0;

    public HangmanGame(HangmanUI ui, HangmanDictionary dictionary, int numberOfTour) {
        this.ui = ui;
        this.dictionary = dictionary;
        this.numberOfTour = dictionary.getWords().size() >= numberOfTour ? numberOfTour : 1;
    }

    @Override
    public void start() {
        Random random = new Random();
        for (int i = 0; i < numberOfTour; i++) {
            int index = random.nextInt((dictionary.getWords().size() - i) + i);
            hangman = new Hangman(dictionary.getWords().get(index));

            ui.displayGameState("Current Word: " + hangman.getGuessedWordState(),
                    "Played Letters: " + hangman.getPlayedLetters(),
                    "Correct Guesses:" + totalPositiveScore,
                    "Incorrect Guesses: " + totalNegativeScore);

            play();

            end();
        }

        ui.displayGameState("Final Score: " + totalPositiveScore);
        ui.displayGameResult(totalPositiveScore > totalNegativeScore, "");
    }

    @Override
    public void play() {
        while (!hangman.isGameOver()) {
            char guessedLetter = ui.getPlayerInputString("Enter your next letter: ").charAt(0);
            String oldPlayedLetters = hangman.getPlayedLetters();
            boolean isValid = hangman.updateWordState(guessedLetter);
            String newPlayedLetters = hangman.getPlayedLetters();

            if (isValid) {
                totalPositiveScore += hangman.getCorrectGuesses();
            } else if (!oldPlayedLetters.equals(newPlayedLetters)) {
                totalNegativeScore = totalNegativeScore + 1;
                ui.displayGameState(hangman.getHangman(hangman.getIncorrectGuesses() - 1));
            }

            if (hangman.isGameOver()) break;

            ui.displayGameState("Current Word: " + hangman.getGuessedWordState(),
                    "Played Letters: " + hangman.getPlayedLetters(),
                    "Correct Guesses:" + totalPositiveScore,
                    "Incorrect Guesses: " + totalNegativeScore);
        }

    }

    @Override
    public void end() {
        ui.displayGameResult(hangman.isWinner(), hangman.getWord());
    }
}
