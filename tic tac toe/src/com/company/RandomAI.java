package com.company;

import java.util.Random;

public class RandomAI extends Game3{
    RandomAI (){
        super();
    }

    Random rand= new Random();

    int rSheet = rand.nextInt((3) + 1) ;
    int rRow = rand.nextInt((3) + 1) ;
    int rCol = rand.nextInt((3) + 1) ;

    Location ai = new Location(rSheet,rRow,rCol);

    public Location generateRandomLocation(){
        while (board[rSheet][rRow][rCol] != '-' || board[rSheet][rRow][rCol] != ' ' ){
            rSheet = rand.nextInt((3) + 1) ;
            rRow = rand.nextInt((3) + 1) ;
            rCol = rand.nextInt((3) + 1) ;
        }

        ai.setSheet(rSheet);
        ai.setCol(rCol);
        ai.setRow(rRow);

        return ai;
    }
}
