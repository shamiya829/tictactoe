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

    public char won(){
        if(rowWin()!='n')
            return rowWin();

        if (colWin()!='n')
            return colWin();

        if (diagWin()!='n')
            return diagWin();

        if(thruWin() != 'n')
            return thruWin();

        return 'n';
    }

    public char thruWin(){
        char val = ' ';
        char nextCh = ' ';
        char nnCh = ' ';
        char nnnCh = ' ';
            for(int r = 0; r < 4; r++){
                for(int c = 0; c < 4; c++){
                    //x
                    if(board[0][r][c] != '-' && board[1][r][c] != '-' && board[2][r][c] != '-' && board[3][r][c] != '-' ) {
                        val = board[0][r][c];
                        nextCh = board[1][r][c];
                        nnCh = board[2][r][c];
                        nnnCh = board[3][r][c];

                    }
            }
        }

        if(val == nextCh && nextCh == nnCh && nnCh == nnnCh && val != ' ')
            return val;
        return 'n';
    }

    public char rowWin(){
        char val = ' ';
        char nextCh = ' ';
        char nnCh = ' ';
        char nnnCh = ' ';
        for(int r = 0; r < 4; r++){
            for(int sh = 0; sh < 4;sh++){
                if(board[sh][r][0] != '-' && board[sh][r][1] != '-' && board[sh][r][2] != '-' && board[sh][r][3] != '-' ) {
                    val = board[sh][r][0];
                    nextCh = board[sh][r][1];
                    nnCh = board[sh][r][2];
                    nnnCh = board[sh][r][3];

                }
            }
        }

        System.out.println(val + " " + nextCh + " " + nnCh + " " + nnnCh);
        if(val == nextCh && nextCh == nnCh && nnCh == nnnCh && val != ' ')
            return val;
        return 'n';
    }

    public char colWin() {
        //checking col wins on each sheet
        char pos = 'j';
        int numequal =0;
        for (int s= 0; s <4; s++) //will run through each box drawn onto board
        {
            for (int c = 0; c<4; c++)
            {
                pos = board[s][0][c];
                for (int r = 0; r<4; r++)
                {
                    if (board[s][r][c]=='-')
                        return 'n';
                    else if (board[s][r][c] == pos)
                    {
                        numequal++;
                    }
                }
            }
        }
        if (numequal==4)
        {
            return pos;
        }
        return 'n';
    }




    public char diagWin(){
        //gets char value
        char pos = board[0][0][3];
        if (pos=='-') //if its empty
        {
            return 'n';
        }
        //diagonal from top right to bottom left
        if ((board[0][0][3]==board[1][1][2])&&(board[2][2][1]==board[3][3][0])&&(board[0][0][3]==board[3][3][0]))
        {
            return pos;
        }

        //checking second diagonal from top left to bottom right
        pos = board[0][0][0];
        if (pos=='-') //if its empty
        {
            return 'n';
        }

        if (board[0][0][0] == board[1][1][1] && board[2][2][2]==board[3][3][3] && board[0][0][0] ==board[3][3][3])
        {
            return pos;
        }

        return 'n';
    }

}
