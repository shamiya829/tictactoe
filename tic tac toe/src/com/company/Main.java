package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Game game = new Game();
        String[][][] board = game.getBoard();


        //new GameFrame("tictac toe");
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

                if (!board[sheet][row][col].isEmpty()) {
                    System.out.println("That is not a valid move!");
                    continue;
                } else {
                    String s = "";
                    s += playerOne.getLetter();
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

                if (!board[sheet][row][col].isEmpty()) {
                    System.out.println("That is not a valid move!");
                    continue;
                } else {
                    String s = "";
                    s += playerTwo.getLetter();
                    board[sheet][row][col] = s; //player places a move!!
                    turn++; //should be an odd number now (playe1)}
                }
            }
        }
        displayBoard(board);

        //Tavishee Shishulakr/Mr.Tully/4th period
    }

    public static void displayBoard(String[][][] board)
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
