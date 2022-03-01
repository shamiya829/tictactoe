package com.company;
import java.util.ArrayList;

public class QuiltCity extends Game3
{
    Location firstMove = new Location(0, 0, 0);
    Location prevMove = firstMove;
    boolean doubleTwos = false;
    boolean first = true;
    char letter = 'n';
    char opponentName = 'n';
    String name = "Quilt City";

    QuiltCity(char letter)
    {
        this.letter = letter;

        if (letter == 'o')
            opponentName = 'x';
        else
            opponentName = 'o';
    }

    public Location makes2() {

        if (prevMove.getSheet() > 0 && board[prevMove.getSheet() - 1][prevMove.getRow()][prevMove.getCol()] == '-') {
            return (new Location(prevMove.getCol(),prevMove.getRow(),prevMove.getSheet() - 1));
        }

        if (prevMove.getSheet() < 3 && board[prevMove.getSheet() + 1][prevMove.getRow()][prevMove.getCol()] == '-') {
            return (new Location(prevMove.getSheet() + 1,  prevMove.getRow(), prevMove.getCol()));
        }

        if (prevMove.getRow() > 0 && board[prevMove.getSheet()][prevMove.getRow() - 1][prevMove.getCol()] == '-') {
            return (new Location(prevMove.getSheet(),  prevMove.getRow() - 1, prevMove.getCol()));
        }

        if (prevMove.getRow() < 3 && board[prevMove.getSheet()][prevMove.getRow() + 1][prevMove.getCol()] == '-') {
            return (new Location(prevMove.getSheet(),  prevMove.getRow() + 1, prevMove.getCol()));
        }

        if (prevMove.getCol() > 0 && board[prevMove.getSheet()][prevMove.getRow()][prevMove.getCol() - 1] == '-') {
            return (new Location(prevMove.getSheet(),  prevMove.getRow(), prevMove.getCol() - 1));
        }

        if (prevMove.getCol() < 3 && board[prevMove.getSheet()][prevMove.getRow()][prevMove.getCol()] == '-') {
            return (new Location(prevMove.getSheet(),  prevMove.getRow(), prevMove.getCol() + 1));
        }

        return generateRandomLocation();
    }

   public Location bestTwoCrossMove(){

       return null;
   }

   public Location installAllCorners(){
       return null;
   }

   public Location bestMove(){

       if (forceMove(letter) != null &&forceMove(letter).getRow() != 999) //if default move (meaning no force moves to take)
       {
           return  forceMove(letter);
       }

       if (forceMove(opponentName) != null &&forceMove(opponentName).getRow() != 999) //if default move (meaning no force moves to take)
       {
           return  forceMove(opponentName);
       }

       if(first == true){
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
                   }
               }
           }
       }
       if (board[maxScore.getChecking().getSheet()][maxScore.getChecking().getRow()][maxScore.getChecking().getCol()]=='-')
           return maxScore.checking;
       else
           return generateRandomLocation();

       return generateRandomLocation();
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



    public Location forceMove(char value) //will only check for it;s opponents char, so that it doesnt move by accdient when situtation look like (eg. xx-o; shouldnt play there)
    {
        char[][][] board = Board.getBoard();
        int count = 0;

        //col through
        for (int c=0;c<4;c++)
        {
            if (board[0][0][c] != '-') {
                count++;
            }
            if (board[1][1][c] != '-') {
                count++;
            }
            if (board[2][2][c] != '-') {
                count++;
            }
            if (board[3][3][c] != '-') {
                count++;
            }

            if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
            {
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
                }
                if (board[s][r][1] == value )
                {
                    count++;
                }
                if (board[s][r][2] == value)
                {
                    count++;
                }
                if (board[s][r][3] == value)
                {
                    count++;
                }

                if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
                {
                    return rowForce(s,r);
                }
                count =0; //reset count after every row to make sure not double counting in a sheet
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
                }
                if (board[s][1][c]  == value) {
                    count++;
                }
                if (board[s][2][c]  == value) {
                    count++;
                }
                if (board[s][3][c] == value) {
                    count++;
                }

                if (count==3) //if there is a 3 in a row, run row force (becuase checking for row 3s)
                {
                    return colForce(s,c);
                }
                count =0; //reset count after every row to make sure not double counting in a sheet
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

            if (count==3)
            {
                return rowThruForce(r);
            }
            count =0;
        }

        count = 0;

        //col through
        for (int c=0;c<4;c++)
        {
            if (board[0][0][c] == value) {
                count++;
            }
            if (board[1][1][c] == value) {
                count++;
            }
            if (board[2][2][c] == value) {
                count++;
            }
            if (board[3][3][c] == value) {
                count++;
            }

            if (count==3)
            {
                return colThruForce(c);
            }
            count =0;
        }

        //backSlashThru
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

        //backSlashThroughSheet
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

        //frontSlashThruSheet
        if (board[0][0][3] == value)
            count++;
        if (board[1][1][2] == value)
            count++;
        if (board[2][2][1] == value)
            count++;
        if (board[3][3][0] == value)
            count++;

        if (count == 3)
        {
            return  frontSlashThruForce();
        }

        count =0;

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
                    return thru(r,c);
                }
                count=0;
            }
        }
        return null;
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



}
