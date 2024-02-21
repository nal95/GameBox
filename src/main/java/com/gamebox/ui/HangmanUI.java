package com.gamebox.ui;

public class HangmanUI implements UI {

    @Override
    public void displayGameState(String... gameDetails) {
        for (String detail : gameDetails) {
            System.out.println(detail);
        }
    }

    @Override
    public void displayGameResult(boolean isWinner, String details) {
        if (isWinner) {
            if (!details.isEmpty()) System.out.println("Congratulations! You guessed the word: " + details);
            if (details.isEmpty()) System.out.println("Congratulations! You win!!!!!!!!!!");
        } else {
            if (!details.isEmpty())
                System.out.println("Sorry, you couldn't guess the word. The correct word was: " + details);
            if (details.isEmpty()) System.out.println("Congratulations! You win!!!!!!!!!!");
            System.out.println("Sorry, you couldn't guess many words correctly.");
        }
    }
}
