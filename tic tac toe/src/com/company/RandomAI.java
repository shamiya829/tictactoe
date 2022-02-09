package com.company;

import java.util.Random;

public class RandomAI extends Game3{
    RandomAI (){
        super();
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
        return new Location(rSheet, rRow, rCol);
    }
}
