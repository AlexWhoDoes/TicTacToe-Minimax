package tictactoe;

import java.util.Random;

public class Easy implements Player {
    Random random = new Random();

    int[] coordinates = new int[2];

    public void makeMove(char[][] board, char token) {
        levelEasy();
        moveByAI(board, token);
    }

    private void levelEasy() {
        coordinates[0] = random.nextInt(3) + 1;
        coordinates[1] = random.nextInt(3) + 1;
    }

    public void moveByAI(char[][] board, char token) {
        System.out.println("Making move level easy");
        computerMove(board, token);
    }

    private void computerMove(char[][] board, char token) {
        if (board[3 - coordinates[1]][coordinates[0] - 1] == ' ') {
            board[3 - coordinates[1]][coordinates[0] - 1] = token;
        } else {
            levelEasy();
            computerMove(board, token);
        }
    }
}
