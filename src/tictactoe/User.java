package tictactoe;

import java.util.Scanner;

public class User implements Player{
    int[] coordinates = new int[2];

    @Override
    public void makeMove(char[][] board, char token) {
        boolean flag = true;
        Scanner scan = new Scanner(System.in);
        while (flag) {
            System.out.println("Enter the Coordinates: ");
            boolean check = values(scan);
            if (check) {
                System.out.println("You should enter numbers!");
                scan.nextLine();
                continue;
            }
            if (coordinates[0] > 3 || coordinates[1] > 3 || coordinates[0] < 1 || coordinates[1] < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (board[3 - coordinates[1]][coordinates[0] - 1] == ' ') {
                board[3 - coordinates[1]][coordinates[0] - 1] = token;
                flag = false;
                //gameView(pos);
                //checkState(c, pos);
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
        }
    }

    private boolean values(Scanner scan) {
        String k = scan.next();
        if (!k.matches("[0-9]")) {
            return true;
        }
        coordinates[0] = Integer.parseInt(k);
        String l = scan.next();
        if (!l.matches("[0-9]")) {
            return true;
        }
        coordinates[1] = Integer.parseInt(l);
        return false;
    }

}
