package com.gamebox.game;

import com.gamebox.tictactoeLogic.TicTacToe;
import com.gamebox.ui.TicTacToeUI;

public class TicTacToeGame implements Game {

    private final TicTacToe tictactoe;

    private final TicTacToeUI ui;

    public TicTacToeGame(TicTacToe tictactoe, TicTacToeUI ui) {
        this.tictactoe = tictactoe;
        this.ui = ui;
    }

    @Override
    public void start() {
        ui.displayGameState(tictactoe.printBoard());
    }

    @Override
    public void play() {

        int player = -1;

        while (!tictactoe.isGameOver()) {

            int[] move;

            if(player == -1) {
                move = ui.getPlayerInputInt(2, "Player One enter your the (row and column): ");
            }else{
                move = ui.getPlayerInputInt(2, "Player Two enter your the (row and column): ");
            }

            if (tictactoe.isMoveValid(move)){

                tictactoe.playerMove(move, player);
                ui.displayGameState(tictactoe.printBoard());

                if (tictactoe.isGameOver()) {
                    break;
                }else if(tictactoe.getGameMode() == GameMode.SINGLE){

                    tictactoe.computerMove();
                    ui.displayGameState(tictactoe.printBoard());

                } else if (tictactoe.getGameMode() == GameMode.VERSUS){

                    if (player == -1){
                        player = 1;
                    }else {
                        player = -1;
                    }
                }

            }else{
                ui.displayGameState("This move is not possible");
            }
        }
    }

    @Override
    public void end() {
        int winner = tictactoe.isWinner();

        if (winner == -1) {
            ui.displayGameResult(true, "Player-One");
        } else if (winner == 1 && tictactoe.getGameMode() == GameMode.SINGLE) {
            ui.displayGameResult(true, "AI");
        } else if (winner == 1) {
            ui.displayGameResult(true, "Player-Two");
        } else {
            ui.displayGameResult(false, "draw!");
        }
    }
}
