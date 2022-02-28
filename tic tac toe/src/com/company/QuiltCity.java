package com.company;

import java.util.ArrayList;

public class QuiltCity extends Game3
{
    Location firstMove= generateRandomLocation();
    int movenumber = 0;
    int winType =8 ; //going to store wintype for straight line
    public final int row = 1; //works
    public final int col = 2; //works
    public final int rowThru = 3; //wprks
    public final int colThru = 4; //works
    public final int backslashdiag = 5; //works
    public final int frontslashdiag = 6; //works
    public final int backslashthru = 7; //works
    public final int frontslashthu =8;
    String name;
    char letter;
    char opponentName = 'n';
    char[][][] board1Ahead = new char [4][4][4];
    char[][][] board2Ahead = new char [4][4][4];

    QuiltCity(char letter)
    {
        for(int sheet=0; sheet < 4; sheet++)
            for(int row=0; row < 4; row++)
                for(int col=0; col < 4; col++)
                {
                    board[sheet][row][col] = '-';
                }

        firstMove = generateRandomLocation();
        this.letter = letter;
        name = "Quilt City (1.0 Version)";
        //winType = (int)Math.floor(Math.random()*(8-1+1)+1); //will pick a certain wintype at random
        System.out.println("win type number: "+winType);

        if (letter == 'o')
            opponentName = 'x';
        else
            opponentName = 'o';
    }

    public ArrayList<Location> rowMoves(char[][][] board) //col values change to make a row win
    {
        ArrayList<Location> arr = new ArrayList<>(); //arraylist of all values in the row of the first move
        int s = firstMove.getSheet();
        int r = firstMove.getRow();

        if(board[s][r][0] == '-') //if the location in col 0 is avaliable, add it to arraylist (and so forth)
            arr.add(new Location (0,r,s));
        if(board[s][r][1] == '-')
            arr.add(new Location (1,r,s));
        if (board[s][r][2] == '-')
            arr.add(new Location (2,r,s));
        if (board[s][r][3] == '-' )
            arr.add(new Location (3,r,s));
        return arr;
    }

    public ArrayList<Location> colMoves(char[][][] board)
    {
        ArrayList<Location> arr = new ArrayList<>();
        int s = firstMove.getSheet();
        int c = firstMove.getCol();

        if(board[s][0][c] == '-') //if the location in col 0 is avaliable, add it to arraylist (and so forth)
            arr.add(new Location (c,0,s));
        if(board[s][1][c] == '-')
            arr.add(new Location (c,1,s));
        if (board[s][2][c] == '-')
            arr.add(new Location (c,2,s));
        if (board[s][3][c] == '-' )
            arr.add(new Location (c,3,s));
        return arr;
    }

    public ArrayList<Location> rowThruMoves(char[][][] board)
    {
        ArrayList<Location> arr = new ArrayList<>();
        int r = firstMove.getRow();

        if (board[0][r][0] == '-')
            arr.add(new Location(0,r,0));
        if (board[1][r][1] == '-')
            arr.add(new Location(1,r,1));
        if (board[2][r][2] == '-')
            arr.add(new Location(2,r,2));
        if (board[3][r][3] == '-')
            arr.add(new Location(3,r,3));

        return arr;
    }

    public ArrayList<Location> colThruMoves(char[][][] board)
    {
        ArrayList<Location> arr = new ArrayList<>();
        int c = firstMove.getCol();
        int r = firstMove.getRow();

        if (board[0][r][c] != 'x' && board[0][r][c] != 'o') {
            arr.add(new Location(c, r, 0));
        }
        if (board[1][r][c] != 'x' && board[1][r][c] != 'o')
            arr.add(new Location(c,r,1));
        if (board[2][r][c] != 'x' && board[2][r][c] != 'o')
            arr.add(new Location(c,r,2));
        if (board[3][r][c] != 'x' && board[3][r][c] != 'o')
            arr.add(new Location(c,r,3));

        return arr;
    }

