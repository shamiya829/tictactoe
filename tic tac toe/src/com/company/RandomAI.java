package com.company;

import java.util.Random;

public class RandomAI extends Game3{
    RandomAI (){
        super();
    }

    Random rand= new Random();

    int rSheet = (int)(Math.random()*(3-0+1)+0);
    int rRow = (int)(Math.random()*(3-0+1)+0);
    int rCol = (int)(Math.random()*(3-0+1)+0);

    Location ai = new Location(rCol,rRow,rSheet);

    public Location generateRandomLocation(){
        while (board[rSheet][rRow][rCol] != '-')
        {
            System.out.println("loops");
            rSheet = (int)(Math.random()*(3-0+1)+0);
            rRow = (int)(Math.random()*(3-0+1)+0);
            rCol = (int)(Math.random()*(3-0+1)+0);
        }

        ai.setSheet(rSheet);
        ai.setCol(rCol);
        ai.setRow(rRow);

        return ai;
    }
}
