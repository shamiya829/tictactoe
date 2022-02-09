package com.company;

import java.lang.invoke.LambdaConversionException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Pillowtown extends Game3{
    Location location;
    char name;
    Location prevMove = generateRandomLocation();

    Pillowtown(char name) {
        location = generateRandomLocation();
        this.name = name;
    }

    public int checkCounter(){
        return 0;
    }


    public ArrayList<Location> check2s() {
        ArrayList<Location> arr = new ArrayList<>();
        return arr;
    }

    public ArrayList<Location> check3s() {
        ArrayList<Location> arr = new ArrayList<>();
        return arr;
    }

    public ArrayList<Location> check4s() {
        ArrayList<Location> arr = new ArrayList<>();
        return arr;
    }

    public Location bestMove(){
        if(check2s().size() == 2){

        }

        return location;
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

    public Location getLocation(){
        return location;
    }



    }



