package com.company;

public class Game {
    String[][][] board = new String[4][4][4]; //[s (sheet)],[r (row)], [c (collumn)
    boolean win = false;
    Game(){
        // E = empty, F = filled

        for(int sheet = 0; sheet < 4; sheet++){
            for(int row = 0; row < 4; row++){
                for(int col = 0; col < 4; col++){
                    board[sheet][row][col] = "E";
                }
            }
        }

    }

    public String[][][] getBoard() {
        return board;
    }

    public boolean win(){
        return win;
    }

    //include win methods
}
