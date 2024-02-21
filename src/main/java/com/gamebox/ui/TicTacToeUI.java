package com.gamebox.ui;

public class TicTacToeUI implements UI {
    @Override
    public void displayGameState(String... gameDetails) {
        for (String detail : gameDetails) {
            System.out.println(detail);
            System.out.println();
        }
    }

    @Override
    public void displayGameResult(boolean isWinner, String details) {
        if (isWinner) {
            System.out.printf("Congratulations, %s wins!!!!", details);
        } else {
            System.out.println("It's a draw!");
        }
    }
}
