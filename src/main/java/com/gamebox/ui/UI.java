package com.gamebox.ui;

import java.util.Scanner;

public interface UI {
    void displayGameState(String... gameDetails);

    void displayGameResult(boolean isWinner, String details);

    default String getPlayerInputString(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }

    default int[] getPlayerInputInt(int inputsLength, String prompt) {
        int[] inputs = new int[inputsLength];
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        while (i < inputsLength) {
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            } else {
                inputs[i] = scanner.nextInt();
                i++;
            }
        }
        return inputs;
    }
}
