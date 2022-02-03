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

    public boolean checkTie(){
        //if won returns n and board is full
        boolean full = false;
        int num = 0;
        for(int sheet = 0; sheet < 4; sheet++)
        { //fills board w empty spaces
            for(int row = 0; row < 4; row++)
            {
                for(int col = 0; col < 4; col++)
                {
                    if(board[sheet][row][col] != '-' && board[sheet][row][col] != ' ') {
                        full = true;
                        num++;
                    }

                }
            }
        }

        System.out.println(num + " " + won());
        if(num == 64 && won() == 'n')
            return true;




        return false;
    }

    public char won(){
        if(rowWin()!='n')
            return rowWin();

        if(sheetDiag1() != 'n')
            return sheetDiag1();

                if(sheetDiag2() != 'n')
            return sheetDiag2();

        if (colWin()!='n')
            return colWin();

        if (firstdiagWin()!='n')
            return firstdiagWin();

        if (seconddiagWin()!='n')
            return seconddiagWin();

        if(thruWin() != 'n')
            return thruWin();

        return 'n';
    }

    public char thruWin()
    {
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

            System.out.println(val + " " + nextCh + " " + nnCh + " " + nnnCh);
        if((val == nextCh && nextCh == nnCh && nnCh == nnnCh) && val!= ' ')
            return val;
        return 'n';
    }

    public char rowWin(){
        char val = ' ';
        char nextCh = ' ';
        char nnCh = ' ';
        char nnnCh = ' ';
        for(int s = 0; s < 4; s++)
        {
            for(int r = 0; r < 4; r++)
            {
                //x
                if(board[s][r][0] != '-' && board[s][r][1] != '-' && board[s][r][2] != '-' && board[s][r][3] != '-' ) {
                    val = board[s][r][0];
                    nextCh = board[s][r][1];
                    nnCh = board[s][r][2];
                    nnnCh = board[s][r][3];

                }
            }
        }

        if((val == nextCh && nextCh == nnCh && nnCh == nnnCh) && val!= ' ')
            return val;
        return 'n';
    }

    public char colWin()
    {
        char val = ' ';
        char nextCh = ' ';
        char nnCh = ' ';
        char nnnCh = ' ';
        for(int s = 0; s < 4; s++)
        {
            for(int c = 0; c < 4; c++)
            {
                //x
                if(board[s][0][c] != '-' && board[s][1][c] != '-' && board[s][2][c] != '-' && board[s][3][c] != '-' ) {
                    val = board[s][0][c];
                    nextCh = board[s][1][c];
                    nnCh = board[s][2][c];
                    nnnCh = board[s][3][c];

                }
            }
        }

        if((val == nextCh && nextCh == nnCh && nnCh == nnnCh) && val!= ' ')
            return val;
        return 'n';
    }

    public char sheetDiag1(){
        //check sheet diagonals
        for(int sh = 0; sh < 4; sh++)
        {

            System.out.println("check \\" + board[sh][0][0] + " " + board[sh][1][1] + " " + board[sh][2][2] + " " + board[sh][3][3]);
            // \check

            if (board[sh][0][0]=='-')
            {
                return 'n';
            }
            else {
                if ((board[sh][0][0] == board[sh][1][1]) && (board[sh][2][2] == board[sh][3][3]) && (board[sh][0][0] == board[sh][3][3])) {
                    return board[sh][0][0];
                }
            }
        }
        return 'n';
    }

    public char sheetDiag2()
    {
        for(int sh = 0; sh < 4; sh++)
        {
            System.out.println("check \\" + board[sh][0][0] + " " + board[sh][1][1] + " " + board[sh][2][2] + " " + board[sh][3][3]);
                if (board[sh][3][0]  == '-')
                {
                    return 'n';
                }
                else {
                    if ((board[sh][3][0] == board[sh][2][1]) && (board[sh][1][2] == board[sh][0][3]) && (board[sh][3][0] == board[sh][0][3])) {
                        return board[sh][3][0];
                    }
                }
        }
        return 'n';
    }



    public char firstdiagWin(){

        //gets char value
        for(int sh = 0; sh < 4; sh++) {
            char pos = board[sh][0][3];
            if (pos == '-') //if its empty
            {
                return 'n';
            }
            //diagonal from top right to bottom left
            if ((board[sh][0][3] == board[sh][1][2]) && (board[sh][2][1] == board[sh][3][0]) && (board[sh][0][3] == board[sh][3][0])) {
                return pos;
            }
        }
        //checking second diagonal from top left to bottom right

        return 'n';
    }

    public char seconddiagWin()
    {
        for(int sh = 0; sh < 4; sh++) {
            char pos = board[sh][0][0];
            if (pos == '-') //if its empty
            {
                return 'n';
            }

            if (board[sh][0][0] == board[sh][1][1] && board[sh][2][2] == board[sh][3][3] && board[sh][0][0] == board[sh][3][3]) {
                return pos;
            }
        }
        return 'n';
    }

}
