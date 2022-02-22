package com.company;

import java.lang.invoke.LambdaConversionException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Pillowtown extends Game3{
    Location location;
    char name;
    Location prevMove = generateRandomLocation();
    boolean twoInRow = false;
    boolean threeInRow = false;

    Pillowtown(char name) {
        location = generateRandomLocation();
        this.name = name;
    }

    public int checkCounter(){
        return 0;
    }



    public ArrayList<Location> check2s() {
        ArrayList<Location> arr = new ArrayList<>();
        arr.clear();
        if (prevMove.getSheet() > 0 && board[prevMove.getSheet() - 1][prevMove.getRow()][prevMove.getCol()] == '-') {
            System.out.println("1");
            arr.add(new Location(prevMove.getSheet() - 1,  prevMove.getRow(), prevMove.getCol()));
        }

        if (prevMove.getSheet() < 3 && board[prevMove.getSheet() + 1][prevMove.getRow()][prevMove.getCol()] == '-') {
            System.out.println("2");
            arr.add(new Location(prevMove.getSheet() + 1,  prevMove.getRow(), prevMove.getCol()));
        }

        if (prevMove.getRow() > 0 && board[prevMove.getSheet()][prevMove.getRow() - 1][prevMove.getCol()] == '-') {
            System.out.println("3");
            arr.add(new Location(prevMove.getSheet(),  prevMove.getRow() - 1, prevMove.getCol()));
        }

        if (prevMove.getRow() < 3 && board[prevMove.getSheet()][prevMove.getRow() + 1][prevMove.getCol()] == '-') {
            System.out.println("4");
            arr.add(new Location(prevMove.getSheet(),  prevMove.getRow() + 1, prevMove.getCol()));
        }

        if (prevMove.getCol() > 0 && board[prevMove.getSheet()][prevMove.getRow()][prevMove.getCol() - 1] == '-') {
            System.out.println("5");
            arr.add(new Location(prevMove.getSheet(),  prevMove.getRow(), prevMove.getCol() - 1));
        }

        if (prevMove.getCol() < 3 && board[prevMove.getSheet()][prevMove.getRow()][prevMove.getCol()] == '-') {
            System.out.println("6");
            arr.add(new Location(prevMove.getSheet(),  prevMove.getRow(), prevMove.getCol() + 1));
        }
        return arr;
    }

    public ArrayList<Location> check3s() {
        ArrayList<Location> arr = new ArrayList<>();
        arr.clear();
        if(twoInRow){
            if (prevMove.getSheet() > 0 && board[prevMove.getSheet() - 1][prevMove.getRow()][prevMove.getCol()] == '-') {
                arr.add(new Location(prevMove.getSheet() - 1,  prevMove.getRow(), prevMove.getCol()));
            }

            if (prevMove.getSheet() < 3 && board[prevMove.getSheet() + 1][prevMove.getRow()][prevMove.getCol()] == '-') {
                arr.add(new Location(prevMove.getSheet() + 1,  prevMove.getRow(), prevMove.getCol()));
            }

            if (prevMove.getRow() > 0 && board[prevMove.getSheet()][prevMove.getRow() - 1][prevMove.getCol()] == '-') {
                arr.add(new Location(prevMove.getSheet(),  prevMove.getRow() - 1, prevMove.getCol()));
            }

            if (prevMove.getRow() < 3 && board[prevMove.getSheet()][prevMove.getRow() + 1][prevMove.getCol()] == '-') {
                arr.add(new Location(prevMove.getSheet(),  prevMove.getRow() + 1, prevMove.getCol()));
            }

            if (prevMove.getCol() > 0 && board[prevMove.getSheet()][prevMove.getRow()][prevMove.getCol() - 1] == '-') {
                arr.add(new Location(prevMove.getSheet(),  prevMove.getRow(), prevMove.getCol() - 1));
            }

            if (prevMove.getCol() < 3 && board[prevMove.getSheet()][prevMove.getRow()][prevMove.getCol()] == '-') {
                arr.add(new Location(prevMove.getSheet(),  prevMove.getRow(), prevMove.getCol() + 1));
            }
        }

        return arr;
    }

    public ArrayList<Location> check4s() {
        ArrayList<Location> arr = new ArrayList<>();
        arr.clear();
        if(threeInRow){
            if (prevMove.getSheet() > 0 && board[prevMove.getSheet() - 1][prevMove.getRow()][prevMove.getCol()] == '-') {
                arr.add(new Location(prevMove.getSheet() - 1,  prevMove.getRow(), prevMove.getCol()));
            }

            if (prevMove.getSheet() < 3 && board[prevMove.getSheet() + 1][prevMove.getRow()][prevMove.getCol()] == '-') {
                arr.add(new Location(prevMove.getSheet() + 1,  prevMove.getRow(), prevMove.getCol()));
            }

            if (prevMove.getRow() > 0 && board[prevMove.getSheet()][prevMove.getRow() - 1][prevMove.getCol()] == '-') {
                arr.add(new Location(prevMove.getSheet(),  prevMove.getRow() - 1, prevMove.getCol()));
            }

            if (prevMove.getRow() < 3 && board[prevMove.getSheet()][prevMove.getRow() + 1][prevMove.getCol()] == '-') {
                arr.add(new Location(prevMove.getSheet(),  prevMove.getRow() + 1, prevMove.getCol()));
            }

            if (prevMove.getCol() > 0 && board[prevMove.getSheet()][prevMove.getRow()][prevMove.getCol() - 1] == '-') {
                arr.add(new Location(prevMove.getSheet(),  prevMove.getRow(), prevMove.getCol() - 1));
            }

            if (prevMove.getCol() < 3 && board[prevMove.getSheet()][prevMove.getRow()][prevMove.getCol()] == '-') {
                arr.add(new Location(prevMove.getSheet(),  prevMove.getRow(), prevMove.getCol() + 1));
            }
        }
        return arr;
    }

    public Location bestMove(){


        if(threeInRow == true){
            if(check4s().size() == 0){
                threeInRow = false;
            }
            else if(check4s().size() == 1) {
                System.out.println("check 4 size 1");
                prevMove = check4s().get(0);
                return check4s().get(0);
            }
            else if(check4s().size() > 1) {
                Location adding = check4s().get((int) (Math.random() * (check4s().size() - 0 + 1) + 0));
                prevMove = adding;
                return adding;
            }
        }

        if(twoInRow == true){
            //because you will have three in a row after this move

            if(check3s().size() == 0){
                twoInRow = false;
            }
            else if(check3s().size() == 1) {
                threeInRow = true;
                System.out.println("check 3 size 1");
                Location adding = check3s().get(0);
                prevMove = adding;
                return adding;
            }
            else if(check3s().size() > 1) {
                threeInRow = true;
                System.out.println("check 3 more than 1");
                Location adding = check3s().get((int) (Math.random() * (check3s().size() - 0 + 1) + 0));
                prevMove = adding;
                return  adding;
            }
        }

        else if (!check2s().isEmpty()) //meaning 2nd move
        {
            System.out.println("check2s runnig");
            Location adding = check2s().get(0);
            prevMove = adding;
            System.out.println("s: "+check2s().get(0).getSheet()+ " r: " + check2s().get(0).getRow()+ " c: " + check2s().get(0).getCol());
            return adding;
        }
        else {
            System.out.println("random location being generalted");
            return generateRandomLocation();
        }



        /*if(twoInRow == false){
            twoInRow = true;
            if(check2s().size() == 0){
                twoInRow = false;
            }
            if(check2s().size() == 1) {
                return check2s().get(0);
            }
             if(check2s().size() > 1) {

             }
        }*/

        System.out.println("best move");

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
        Location adding = new Location(rSheet, rRow, rCol);
        prevMove = adding;
        return adding;
    }

    public Location getLocation(){
        return location;
    }



    }



