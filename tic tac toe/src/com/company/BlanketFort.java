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
        winType = (int)Math.floor(Math.random()*(6-1+1)+1); //will pick a certain wintype
    }

    public ArrayList<Location> rowMoves()
    {
        ArrayList<Location> arr = new ArrayList<>();
        for(int s = 0; s < 4; s++)
        {
            for(int r = 0; r < 4; r++)
            {
                //x
                if(board[s][r][0] != '-')
                    arr.add(new Location (s,r,0));
                if(board[s][r][1] != '-')
                    arr.add(new Location (s,r,1));
                if (board[s][r][2] != '-')
                    arr.add(new Location (s,r,2));
                if (board[s][r][3] != '-' )
                    arr.add(new Location (s,r,3));

                }
            }
        return arr;
    }


    public Location getFirstMove() {
        return firstMove;
    }

    public Location bestMove()
    {

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
