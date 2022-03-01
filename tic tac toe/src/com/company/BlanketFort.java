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
    char letter = 'n';
    boolean first = true;
    char opponentName = 'n';

    BlanketFort(char name)
    {
        this.letter = name;
        winType = (int)Math.floor(Math.random()*(8-1+1)+1); //will pick a certain wintype at random
        System.out.println("win tyep number: "+winType);

        if (letter == 'o')
            opponentName = 'x';
        else
            opponentName = 'o';
    }

    public ArrayList<Location> rowMoves() //col values change to make a row win
    {
        char[][][] board = Board.getBoard();
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

    public ArrayList<Location> colMoves()
    {
        char[][][] board = Board.getBoard();
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

    public ArrayList<Location> backSlashDiagMove()
    {
        char[][][] board = Board.getBoard();
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

    public ArrayList<Location> frontSlashDiagMove()
    {
        char[][][] board = Board.getBoard();
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

    public ArrayList<Location> backSlashThruMove()
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

    public ArrayList<Location> frontSlashThruMove()
    {
        char[][][] board = Board.getBoard();
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

    public Location forceMove(char value) //will only check for it;s opponents char, so that it doesnt move by accdient when situtation look like (eg. xx-o; shouldnt play there)
    {
        char[][][] board = Board.getBoard();
        int count = 0;

        //col through
        for (int c=0;c<4;c++)
        {
            if (board[0][0][c] != '-') {
                //System.out.println("board 0 r c count plus " + board[0][0][c]);
                count++;
            }
            if (board[1][1][c] != '-') {
                //System.out.println("board 1 r c count plus " + board[1][1][c]);
                count++;
            }
            if (board[2][2][c] != '-') {
                //System.out.println("board 2 r c count plus " + board[2][2][c]);
                count++;
            }
            if (board[3][3][c] != '-') {
                //System.out.println("board 3 r c count plus " + board[3][3][c]);
                count++;
            }

            if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
            {
                //System.out.println(" col thoruhg count was 3");
                return colThruForce(c);
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
                    //System.out.println("board s r 0 count plus " + board[s][r][0]);
                }
                if (board[s][r][1] == value )
                {
                    //System.out.println("board s r 1 count plus "+board[s][r][1]);
                    count++;
                }
                if (board[s][r][2] == value)
                {
                   // System.out.println("board s r 2 count plus " + board[s][r][2]);
                    count++;
                }
                if (board[s][r][3] == value)
                {
                    //System.out.println("board s r 3 count plus "+ board[s][r][3]);
                    count++;
                }

                if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
                {
                    //System.out.println("count was 3");
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
                if (board[s][0][c]  == value)
                {
                    count++;
                    //System.out.println(" col board s r 0 count plus " + board[s][0][c]);
                }
                if (board[s][1][c]  == value) {
                    //System.out.println(" col board s r 1 count plus "+board[s][1][c]);
                    count++;
                }
                if (board[s][2][c]  == value) {
                    //System.out.println(" col board s r 2 count plus " + board[s][2][c]);
                    count++;
                }
                if (board[s][3][c] == value) {
                    //System.out.println(" col board s r 3 count plus "+ board[s][3][c]);
                    count++;
                }

                if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
                {
                  //  System.out.println(" col count was 3");
                    return colForce(s,c);
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
                return rowThruForce(r);
            }
            count =0; //reset count after every row to make sure not double counting in a sheet
            //System.out.println(" count reset col ");
        }

        count = 0;

        //col through
        for (int c=0;c<4;c++)
        {
                if (board[0][0][c] == value) {
                   // System.out.println("board 0 0 c count plus " + board[0][0][c]);
                    count++;
                }
                if (board[1][1][c] == value) {
                   // System.out.println("board 1 1 c count plus " + board[1][1][c]);
                    count++;
                }
                if (board[2][2][c] == value) {
                    //System.out.println("board 2 2 c count plus " + board[2][2][c]);
                    count++;
                }
                if (board[3][3][c] == value) {
                   // System.out.println("board 3 3 c count plus " + board[3][3][c]);
                    count++;
                }

                if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
                {
                    //System.out.println(" col thoruhg count was 3");
                    return colThruForce(c);
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
                return backslashDiagForce(s);
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
                return frontSlashDiagForce(s);
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
            return backSlashThruForce();
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
            return  frontSlashThruForce();
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
                    //System.out.println("thru called");
                    return thru(r,c);
                }
                count=0;
            }
        }

       // System.out.println("default location forcemove");
        return null; //default in case there is no force moves to take, number used in best move
    }

    public Location frontSlashDiagForce(int s)
    {
        char[][][] board = Board.getBoard();

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

    public Location rowForce(int s, int r) //will check for the open spot and play there
    {
        char[][][] board = Board.getBoard();

        if (board[s][r][0] == '-') //if the location in col 0 is avaliable, add it to arraylist (and so forth)
            return new Location (0,r,s);
        if (board[s][r][1] == '-')
            return new Location (1,r,1);
        if (board[s][r][2] == '-')
            return new Location (2,r,s);
        if (board[s][r][3] == '-')
            return new Location (3,r,s);

        //System.out.println("default location rowforce");
        return null; //if for some reason doesnt work itll play in 000
    }

    public Location colForce(int s, int c)
    {
        char[][][] board = Board.getBoard();

        if(board[s][0][c] == '-') //if the location in col 0 is avaliable, add it to arraylist (and so forth)
            return new Location (c,0,s);
        if(board[s][1][c] == '-')
            return new Location (c,1,s);
        if (board[s][2][c] == '-')
            return new Location (c,2,s);
        if (board[s][3][c] == '-' )
            return new Location (c,3,s);

       // System.out.println("col force default");
        return null; //if for some reason doesnt work itll play in 000
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

        return null;
    }

    public Location colThruForce(int c)
    {
        //System.out.println("BOB LIKES BURGERS");
        char[][][] board = Board.getBoard();

       // System.out.println("BOB LIKES BURGERS");

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

    public Location backslashDiagForce(int s)
    {
        char[][][] board = Board.getBoard();

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

    public Location backSlashThruForce()
    {
        char[][][] board = Board.getBoard();

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


    public Location frontSlashThruForce()
    {
        char[][][] board = Board.getBoard();

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

    public Location thru(int r, int c)
    {
        char[][][] board = Board.getBoard();

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
        char[][][] board = Board.getBoard();

        if (forceMove(letter) != null &&forceMove(letter).getRow() != 999) //if default move (meaning no force moves to take)
        {
            //	System.out.println("entered force move");
            return  forceMove(letter);
        }

        if (forceMove(opponentName) != null &&forceMove(opponentName).getRow() != 999) //if default move (meaning no force moves to take)
        {
            //	System.out.println("entered force move");
            return  forceMove(opponentName);
        }

        if(first){
            first = false;
            return firstMove;
        }

        Scores maxScore = new Scores(board,letter,new Location(0,0,0));
        System.out.println("max score: " + maxScore.getScore());
        for(int sheet=0; sheet < 4; sheet++)
        {
            for (int row = 0; row < 4; row++)
            {
                for (int col = 0; col < 4; col++)
                {
                    Scores moveCheckScore = new Scores(board,letter,new Location(col,row,sheet));

                    System.out.println("checking score: " + moveCheckScore.getScore());


                    if (board[sheet][row][col] == '-')  //only check scores for open spots
                    {
                        if (moveCheckScore.getScore() > maxScore.getScore())  //if the score of a spot is bigger than ur current max, make it the max
                        {
                            System.out.println("checking set");
                            maxScore.setChecking(moveCheckScore.getChecking());
                            maxScore.setScore(moveCheckScore.getScore());
                        }
                    }//does this work?
                }
            }
        }
        return maxScore.getChecking(); //return the best move!



        /*
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
        }*/

        //return generateRandomLocation(); //shouldnt even get here - added in because java yelling at me
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
