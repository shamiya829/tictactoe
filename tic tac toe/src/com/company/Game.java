package com.company;

public class Game {
    char[][][] board = new char[4][4][4]; //[s (sheet)],[r (row)], [c (collumn)
    boolean win = false;
    Game(){
        // E = empty, F = filled

        for(int sheet = 0; sheet < 4; sheet++){
            for(int row = 0; row < 4; row++){
                for(int col = 0; col < 4; col++){
                    board[sheet][row][col] = 'E';
                }
            }
        }

    }
    public char[][][] getBoard() {
        return board;
    }

    public boolean isEmpty(int s, int r, int c){
        if(board[s][r][c] == 'E')
            return true;
        return false;
    }

    public boolean win(){
        //give scenarios
        return win;
    }

}
