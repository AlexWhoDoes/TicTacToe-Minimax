package tictactoe;

public class Hard implements Player {

    int[] coordinates = new int[2];


    @Override
    public void makeMove(char[][] board, char token) {
        int currentScore = 0;
        int bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = token;
                    currentScore = minimax(board, token, false);
                    board[i][j] = ' ';
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        coordinates[0] = j + 1;
                        coordinates[1] = 3 - i;
                    }
                }
            }
        }
        computerMove(board, token);
    }


    private int minimax(char[][] board, char token, boolean isMaximizing) {
        if (checkState(token, board)) {
            return 1;
        }
        if (checkState(token == 'X' ? 'O' : 'X', board)) {
            return -1;
        }
        if (!isEmpty(board)) {
            return 0;
        }
        if (isMaximizing) {
            int currentScore;
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = token;
                        currentScore = minimax(board, token, false);
                        board[i][j] = ' ';
                        if (currentScore > bestScore) {
                            bestScore = currentScore;
                        }
                    }
                }
            }
            return bestScore;
        } else {
            char oppositeToken = token == 'X' ? 'O' : 'X';
            int currentScore;
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = oppositeToken;
                        currentScore = minimax(board, token, true);
                        board[i][j] = ' ';
                        if (currentScore < bestScore) {
                            bestScore = currentScore;
                        }
                    }
                }
            }
            return bestScore;
        }
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

    private void computerMove(char[][] board, char token) {
        System.out.println("Making move level hard");
        board[3 - coordinates[1]][coordinates[0] - 1] = token;
    }


}
