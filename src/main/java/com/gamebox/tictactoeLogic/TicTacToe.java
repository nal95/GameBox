package com.gamebox.tictactoeLogic;

import com.gamebox.game.GameMode;

import java.util.Arrays;

public class TicTacToe {

    private final int[][] board = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    private final GameMode gameMode;

    public TicTacToe(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public String printBoard() {
        String px = "      0   1   2";
        String py = "      ^   ^   ^";
        StringBuilder line_one = new StringBuilder("0 ->  _ | _ | _");
        StringBuilder line_two = new StringBuilder("1 ->  _ | _ | _");
        StringBuilder line_three = new StringBuilder("2 ->  - | - | - ");

        for (int row = 0; row < 3; row++) {
            int position = 6;
            for (int col = 0; col < 3; col++) {
                char boardCharValue = getBoardCharValue(board[row][col]);
                if (row == 0) {
                    line_one.setCharAt(position, boardCharValue);
                } else if (row == 1) {
                    line_two.setCharAt(position, boardCharValue);
                } else {
                    line_three.setCharAt(position, boardCharValue);
                }
                position = position + 4;
            }
        }

        return px + '\n' + py + '\n'
                + line_one.toString() + '\n'
                + "      ---------" + '\n'
                + line_two.toString() + '\n'
                + "      ---------" + '\n'
                + line_three.toString() + '\n';
    }

    public void playerMove(int[] move, int value) {
        board[move[0]][move[1]] = value;
    }

    public boolean isMoveValid(int[] move) {
        return board[move[0]][move[1]] == 0;
    }

    public void computerMove() {
        int[] bestMove = minimax();
        int row = bestMove[0], col = bestMove[1];
        board[row][col] = 1;
    }

    public boolean isGameOver() {
        return isWinner() != 0 || isBoardFull();
    }

    public int isWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0) {
                return board[i][0];
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != 0) {
                return board[0][i];
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0) {
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0) {
            return board[0][2];
        }

        return 0;
    }

    private boolean isBoardFull() { //review naming
        return Arrays.stream(board)
                .flatMapToInt(Arrays::stream)
                .noneMatch(num -> num == 0);
    }

    private char getBoardCharValue(int caseValue) {
        if (caseValue == -1) {
            return 'X';
        } else if (caseValue == 1) {
            return 'O';
        } else {
            return '-';
        }
    }

    private int minimaxHelper(boolean isMaximizing) {
        if (isWinner() != 0) {
            return isWinner();
        } else if (isBoardFull()) {
            return 0;
        }

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 1;
                        int eval = minimaxHelper(false);
                        board[i][j] = 0;
                        maxEval = Math.max(maxEval, eval);
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = -1;
                        int eval = minimaxHelper(true);
                        board[i][j] = 0;
                        minEval = Math.min(minEval, eval);
                    }
                }
            }
            return minEval;
        }
    }

    private int[] minimax() {
        int[] bestMove = {-1, -1};
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    int score = minimaxHelper(false);
                    board[i][j] = 0;

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        return bestMove;
    }
}
