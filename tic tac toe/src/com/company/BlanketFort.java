package com.company;

import java.util.ArrayList;

public class BlanketFort extends Game3
{
    Location firstMove= generateRandomLocation();
    int winType = 0; //going to store wintype for straight line
    public final int row = 1;
    public final int col = 2;
    public final int rowThru = 3;
    public final int colThru = 4;
    public final int diagonalSheet = 5;
    public final int getDiagonalThruSheet = 6;

    BlanketFort()
    {
        winType = (int)Math.floor(Math.random()*(6-1+1)+1); //will pick a certain wintype at random
    }

    public ArrayList<Location> rowMoves() //col values change to make a row win
    {
        ArrayList<Location> arr = new ArrayList<>(); //arraylist of all values in the row of the first move
        int s = firstMove.getSheet();
        int r = firstMove.getRow();

                if(board[s][r][0] != '-') //if the location in col 0 is avaliable, add it to arraylist (and so forth)
                    arr.add(new Location (s,r,0));
                if(board[s][r][1] != '-')
                    arr.add(new Location (s,r,1));
                if (board[s][r][2] != '-')
                    arr.add(new Location (s,r,2));
                if (board[s][r][3] != '-' )
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
        if(board[s][1][c] != '-')
            arr.add(new Location (s,1,c));
        if (board[s][2][c] != '-')
            arr.add(new Location (s,2,c));
        if (board[s][3][c] != '-' )
            arr.add(new Location (s,3,c));
        return arr;
    }



    public Location getFirstMove() {
        return firstMove;
    }

    public Location bestMove()
    {
        if (winType == row)
        {
            return rowMoves().remove(0); // shouldnt replace on move, only adds moves to arraylist if they are avalibale spots
        }
        if (winType == col)
        {
            return colMoves().remove(0);
        }
        if (winType == rowThru)
        {
        }
        if (winType == colThru)
        {
        }
        if (winType == diagonalSheet)
        {
        }
        if (winType == getDiagonalThruSheet)
        {
        }
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
}