    public ArrayList<Location> backSlashDiagMove(char[][][] board)
    {
        ArrayList<Location> arr = new ArrayList<>();
        int s = firstMove.getSheet();

        if (board[s][0][0] == '-')
            arr.add(new Location(0,0,s));
        if (board[s][1][1] == '-')
            arr.add(new Location(1,1,s));
        if (board[s][2][2] == '-')
            arr.add(new Location(2,2,s));
        if (board[s][3][3] == '-')
            arr.add(new Location(3,3,s));

        return arr;
    }

    public ArrayList<Location> frontSlashDiagMove(char[][][] board)
    {
        ArrayList<Location> arr = new ArrayList<>();
        int s = firstMove.getSheet();

        if (board[s][0][3] == '-')
            arr.add(new Location(3,0,s));
        if (board[s][1][2] == '-')
            arr.add(new Location(2,1,s));
        if (board[s][2][1] == '-')
            arr.add(new Location(1,2,s));
        if (board[s][3][0] == '-')
            arr.add(new Location(0,3,s));

        return arr;
    }

    public ArrayList<Location> backSlashThruMove(char[][][] board)
    {
        ArrayList<Location> arr = new ArrayList<>();
        int s = 0;

        if (board[s][0][0] == '-')
            arr.add(new Location(0,0,s));
        if (board[s+1][1][1] == '-')
            arr.add(new Location(1,1,s+1));
        if (board[s+2][2][2] == '-')
            arr.add(new Location(2,2,s+2));
        if (board[s+3][3][3] == '-')
            arr.add(new Location(3,3,s+3));

        return arr;
    }

    public ArrayList<Location> frontSlashThruMove(char[][][] board)
    {
        ArrayList<Location> arr = new ArrayList<>();
        int s = 0;

        if (board[s][0][3] == '-')
            arr.add(new Location(3,0,s));
        if (board[s+1][1][2] == '-')
            arr.add(new Location(2,1,s+1));
        if (board[s+2][2][1] == '-')
            arr.add(new Location(1,2,s+2));
        if (board[s+3][3][0] == '-')
            arr.add(new Location(0,3,s+3));

        return arr;
    }

