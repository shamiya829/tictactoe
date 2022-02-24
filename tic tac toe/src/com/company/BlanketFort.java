package com.company;

import java.util.ArrayList;

public class BlanketFort extends Game3
{
    Location firstMove= generateRandomLocation();
    int movenumber = 0;
    int winType = 4; //going to store wintype for straight line
    public final int row = 1;
    public final int col = 2;
    public final int rowThru = 3;
    public final int colThru = 4;
    public final int backslashdiag = 5;
    public final int frontslashdiag = 6;
    public final int backslashthru = 7;
    public final int frontslashthu =8;
    char name = 'n';

    BlanketFort(char name)
    {
        this.name = name;
        //winType = (int)Math.floor(Math.random()*(8-1+1)+1); //will pick a certain wintype at random
        System.out.println("win tyep number: "+winType);
    }

    public ArrayList<Location> rowMoves() //col values change to make a row win
    {
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
        ArrayList<Location> arr = new ArrayList<>();
        int s = firstMove.getSheet();
        int c = firstMove.getCol();

        if(board[s][0][c] != '-') //if the location in col 0 is avaliable, add it to arraylist (and so forth)
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

        if (board[0][r][c] != 'x' && board[0][0][c] != 'o') {
            System.out.println("added 0 sheet");
            //System.out.println("\n\n");
            arr.add(new Location(0, r, c));
            //displayBoard(board);
        }
        if (board[0][r][c] != 'x' && board[0][0][c] != 'o')
            arr.add(new Location(1,r,c));
        if (board[0][r][c] != 'x' && board[0][0][c] != 'o')
            arr.add(new Location(2,r,c));
        if (board[0][r][c] != 'x' && board[0][0][c] != 'o')
            arr.add(new Location(3,r,c));

        return arr;
    }

    public ArrayList<Location> backSlashDiagMove()
    {
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
        ArrayList<Location> arr = new ArrayList<>();
        int s = firstMove.getSheet();

        if (board[s][0][3] == '-')
            arr.add(new Location(s,0,0));
        if (board[s][1][2] == '-')
            arr.add(new Location(s,1,1));
        if (board[s][2][1] == '-')
            arr.add(new Location(s,2,2));
        if (board[s][3][0] == '-')
            arr.add(new Location(s,3,3));

        return arr;
    }

    public ArrayList<Location> backSlashThruMove()
    {
        ArrayList<Location> arr = new ArrayList<>();
        int s = 0;

        if (board[s][0][0] == '-')
            arr.add(new Location(s,0,0));
        if (board[s+1][1][1] == '-')
            arr.add(new Location(s,1,1));
        if (board[s+2][2][2] == '-')
            arr.add(new Location(s,2,2));
        if (board[s+3][3][3] == '-')
            arr.add(new Location(s,3,3));

        return arr;
    }

    public ArrayList<Location> frontSlashThruMove()
    {
        ArrayList<Location> arr = new ArrayList<>();
        int s = 0;

        if (board[s][0][3] == '-')
            arr.add(new Location(s,0,0));
        if (board[s+1][1][2] == '-')
            arr.add(new Location(s,1,1));
        if (board[s+2][2][1] == '-')
            arr.add(new Location(s,2,2));
        if (board[s+3][3][0] == '-')
            arr.add(new Location(s,3,3));

        return arr;
    }

    public Location getFirstMove() {
        return firstMove;
    }



    public Location bestMove()
    {
        if (movenumber ==0)
        {
            movenumber++;
            return firstMove;
        }
        else if (winType == row)
        {
            return rowMoves().remove(0); // shouldnt replace on move, only adds moves to arraylist if they are avalibale spots
        }
        else if (winType == col)
        {
            return colMoves().remove(0);
        }
        else if (winType == rowThru)
        {
            return rowThruMoves().remove(0);
        }
        else if (winType == colThru)
        {
            System.out.println("colthrough called");
            Location removing  = colThruMoves().remove(0);
            System.out.println("playing moves: " + removing.getSheet() + "r: " + removing.getRow() + "c:" + removing.getCol());
            return colThruMoves().remove(0);
        }
        else if (winType == backslashdiag)
        {
            return backSlashDiagMove().remove(0);
        }
        else if (winType == frontslashdiag)
        {
            return frontSlashDiagMove().remove(0);
        }
        else if (winType == backslashthru)
        {
            return backSlashThruMove().remove(0);
        }
        else if (winType == frontslashthu)
        {
            return frontSlashThruMove().remove(0);
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
