package tictactoe;

import java.util.Random; // TO FINISH!!!

public class Medium implements Player {
    Random random = new Random();

    int[] coordinates = new int[2];

    @Override
    public void makeMove(char[][] board, char token) {
        if(aiMove(board, token)) {
            computerMove(board, token);
        }
        else if(aiLikeOpponent(board, token)) {
            computerMove(board, token);
        }
        else {
            randomMove(board, token);
        }
    }

    private boolean aiLikeOpponent(char[][] board, char token) {
        boolean test = false;
        char opponentToken = token == 'X' ? 'O' : 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = opponentToken;
                } else continue;
                if (checkState(opponentToken, board)) {
                    coordinates[0] = j + 1;
                    coordinates[1] = 3 - i;
                    test = true;
                }
                board[i][j] = ' ';
                break;
            }
        }
        return test;
    }

    public boolean aiMove (char [][] board, char token) {
        boolean check = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = token;
                } else continue;
                if (checkState(token, board)) {
                    coordinates[0] = j + 1;
                    coordinates[1] = 3 - i;
                    check = true;
                }
                board[i][j] = ' ';
                break;
            }
        }
        return check;
    }

    private void randomMove(char[][] board, char token) {
        Random random = new Random();
        coordinates[0] = random.nextInt(3) + 1;
        coordinates[1] = random.nextInt(3) + 1;
        if (board[3 - coordinates[1]][coordinates[0] - 1] == ' ') {
            System.out.println("Making move level medium");
            board[3 - coordinates[1]][coordinates[0] - 1] = token;
        }
        else randomMove(board, token);
    }

    private void computerMove(char[][] board, char token) {
        System.out.println("Making move level medium");
        board[3 - coordinates[1]][coordinates[0] - 1] = token;
    }

    private boolean checkState(char token, char[][] board) {
        boolean check = false;
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == token && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                check = true;
                break;
            } else if (board[0][i] == token && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                check = true;
                break;
            }
        }
        if (!check) {
            if (board[0][0] == token && board[1][1] == board[0][0] && board[2][2] == board[1][1]) {
                check = true;
            } else if (board[0][2] == token && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                check = true;
            } else if (!isEmpty(board)) {
                check = true;
            }
        }
        return check;
    }

    private boolean isEmpty(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == ' ') {
                    return true;
                }
            }

        }
        return false;
    }


}