    public Location forceMove(char value, char[][][] board) //will only check for it;s opponents char, so that it doesnt move by accdient when situtation look like (eg. xx-o; shouldnt play there)
    {
        int count = 0;

        //col through
        for (int c=0;c<4;c++)
        {
            if (board[0][0][c] != '-') {
                System.out.println("board 0 r c count plus " + board[0][0][c]);
                count++;
            }
            if (board[1][1][c] != '-') {
                System.out.println("board 1 r c count plus " + board[1][1][c]);
                count++;
            }
            if (board[2][2][c] != '-') {
                System.out.println("board 2 r c count plus " + board[2][2][c]);
                count++;
            }
            if (board[3][3][c] != '-') {
                System.out.println("board 3 r c count plus " + board[3][3][c]);
                count++;
            }

            if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
            {
                System.out.println(" col thoruhg count was 3");
                return colThruForce(c, board);
            }
            count =0;
        }


        //row force win
        for (int s = 0; s < 4; s++) //will go thoruhg and check every row for a 3 in a row
        {
            for (int r = 0; r < 4; r++ )
            {
                if (board[s][r][0] == value) //if the space is taken, makes sure ai doesnt miss any wins too
                {
                    count++;
                    System.out.println("board s r 0 count plus " + board[s][r][0]);
                }
                if (board[s][r][1] == value )
                {
                    System.out.println("board s r 1 count plus "+board[s][r][1]);
                    count++;
                }
                if (board[s][r][2] == value)
                {
                    System.out.println("board s r 2 count plus " + board[s][r][2]);
                    count++;
                }
                if (board[s][r][3] == value)
                {
                    System.out.println("board s r 3 count plus "+ board[s][r][3]);
                    count++;
                }

                if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
                {
                    System.out.println("count was 3");
                    return rowForce(s,r,board);
                }
                count =0; //reset count after every row to make sure not double counting in a sheet
                //System.out.println(" count reset ");
            }
        }

        //colsheet blocking from here
        for (int s = 0; s < 4; s++)
        {
            for (int c = 0; c < 4; c++ )
            {
                if (board[s][0][c]  == value)
                {
                    count++;
                    System.out.println(" col board s r 0 count plus " + board[s][0][c]);
                }
                if (board[s][1][c]  == value) {
                    System.out.println(" col board s r 1 count plus "+board[s][1][c]);
                    count++;
                }
                if (board[s][2][c]  == value) {
                    System.out.println(" col board s r 2 count plus " + board[s][2][c]);
                    count++;
                }
                if (board[s][3][c] == value) {
                    System.out.println(" col board s r 3 count plus "+ board[s][3][c]);
                    count++;
                }

                if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
                {
                    System.out.println(" col count was 3");
                    return colForce(s,c,board);
                }
                count =0; //reset count after every row to make sure not double counting in a sheet
                //System.out.println(" count reset col ");
            }
        }

        //row through checks
        for (int r = 0; r < 4; r++ )
        {
            if (board[0][r][0]  == value)
                count++;
            if (board[1][r][1]  == value)
                count++;
            if (board[2][r][2]  == value)
                count++;
            if (board[3][r][3] == value)
                count++;

            if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
            {
                //System.out.println(" col count was 3");
                return rowThruForce(r,board);
            }
            count =0; //reset count after every row to make sure not double counting in a sheet
            //System.out.println(" count reset col ");
        }

        count = 0;

        //col through
        for (int c=0;c<4;c++)
        {
            if (board[0][0][c] == value) {
                System.out.println("board 0 0 c count plus " + board[0][0][c]);
                count++;
            }
            if (board[1][1][c] == value) {
                System.out.println("board 1 1 c count plus " + board[1][1][c]);
                count++;
            }
            if (board[2][2][c] == value) {
                System.out.println("board 2 2 c count plus " + board[2][2][c]);
                count++;
            }
            if (board[3][3][c] == value) {
                System.out.println("board 3 3 c count plus " + board[3][3][c]);
                count++;
            }

            if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
            {
                System.out.println(" col thoruhg count was 3");
                return colThruForce(c, board);
            }
            count =0;
        }
        //back slash thou
        for (int s = 0; s<4; s++)
        {
            if (board[s][0][0] == value)
                count++;
            if (board[s][1][1] == value)
                count++;
            if (board[s][2][2] == value)
                count++;
            if (board[s][3][3] == value)
                count++;

            if (count == 3)
            {
                return backslashDiagForce(s, board);
            }

            count = 0;
        }

        //front slash thu
        for (int s = 0; s<4; s++)
        {
            if (board[s][0][3] == value)
                count++;
            if (board[s][1][2] == value)
                count++;
            if (board[s][2][1] == value)
                count++;
            if (board[s][3][0] == value)
                count++;

            if (count == 3)
            {
                return frontSlashDiagForce(s,board);
            }

            count =0;
        }

        //backslashth
        if (board[0][0][0] == value)
            count++;
        if (board[1][1][1] == value)
            count++;
        if (board[2][2][2] == value)
            count++;
        if (board[3][3][3] == value)
            count++;

        if (count==3)
        {
            return backSlashThruForce(board);
        }

        count =0;

        //frontSlashThruMove
        if (board[0][0][3] == value)
            count++;
        if (board[0+1][1][2] == value)
            count++;
        if (board[0+2][2][1] == value)
            count++;
        if (board[0+3][3][0] == value)
            count++;

        if (count == 3)
        {
            return  frontSlashThruForce(board);
        }

        count =0;

        //col though same col diff row n sheet
        for (int c=0;c<4;c++)
        {
            for (int r=0;r<4;r++)
            {
                if (board[0][r][c] == value)
                    count++;
                if (board[1][r][c] == value)
                    count++;
                if (board[2][r][c] == value)
                    count++;
                if (board[3][r][c] == value)
                    count++;

                if (count==3)
                {
                    System.out.println("thru called");
                    return thru(r,c,board);
                }
                count=0;
            }
        }

        System.out.println("default location forcemove");
        return null; //default in case there is no force moves to take, number used in best move
    }

    public Location frontSlashDiagForce(int s, char[][][] board)
    {
        if (board[s][0][3] == '-')
            return (new Location(3,0,s));
        if (board[s][1][2] == '-')
            return(new Location(2,1,s));
        if (board[s][2][1] == '-')
            return(new Location(1,2,s));
        if (board[s][3][0] == '-')
            return(new Location(0,3,s));

        return null;
    }

