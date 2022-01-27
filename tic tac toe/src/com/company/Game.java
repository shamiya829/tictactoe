package com.company;

public class Game {
    char[][][] board = new char[4][4][4]; //[s (sheet)],[r (row)], [c (collumn)

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
        if(rowWin()||colWin()||diagWin())
            return true;
        return false;
    }

    public boolean rowWin(){
        for(int row = 0; row < board.length; row++) {
            int numEqual = 0;
            char val = board[0][row][0];
            if (val == 'E') continue;
            for(int column = 1; column < board.length; column ++) {

                for(int sheet = 0; sheet < board.length; sheet++){
                    char nextChar = board[0][row][column];
                    char nextNextChar = board[sheet][row][column];
                if(nextChar == 'E' || nextNextChar == 'E')
                    break;
                else if (val != nextChar || nextChar != nextNextChar)
                    break;
                else numEqual++;
                }
            }
            if(numEqual == 4)
                return true;
        }
        return false;
    }

    public boolean colWin(){
        return false;
    }

    public boolean diagWin(){
        return false;
    }

}
