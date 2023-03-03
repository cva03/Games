package test;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.start();
    }

    private void start() throws InterruptedException {
        System.out.println("1. 2 Players");
        System.out.println("2. Play with CPU");
        boolean cpu = false;
        if (sc.nextInt() == 2) {
            cpu = true;
        }
        int[][] board = new int[3][3];
        setBoard(board);
        print(board);
        int player = 0;
        int placements = 0;
        boolean firstTime = true;
        while (true) {
            if (placements == 9) {
                System.out.println("Match Draw");
                break;
            }
            int input;
            if (cpu && player % 2 == 1) {
                if (firstTime) {
                    Thread.sleep(500);
                    System.out.println("CPU's chance");
                    Thread.sleep(1000);
                    firstTime = false;
                }
                Random random = new Random();
                input = random.nextInt(9);
            } else {
                System.out.println("Player " + (player % 2 + 1) + "'s chance");
                System.out.println("Enter the input");
                input = sc.nextInt();
            }
            if (checksafe(board, input, (player % 2 + 1))) {
                firstTime = true;
                placements++;
                print(board);
                if (checkWin(board)) {
                    win(player % 2 + 1, cpu);
                    break;
                }
                player++;
            } else {
                if (cpu && player % 2 == 1) {
                    continue;
                }
                System.out.println("Place not available");
            }
        }
    }

    private void print(int[][] board) {
        System.out.println("+ - + - + - +");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == -1) {
                    System.out.print(" X");
                } else if (board[i][j] == -2)
                    System.out.print(" O");
                else
                    System.out.printf("%2d", board[i][j]);
                System.out.print(" |");
            }
            System.out.println("\n+ - + - + - +");
        }
        System.out.println();
    }

    private void win(int player, boolean cpu) {
        if (player == 1) {
            System.out.println("Player 1 wins");
        } else {
            if (cpu) {
                System.out.println("CPU wins");
            } else {
                System.out.println("Player 2 wins");
            }
        }
    }

    private boolean checkWin(int[][] board) {
        int n = 3;
        for (int i = 0; i < n; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
            return true;
        }
        return false;

    }

    private boolean checksafe(int[][] board, int input, int player) {
        int who;
        if (player == 1) {
            who = -1;
        } else {
            who = -2;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == input) {
                    board[i][j] = who;
                    return true;
                }
            }
        }
        return false;
    }

    private void setBoard(int[][] board) {
        int n = 3;
        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = num++;
            }
        }
    }
}