    public Location rowForce(int s, int r, char[][][] board) //will check for the open spot and play there
    {
        if (board[s][r][0] == '-') //if the location in col 0 is avaliable, add it to arraylist (and so forth)
            return new Location (0,r,s);
        if (board[s][r][1] == '-')
            return new Location (1,r,1);
        if (board[s][r][2] == '-')
            return new Location (2,r,s);
        if (board[s][r][3] == '-')
            return new Location (3,r,s);

        System.out.println("default location rowforce");
        return null; //if for some reason doesnt work itll play in 000
    }

    public Location colForce(int s, int c, char[][][] board)
    {
        if(board[s][0][c] == '-') //if the location in col 0 is avaliable, add it to arraylist (and so forth)
            return new Location (c,0,s);
        if(board[s][1][c] == '-')
            return new Location (c,1,s);
        if (board[s][2][c] == '-')
            return new Location (c,2,s);
        if (board[s][3][c] == '-' )
            return new Location (c,3,s);

        System.out.println("col force default");
        return null; //if for some reason doesnt work itll play in 000
    }

    public Location rowThruForce(int r, char[][][] board)
    {
        if (board[0][r][0] == '-')
            return (new Location(0,r,0));
        if (board[1][r][1] == '-')
            return (new Location(1,r,1));
        if (board[2][r][2] == '-')
            return (new Location(2,r,2));
        if (board[3][r][3] == '-')
            return(new Location(3,r,3));

        return null;
    }

    public Location colThruForce(int c, char[][][] board)
    {
        if (board[0][0][c] == '-') {
            return (new Location(c, 0, 0));
        }
        if (board[1][1][c] == '-')
            return(new Location(c,1,1));
        if (board[2][2][c] == '-')
            return(new Location(c,2,2));
        if (board[3][3][c] == '-')
            return (new Location(c,3,3));

        return null;
    }

    public Location backslashDiagForce(int s, char[][][] board)
    {
        if (board[s][0][0] == '-')
            return(new Location(0,0,s));
        if (board[s][1][1] == '-')
            return (new Location(1,1,s));
        if (board[s][2][2] == '-')
            return(new Location(2,2,s));
        if (board[s][3][3] == '-')
            return(new Location(3,3,s));

        return null;
    }

    public Location backSlashThruForce(char[][][] board )
    {

        int s= 0;
        if (board[s][0][0] == '-')
            return (new Location(0,0,s));
        if (board[s+1][1][1] == '-')
            return(new Location(1,1,s+1));
        if (board[s+2][2][2] == '-')
            return(new Location(2,2,s+2));
        if (board[s+3][3][3] == '-')
            return(new Location(3,3,s+3));

        return null;
    }


    public Location frontSlashThruForce(char[][][] board)
    {
        int s= 0;
        if (board[s][0][3] == '-')
            return(new Location(3,0,s));
        if (board[s+1][1][2] == '-')
            return(new Location(2,1,s+1));
        if (board[s+2][2][1] == '-')
            return(new Location(1,2,s+2));
        if (board[s+3][3][0] == '-')
            return(new Location(0,3,s+3));

        return null;
    }

    public Location thru(int r, int c, char[][][] board)
    {
        if (board[0][r][c] == '-')
            return new Location (c,r,0);
        if (board[1][r][c] == '-')
            return new Location (c,r,1);
        if (board[2][r][c] == '-')
            return new Location (c,r,2);
        if (board[3][r][c] == '-')
            return new Location (c,r,3);

        return null;
    }


