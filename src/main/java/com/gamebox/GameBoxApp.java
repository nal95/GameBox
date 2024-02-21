package com.gamebox;

import com.gamebox.game.Game;
import com.gamebox.game.GameMode;
import com.gamebox.game.GameType;
import com.gamebox.hangmanLogic.HangmanDictionary;
import com.gamebox.hangmanLogic.WordCategory;
import com.gamebox.ui.HangmanUI;
import com.gamebox.ui.TicTacToeUI;

import java.util.Scanner;

public class GameBoxApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to GameBox!");
        System.out.println("Choose a game:");

        for (int i = 1; i < GameType.values().length + 1; i++) {
            System.out.println(i + ". " + GameType.values()[i - 1]);
        }

        int choice = scanner.nextInt();
        GameType selectedGameType = GameType.values()[choice - 1];

        switch (selectedGameType) {
            case HANGMAN:
                System.out.println("Select your words category: ");

                for (WordCategory category : WordCategory.values()) {

                    System.out.println((category.ordinal() + 1) + ". " + category.name());
                }

                int category = scanner.nextInt();

                HangmanDictionary dictionary = new HangmanDictionary(WordCategory.values()[category - 1]);

                System.out.printf("Enter the number of Tour you want ot play (between %s and %s): \n", 1, dictionary.getWords().size());

                int numberOfTour = scanner.nextInt();

                playHangman(selectedGameType, dictionary, numberOfTour);

                break;

            case TIC_TAC_TOE:
                System.out.println("Select your game modus:");
                System.out.println("1. Single (Player vs Computer)");
                System.out.println("2. VS (Player X vs Player O)");

                int mode = scanner.nextInt();

                playTicTacToe(selectedGameType, mode - 1);

                break;

            case COUNTDOWN:
                System.out.println("Available soon...");
                break;
        }
    }

    private static void playHangman(GameType selectedGameType, HangmanDictionary dictionary, int numberOfTour) {
        HangmanUI hangmanUI = new HangmanUI();
        Game hangmanGame = GameFactory.createGame(selectedGameType, GameMode.SINGLE, hangmanUI, dictionary, numberOfTour);
        hangmanGame.start();
    }

    private static void playTicTacToe(GameType selectedGameType, int mode) {
        GameMode gameMode = GameMode.values()[mode];
        TicTacToeUI ticTacToeUI = new TicTacToeUI();
        Game tictactoeGame = GameFactory.createGame(selectedGameType, gameMode, ticTacToeUI, null, 0);
        tictactoeGame.start();
        tictactoeGame.play();
        tictactoeGame.end();
    }
}