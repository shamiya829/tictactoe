package com.company;

public class Game3 {
    char[][][] board = new char[4][4][4]; //[s (sheet)],[r (row)], [c (collumn)

    Game3(){
        // - = empty

        for(int sheet = 0; sheet < 4; sheet++)
        { //fills board w empty spaces
            for(int row = 0; row < 4; row++)
            {
                for(int col = 0; col < 4; col++)
                {
                    board[sheet][row][col] = '-';
                }
            }
        }

    }
    public char[][][] getBoard() {
        return board;
    }

    public boolean isEmpty(int s, int r, int c){
        if(board[s][r][c] != '-')
            return false;
        return true;
    }

    public boolean won(){
        if(rowWin()||colWin()||diagWin())
            return true;
        //return char who won - if no winner return 'n'
        return false;
    }

    public boolean rowWin(){
        for(int row = 0; row < board.length; row++) {
            int numEqual = 0;
            char val = board[0][row][0];
            if (val == '-') continue;
            for(int column = 1; column < board.length; column ++) {

                for(int sheet = 0; sheet < board.length; sheet++){
                    char nextChar = board[0][row][column];
                    char nextNextChar = board[sheet][row][column];
                if(nextChar == '-' || nextNextChar == '-')
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

    public boolean colWin() {
        //checking col wins on each sheet
        char pos = 'j';
        int r = 0, s = 0, c = 0;
        for (int sheet = 50; sheet <= 950; sheet += 225) //will run through each box drawn onto board
        {
            c = 0;
            if (s > 3)
                break;
            for (int col = 100; col <= 300; col += 50) {
                r = 0;
                if (c > 3)
                    break;
                for (int row = sheet; row <= sheet + 200; row += 50) {
                    if (r > 3)
                        break;
                    if (r == 1) {
                        pos = board[s][0][c];
                        if (pos == '-')
                            return false;
                    }
                    if (board[s][r][c] != pos)
                        return false;

                    r++;
                }
                c++;
            }
            s++;
        }
        return true;
    }


    public boolean diagWin(){
        return false;
    }

}