    public Location bestMove()
    {
        char[][][] boardB  = Board.getBoard();

        for(int sheet=0; sheet < 4; sheet++)
            for(int row=0; row < 4; row++)
                for(int col=0; col < 4; col++)
                {
                    if(board[sheet][row][col] != '-')
                        board1Ahead[sheet][row][col] = board[sheet][row][col];
                }


        if (forceMove(opponentName, board) != null &&forceMove(opponentName, board).getRow() != 999) //if default move (meaning no force moves to take)
        {
            System.out.println("entered froce move");
            return  forceMove(opponentName, board);
        }

        if (winType == row && !rowMoves(board).isEmpty())
        {
            return rowMoves(board).remove(0); // shouldnt replace on move, only adds moves to arraylist if they are avalibale spots
        }
        else if (winType == col && !colMoves(board).isEmpty())
        {
            return colMoves(board).remove(0);
        }
        else if (winType == rowThru && !rowThruMoves(board).isEmpty())
        {
            return rowThruMoves(board).remove(0);
        }
        else if (winType == colThru && !colThruMoves(board).isEmpty())
        {
            return colThruMoves(board).remove(0);
        }
        else if (winType == backslashdiag && !backSlashDiagMove(board).isEmpty())
        {
            return backSlashDiagMove(board).remove(0);
        }
        else if (winType == frontslashdiag &&  !frontSlashDiagMove(board).isEmpty())
        {
            return frontSlashDiagMove(board).remove(0);
        }
        else if (winType == backslashthru && !backSlashThruMove(board).isEmpty())
        {
            return backSlashThruMove(board).remove(0);
        }
        else if (winType == frontslashthu && !frontSlashThruMove(board).isEmpty())
        {
            return frontSlashThruMove(board).remove(0);
        }
        else
        {
            winType = (int)Math.floor(Math.random()*(8-1+1)+1); //reset the win type if you are blocked (mening ur arraylist in that wintype in empty)
            bestMove();
        }

        return generateRandomLocation(); //shouldnt even get here - added in because java yelling at me
    }

