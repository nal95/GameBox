package com.gamebox;

import com.gamebox.game.*;
import com.gamebox.hangmanLogic.HangmanDictionary;
import com.gamebox.ui.HangmanUI;
import com.gamebox.tictactoeLogic.TicTacToe;
import com.gamebox.ui.TicTacToeUI;
import com.gamebox.ui.UI;

public class GameFactory {
    public static Game createGame(GameType type, GameMode gameMode, UI ui, HangmanDictionary dictionary, int numberOfTour) {
        switch (type) {
            case TIC_TAC_TOE:
                TicTacToe tictactoe = new TicTacToe(gameMode);
                return new TicTacToeGame(tictactoe, (TicTacToeUI) ui);
            case HANGMAN:
                return new HangmanGame((HangmanUI) ui, dictionary, numberOfTour);
            case COUNTDOWN:
                System.out.println("COUNTDOWN Game");
            default:
                throw new IllegalArgumentException("Unsupported game type");
        }
    }
}
