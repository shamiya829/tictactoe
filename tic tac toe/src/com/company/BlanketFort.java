package com.company;

import java.util.ArrayList;

public class BlanketFort extends Game3
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
    char name = 'n';

    BlanketFort(char name)
    {
        this.name = name;
        winType = (int)Math.floor(Math.random()*(8-1+1)+1); //will pick a certain wintype at random
        System.out.println("win tyep number: "+winType);
    }

    public ArrayList<Location> rowMoves() //col values change to make a row win
    {
        char[][][] board = Board.getBoard();
        ArrayList<Location> arr = new ArrayList<>(); //arraylist of all values in the row of the first move
        int s = firstMove.getSheet();
        int r = firstMove.getRow();

                if(board[s][r][0] == '-') //if the location in col 0 is avaliable, add it to arraylist (and so forth)
                    arr.add(new Location (s,r,0));
                if(board[s][r][1] == '-')
                    arr.add(new Location (s,r,1));
                if (board[s][r][2] == '-')
                    arr.add(new Location (s,r,2));
                if (board[s][r][3] == '-' )
                    arr.add(new Location (s,r,3));
        return arr;
    }

    public ArrayList<Location> colMoves()
    {
        char[][][] board = Board.getBoard();
        ArrayList<Location> arr = new ArrayList<>();
        int s = firstMove.getSheet();
        int c = firstMove.getCol();

        if(board[s][0][c] == '-') //if the location in col 0 is avaliable, add it to arraylist (and so forth)
            arr.add(new Location (s,0,c));
        if(board[s][1][c] == '-')
            arr.add(new Location (s,1,c));
        if (board[s][2][c] == '-')
            arr.add(new Location (s,2,c));
        if (board[s][3][c] == '-' )
            arr.add(new Location (s,3,c));
        return arr;
    }

    public ArrayList<Location> rowThruMoves()
    {
        char[][][] board = Board.getBoard();
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

    public ArrayList<Location> colThruMoves()
    {
        char[][][] board = Board.getBoard();
        ArrayList<Location> arr = new ArrayList<>();
        int c = firstMove.getCol();
        int r = firstMove.getRow();

        if (board[0][r][c] != 'x' && board[0][r][c] != 'o') {
            arr.add(new Location(0, r, c));
        }
        if (board[1][r][c] != 'x' && board[1][r][c] != 'o')
            arr.add(new Location(1,r,c));
        if (board[2][r][c] != 'x' && board[2][r][c] != 'o')
            arr.add(new Location(2,r,c));
        if (board[3][r][c] != 'x' && board[3][r][c] != 'o')
            arr.add(new Location(3,r,c));

        return arr;
    }

    public ArrayList<Location> backSlashDiagMove()
    {
        char[][][] board = Board.getBoard();
        ArrayList<Location> arr = new ArrayList<>();
        int s = firstMove.getSheet();

        if (board[s][0][0] == '-')
            arr.add(new Location(s,0,0));
        if (board[s][1][1] == '-')
            arr.add(new Location(s,1,1));
        if (board[s][2][2] == '-')
            arr.add(new Location(s,2,2));
        if (board[s][3][3] == '-')
            arr.add(new Location(s,3,3));

        return arr;
    }

    public ArrayList<Location> frontSlashDiagMove()
    {
        char[][][] board = Board.getBoard();
        ArrayList<Location> arr = new ArrayList<>();
        int s = firstMove.getSheet();

        if (board[s][0][3] == '-')
            arr.add(new Location(s,0,3));
        if (board[s][1][2] == '-')
            arr.add(new Location(s,1,2));
        if (board[s][2][1] == '-')
            arr.add(new Location(s,2,1));
        if (board[s][3][0] == '-')
            arr.add(new Location(s,3,0));

        return arr;
    }

    public ArrayList<Location> backSlashThruMove()
    {
        char[][][] board = Board.getBoard();
        ArrayList<Location> arr = new ArrayList<>();
        int s = 0;

        if (board[s][0][0] == '-')
            arr.add(new Location(s,0,0));
        if (board[s+1][1][1] == '-')
            arr.add(new Location(s+1,1,1));
        if (board[s+2][2][2] == '-')
            arr.add(new Location(s+2,2,2));
        if (board[s+3][3][3] == '-')
            arr.add(new Location(s+3,3,3));

        return arr;
    }

    public ArrayList<Location> frontSlashThruMove()
    {
        char[][][] board = Board.getBoard();
        ArrayList<Location> arr = new ArrayList<>();
        int s = 0;

        if (board[s][0][3] == '-')
            arr.add(new Location(s,0,3));
        if (board[s+1][1][2] == '-')
            arr.add(new Location(s+1,1,2));
        if (board[s+2][2][1] == '-')
            arr.add(new Location(s+2,2,1));
        if (board[s+3][3][0] == '-')
            arr.add(new Location(s+3,3,0));

        return arr;
    }

    public Location forceMove()
    {
        char[][][] board = Board.getBoard();
        int count = 0;
        //row force win
        for (int s = 0; s < 4; s++) //will go thoruhg and check every row for a 3 in a row
        {
            for (int r = 0; r < 4; r++ )
            {
                if (board[s][r][0] != '-') //if the space is taken, makes sure ai doesnt miss any wins too
                {
                    count++;
                    System.out.println("board s r 0 count plus " + board[s][r][0]);
                }
                if (board[s][r][1] != '-') {
                    System.out.println("board s r 1 count plus "+board[s][r][1]);
                    count++;
                }
                if (board[s][r][2] != '-') {
                    System.out.println("board s r 2 count plus " + board[s][r][2]);
                    count++;
                }
                if (board[s][r][3] != '-') {
                    System.out.println("board s r 3 count plus "+ board[s][r][3]);
                    count++;
                }

                if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
                {
                    System.out.println("count was 3");
                    return rowForce(s,r);
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
                if (board[s][0][c] != '-')
                {
                    count++;
                    System.out.println(" col board s r 0 count plus " + board[s][0][c]);
                }
                if (board[s][1][c] != '-') {
                    System.out.println(" col board s r 1 count plus "+board[s][1][c]);
                    count++;
                }
                if (board[s][2][c] != '-') {
                    System.out.println(" col board s r 2 count plus " + board[s][2][c]);
                    count++;
                }
                if (board[s][3][c] != '-') {
                    System.out.println(" col board s r 3 count plus "+ board[s][3][c]);
                    count++;
                }

                if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
                {
                    System.out.println(" col count was 3");
                    return colForce(s,c);
                }
                count =0; //reset count after every row to make sure not double counting in a sheet
                System.out.println(" count reset col ");
            }
        }

        //row through checks
        for (int r = 0; r < 4; r++ )
        {
            if (board[0][r][0] != '-')
                count++;
            if (board[1][r][1] != '-')
                count++;
            if (board[2][r][2] != '-')
                count++;
            if (board[3][r][3] != '-')
                count++;

            if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
            {
                //System.out.println(" col count was 3");
                return rowThruForce(r);
            }
            count =0; //reset count after every row to make sure not double counting in a sheet
            //System.out.println(" count reset col ");
        }

        //col through
        for (int c=0;c<4;c++)
        {
            for (int r=0;r<4;r++)
            {
                if (board[0][r][c] != '-') {
                    System.out.println("board 0 r c count plus " + board[0][r][c]);
                    count++;
                }
                if (board[1][r][c] != '-') {
                    System.out.println("board 1 r c count plus " + board[1][r][c]);
                    count++;
                }
                if (board[2][r][c] != '-') {
                    System.out.println("board 2 r c count plus " + board[2][r][c]);
                    count++;
                }
                if (board[3][r][c] != '-') {
                    System.out.println("board 3 r c count plus " + board[3][r][c]);
                    count++;
                }

                if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
                {
                    System.out.println(" col thoruhg count was 3");
                    colThruForce(r,c);
                }

            }
            count =0;
        }

        System.out.println("default location forcemove");
        return new Location (999,999,999); //default in case there is no force moves to take, number used in best move


    }

    public Location rowForce(int s, int r) //will check for the open spot and play there
    {
        char[][][] board = Board.getBoard();
        if (board[s][r][0] == '-') //if the location in col 0 is avaliable, add it to arraylist (and so forth)
            return new Location (s,r,0);
        if (board[s][r][1] == '-')
            return new Location (s,r,1);
        if (board[s][r][2] == '-')
            return new Location (s,r,2);
        if (board[s][r][3] == '-')
            return new Location (s,r,3);

        System.out.println("default location rowforce");
        return new Location (0,0,0); //if for some reason doesnt work itll play in 000
    }

    public Location colForce(int s, int c)
    {
        char[][][] board = Board.getBoard();

        if(board[s][0][c] == '-') //if the location in col 0 is avaliable, add it to arraylist (and so forth)
            return new Location (s,0,c);
        if(board[s][1][c] == '-')
            return new Location (s,1,c);
        if (board[s][2][c] == '-')
            return new Location (s,2,c);
        if (board[s][3][c] == '-' )
            return new Location (s,3,c);

        return new Location (0,0,0); //if for some reason doesnt work itll play in 000
    }

    public Location rowThruForce(int r)
    {
        char[][][] board = Board.getBoard();

        if (board[0][r][0] == '-')
            return (new Location(0,r,0));
        if (board[1][r][1] == '-')
            return (new Location(1,r,1));
        if (board[2][r][2] == '-')
            return (new Location(2,r,2));
        if (board[3][r][3] == '-')
            return(new Location(3,r,3));

        return new Location (0,0,0);
    }

    public Location colThruForce(int r,int c)
    {
        char[][][] board = Board.getBoard();

        if (board[0][r][c] == '-') {
            return (new Location(0, r, c));
        }
        if (board[1][r][c] == '-')
            return(new Location(1,r,c));
        if (board[2][r][c] == '-')
            return(new Location(2,r,c));
        if (board[3][r][c] == '-')
            return (new Location(3,r,c));

        return new Location (0,0,0);
    }




    public Location bestMove()
    {
        if (forceMove().getRow() != 999) //if default move (meaning no force moves to take)
        {
            System.out.println("entered froce move");
            return  forceMove();
        }

        if (winType == row && !rowMoves().isEmpty())
        {
            return rowMoves().remove(0); // shouldnt replace on move, only adds moves to arraylist if they are avalibale spots
        }
        else if (winType == col && !colMoves().isEmpty())
        {
            return colMoves().remove(0);
        }
        else if (winType == rowThru && !rowThruMoves().isEmpty())
        {
            return rowThruMoves().remove(0);
        }
        else if (winType == colThru && !colThruMoves().isEmpty())
        {
            //System.out.println("colthrough called");
            Location removing  = colThruMoves().remove(0);
            //System.out.println("playing moves: " + removing.getSheet() + "r: " + removing.getRow() + "c:" + removing.getCol());
            return colThruMoves().remove(0);
        }
        else if (winType == backslashdiag && !backSlashDiagMove().isEmpty())
        {
            return backSlashDiagMove().remove(0);
        }
        else if (winType == frontslashdiag &&  !frontSlashDiagMove().isEmpty())
        {
            return frontSlashDiagMove().remove(0);
        }
        else if (winType == backslashthru && !backSlashThruMove().isEmpty())
        {
            return backSlashThruMove().remove(0);
        }
        else if (winType == frontslashthu && !frontSlashThruMove().isEmpty())
        {
            return frontSlashThruMove().remove(0);
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
}
