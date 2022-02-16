package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {

        //Tavishee Shishulakr/Mr.Tully/4th Period
        //Shamiya Lin/Mr. Tully/4th Period

        Game3 game = new Game3();
        char[][][] board = game.getBoard();
        //hi

        new GameFrame("4X4X4 Tic Tac Toe");

        /*displayBoard(board);

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter player 1 name");
        String name = keyboard.nextLine();
        System.out.println("Enter player 1 character");
        char letter = keyboard.nextLine().charAt(0);
        Player playerOne = new Player(name,letter);

        System.out.println("Enter player 2 name");
        name = keyboard.nextLine();
        System.out.println("Enter player 2 character");
        letter = keyboard.nextLine().charAt(0);
        Player playerTwo = new Player(name,letter);

        int turn = 0,sheet=0,row=0,col=0;
        while (!game.win()) //checking for wins!
        {
            if (turn%2 != 0) // if its an odd number (player 1)
            {
                System.out.println("Player one enter the sheet you want to play on: ");
                sheet = keyboard.nextInt();
                System.out.println("Player one enter the row you want to play on: ");
                row = keyboard.nextInt();
                System.out.println("Player one enter the col you want to play on: ");
                col = keyboard.nextInt();

                if (board[sheet][row][col] != '-') {// i changed it to a comparison statement cuz u used board u can also use isEmpty(sheet, row, col) in Game
                    System.out.println("That is not a valid move!");
                    continue;
                } else {
                    char s;
                    s = playerOne.getLetter();
                    board[sheet][row][col] = s; //player places a move!!
                    turn++; //should be an even number now (playe2)}
                }
            }
            else //player 2
            {
                System.out.println("Player 2 enter the sheet you want to play on: ");
                sheet = keyboard.nextInt();
                System.out.println("Player 2 enter the row you want to play on: ");
                row = keyboard.nextInt();
                System.out.println("Player 2 enter the col you want to play on: ");
                col = keyboard.nextInt();

                if (board[sheet][row][col] != 'E') {
                    System.out.println("That is not a valid move!");
                    continue;
                } else {
                    char s;
                    s = playerTwo.getLetter();
                    board[sheet][row][col] = s; //player places a move!!
                    turn++; //should be an odd number now (playe1)}
                }
            }
        }*/

    }

    public static void displayBoard(char[][][] board)
    {
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                for (int k = 0; k < 4; k++) {

                    System.out.print("[" + board[i][j][k] + "] ");
                }

                System.out.println();
            }
            System.out.println();
        }
    }
}