    public Location generateRandomLocation()
    {

        int rSheet = (int) (Math.random()*(3-0+1)+0);
        int rRow = (int) (Math.random()*(3-0+1)+0);
        int rCol = (int) (Math.random()*(3-0+1)+0);

        while (board[rSheet][rRow][rCol] != '-')
        {
            rSheet = (int) (Math.random()*(3-0+1)+0);
            rRow = (int) (Math.random()*(3-0+1)+0);
            rCol = (int) (Math.random()*(3-0+1)+0);

        }
        Location adding = new Location(rSheet, rRow, rCol);
        return adding;
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


    public void bestMoveOneAhead()
    {
        char[][][] boardB  = Board.getBoard();

        if (forceMove(opponentName, board) != null &&forceMove(opponentName, board).getRow() != 999) //if default move (meaning no force moves to take)
        {
            System.out.println("entered froce move");
            board1Ahead[forceMove(opponentName, board).getSheet()][forceMove(opponentName, board).getRow()][forceMove(opponentName, board).getCol()] = letter;

        }

        if (winType == row && !rowMoves(board).isEmpty())
        {
            board1Ahead[  rowMoves(board).remove(0).getSheet()][rowMoves(board).remove(0).getRow()][rowMoves(board).remove(0).getCol()] = letter;

        }
        else if (winType == col && !colMoves(board).isEmpty())
        {
            board1Ahead[  colMoves(board).remove(0).getSheet()][colMoves(board).remove(0).getRow()][colMoves(board).remove(0).getCol()] = letter;
        }
        else if (winType == rowThru && !rowThruMoves(board).isEmpty())
        {
            board1Ahead[  rowThruMoves(board).remove(0).getSheet()][rowThruMoves(board).remove(0).getRow()][rowThruMoves(board).remove(0).getCol()] = letter;
        }
        else if (winType == colThru && !colThruMoves(board).isEmpty())
        {
            board1Ahead[  colThruMoves(board).remove(0).getSheet()][colThruMoves(board).remove(0).getRow()][colThruMoves(board).remove(0).getCol()] = letter;
        }
        else if (winType == backslashdiag && !backSlashDiagMove(board).isEmpty())
        {
            board1Ahead[  backSlashDiagMove(board).remove(0).getSheet()][backSlashDiagMove(board).remove(0).getRow()][backSlashDiagMove(board).remove(0).getCol()] = letter;
        }
        else if (winType == frontslashdiag &&  !frontSlashDiagMove(board).isEmpty())
        {
            board1Ahead[  frontSlashDiagMove(board).remove(0).getSheet()][frontSlashDiagMove(board).remove(0).getRow()][frontSlashDiagMove(board).remove(0).getCol()] = letter;
        }
        else if (winType == backslashthru && !backSlashThruMove(board).isEmpty())
        {
            board1Ahead[  backSlashThruMove(board).remove(0).getSheet()][backSlashThruMove(board).remove(0).getRow()][backSlashThruMove(board).remove(0).getCol()] = letter;
        }
        else if (winType == frontslashthu && !frontSlashThruMove(board).isEmpty())
        {
            board1Ahead[  frontSlashThruMove(board).remove(0).getSheet()][frontSlashThruMove(board).remove(0).getRow()][frontSlashThruMove(board).remove(0).getCol()] = letter;
        }
        else
        {
            winType = (int)Math.floor(Math.random()*(8-1+1)+1); //reset the win type if you are blocked (mening ur arraylist in that wintype in empty)
            bestMove();
            Location random = generateRandomLocation();
            board1Ahead[random.getSheet()][random.getRow()][random.getCol()] = letter;

        }
    }

    public void bestMoveTwoAhead()
    {
        char[][][] boardB  = Board.getBoard();

        for(int sheet=0; sheet < 4; sheet++)
            for(int row=0; row < 4; row++)
                for(int col=0; col < 4; col++)
                {
                    if(board1Ahead[sheet][row][col] != '-')
                        board2Ahead[sheet][row][col] = board1Ahead[sheet][row][col];
                }

        if (forceMove(opponentName, board1Ahead) != null &&forceMove(opponentName, board1Ahead).getRow() != 999) //if default move (meaning no force moves to take)
        {
            System.out.println("entered froce move");
            board2Ahead[forceMove(opponentName, board1Ahead).getSheet()][forceMove(opponentName, board1Ahead).getRow()][forceMove(opponentName, board1Ahead).getCol()] = letter;

        }

        if (winType == row && !rowMoves(board).isEmpty())
        {
            board2Ahead[  rowMoves(board).remove(0).getSheet()][rowMoves(board).remove(0).getRow()][rowMoves(board).remove(0).getCol()] = letter;

        }
        else if (winType == col && !colMoves(board).isEmpty())
        {
            board2Ahead[  colMoves(board).remove(0).getSheet()][colMoves(board).remove(0).getRow()][colMoves(board).remove(0).getCol()] = letter;
        }
        else if (winType == rowThru && !rowThruMoves(board).isEmpty())
        {
            board2Ahead[  rowThruMoves(board).remove(0).getSheet()][rowThruMoves(board).remove(0).getRow()][rowThruMoves(board).remove(0).getCol()] = letter;
        }
        else if (winType == colThru && !colThruMoves(board).isEmpty())
        {
            board2Ahead[  colThruMoves(board).remove(0).getSheet()][colThruMoves(board).remove(0).getRow()][colThruMoves(board).remove(0).getCol()] = letter;
        }
        else if (winType == backslashdiag && !backSlashDiagMove(board).isEmpty())
        {
            board2Ahead[  backSlashDiagMove(board).remove(0).getSheet()][backSlashDiagMove(board).remove(0).getRow()][backSlashDiagMove(board).remove(0).getCol()] = letter;
        }
        else if (winType == frontslashdiag &&  !frontSlashDiagMove(board).isEmpty())
        {
            board2Ahead[  frontSlashDiagMove(board).remove(0).getSheet()][frontSlashDiagMove(board).remove(0).getRow()][frontSlashDiagMove(board).remove(0).getCol()] = letter;
        }
        else if (winType == backslashthru && !backSlashThruMove(board).isEmpty())
        {
            board2Ahead[  backSlashThruMove(board).remove(0).getSheet()][backSlashThruMove(board).remove(0).getRow()][backSlashThruMove(board).remove(0).getCol()] = letter;
        }
        else if (winType == frontslashthu && !frontSlashThruMove(board).isEmpty())
        {
            board2Ahead[  frontSlashThruMove(board).remove(0).getSheet()][frontSlashThruMove(board).remove(0).getRow()][frontSlashThruMove(board).remove(0).getCol()] = letter;
        }
        else
        {
            winType = (int)Math.floor(Math.random()*(8-1+1)+1); //reset the win type if you are blocked (mening ur arraylist in that wintype in empty)
            bestMove();
            Location random = generateRandomLocation();
            board2Ahead[random.getSheet()][random.getRow()][random.getCol()] = letter;

        }
    }
}
