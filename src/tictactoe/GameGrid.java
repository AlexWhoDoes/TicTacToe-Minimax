package tictactoe;

import java.util.Scanner;

public class GameGrid {
    Scanner scan = new Scanner(System.in);
    char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

    private String result = "";


    public char[][] getBoard() {
        return board;
    }

    Player[] players = new Player[2];
    char playerFirst;
    char playerSecond;

    String[] modes;

    public void printGrid() {
        System.out.println("---------");
        System.out.printf("| %s %s %s |%n", this.board[0][0], this.board[0][1], this.board[0][2]);
        System.out.printf("| %s %s %s |%n", this.board[1][0], this.board[1][1], this.board[1][2]);
        System.out.printf("| %s %s %s |%n", this.board[2][0], this.board[2][1], this.board[2][2]);
        System.out.println("---------");

    }

    private boolean isEmpty() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (this.board[i][j] == ' ') {
                    return true;
                }
            }

        }
        return false;
    }

    public String checkWinner() {
        boolean check = true; // what if player win a game earlier than expected
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                result = board[i][0] + " wins";
                check = false;
                break;
            } else if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                result = board[0][i] + " wins";
                check = false;
                break;
            }
        }
        if (check) {
            if (board[1][1] == board[0][0] && board[2][2] == board[1][1]) {
                result = board[1][1] + " wins";
                check = false;
            } else if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                result = board[0][2] + " wins";
                check = false;
            }
        }

        if (check) return "Draw";
        return result;
    } //return  draw or winner

    public boolean checkWinnerBoolean(char token) {
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
            if (board[1][1] == token && board[1][1] == board[0][0] && board[2][2] == board[1][1]) {
                check = true;
            } else if (board[0][2] == token && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                check = true;
            }
        }
        return check;
    }

    public void setModes() {
        System.out.println("Input command: ");
        String mode = scan.nextLine();
        modes = mode.split("\\s+[\n]?");
        if (!modes[0].matches("exit")) {
            while (modes.length < 3 || isExit()) {
                System.out.println("Bad parameters!");
                System.out.println("Input command: ");
                mode = scan.nextLine();
                modes = mode.split("\\s+[\n]?");
            }
        }
    }

    private boolean isExit() {
        return !(modes[1].matches("easy|medium|hard|user") && modes[2].matches("easy|medium|hard|user"));
    }

    public void playMode() {
        setModes();
        if (modes[0].equalsIgnoreCase("start")) {
            definePlayers();
            gameLoop();
        } else if (modes[0].equals("exit")) {
            System.exit(0);
        }
    }

    private void gameLoop() {
        while (isEmpty()) {
            players[0].makeMove(this.board, this.playerFirst);
            printGrid();
            if (checkWinnerBoolean(this.playerFirst)) break;
            if (!isEmpty()) break;
            players[1].makeMove(this.board, this.playerSecond);
            printGrid();
            if (checkWinnerBoolean(this.playerSecond)) break;
        }
        System.out.println(checkWinner());
    }

    public void definePlayers() {
        switch (modes[1]) {
            case "user":
                players[0] = new User();
                playerFirst = 'X';
                break;
            case "easy":
                players[0] = new Easy();
                playerFirst = 'X';
                break;
            case "medium":
                players[0] = new Medium();
                playerFirst = 'X';
                break;
            case "hard":
                players[0] = new Hard();
                playerFirst = 'X';
                break;
        }
        switch (modes[2]) {
            case "user":
                players[1] = new User();
                playerSecond = 'O';
                break;
            case "easy":
                players[1] = new Easy();
                playerSecond = 'O';
                break;
            case "medium":
                players[1] = new Medium();
                playerSecond = 'O';
                break;
            case "hard":
                players[1] = new Hard();
                playerSecond = 'O';
                break;
        }
    }
}




