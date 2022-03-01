package com.company;

public class Scores
{
    //will based on each win condition calculate how good each move is (higher the number the better the score) - used in
    // blanketfort in order to determine the *best move* instead of defaulting to straight line
    int score =0;
    char [][][] board = new char[4][4][4];
    char letter;
    Location checking;

    Scores(char [][][] b, char letter, Location checking)
    {

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                for (int k = 0; k < 4; k++) {

                    board[i][j][k] = b[i][j][k];
                }
            }
        }

        this.letter = letter;
        this.checking = checking;
        score = 0;
    }

    public int getScore()
    {
        displayBoard(board);
        score=colCount() +rowCount() + diagonalCountSheet()
                +rowThruCount() +colThruCount()
                +colSameThruCount() + specialDiagonalBackSlash()
                +specialDiagonalForwardSlash() + diagonalCountSheetForwardSlash();

        return score;
    }

    public Location getChecking() {
        return checking;
    }

    public void setChecking(Location checking) {
        this.checking = checking;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int colCount() //col same sheet upwards n downwards- goes up in the same sheet checking if it ever encounters itself, adds to score
    {
        int s = checking.getSheet();
        int r = checking.getRow();
        int c = checking.getCol();
        int count = 0;

        while (r>=0)
        {
            if (board[s][r][c] == letter)
            {
                count++;
            }
            r--;
        }

        r  = checking.getRow();
        while (r<4)
        {
            if (board[s][r][c] == letter)
            {
                count++;
            }
            r++;
        }
        System.out.println("col count score: " + (int)(Math.pow(3,count)));
        return (int)(Math.pow(3,count));
    }

    public int rowCount () //check row left n right
    {
        int s = checking.getSheet();
        int r = checking.getRow();
        int c = checking.getCol();
        int count = 0;

        while (c>=0)
        {
            if (board[s][r][c] == letter)
            {
                count++;
            }
            c--;
        }

        c  = checking.getRow();
        while (c<4)
        {
            if (board[s][r][c] == letter)
            {
                count++;
            }
            c++;
        }

        System.out.println("row count score: " + (int)(Math.pow(3,count)));
        return (int)(Math.pow(3,count));
    }

    public int diagonalCountSheet() //will calculate scored based on diagonals on sheet \  (RECODE FOR THOSE THAT DONT LIE ON DIAGONAL)
    {
        int s = checking.getSheet();
        int r = checking.getRow();
        int c = checking.getCol();
        int count = 0;


        if (board[s][0][0] == letter)
            count++;
        if (board[s][1][1] == letter)
            count++;
        if (board[s][2][2] == letter)
            count++;
        if (board[s][3][3] == letter)
            count++;

        System.out.println("diagonal 1 count score: " + (int)(Math.pow(3,count)));
        return (int)(Math.pow(3,count));
    }

    public int diagonalCountSheetForwardSlash() // / diagonal on each sheet
    {
        int s = checking.getSheet();
        int count =0;
        if (board[s][0][3] == letter)
            count++;
        if (board[s][1][2] == letter)
            count++;
        if (board[s][2][1] == letter)
            count++;
        if (board[s][3][0] == letter)
            count++;

        System.out.println("diagonalCountSheetForwardSlash: " + (int)(Math.pow(3,count)));
        return (int)(Math.pow(3,count));
    }

    public int rowThruCount()
    {
        int count =0;
        int r = checking.getRow();

        if (board[0][r][0] ==letter)
            count++;
        if (board[1][r][1] == letter)
            count++;
        if (board[2][r][2] == letter)
            count++;
        if (board[3][r][3] == letter)
            count++;

        System.out.println("rowthru count score: " + (int)(Math.pow(3,count)));
        return (int)(Math.pow(3,count));
    }

    public int colThruCount()
    {
        int c = checking.getCol();
        int count =0;

        if (board[0][0][c] == letter) {
            count++;
        }
        if (board[1][1][c] == letter)
            count++;
        if (board[2][2][c] == letter)
            count++;
        if (board[3][3][c] == letter)
            count++;

        return (int)(Math.pow(3,count));
    }

    public int colSameThruCount()
    {
        int count =0;
        int r = checking.getRow();
        int c= checking.getCol();

        for (int s = 0; s <4; s++)
        {
            if (board[s][r][c] == letter)
            {
                count++;
            }
        }
        System.out.println("colSameThruCount: " + (int)(Math.pow(3,count)));
        return (int)(Math.pow(3,count));
    }

    public int specialDiagonalBackSlash() // \ diagonal
    {
        int s= 0;
        int count =0;
        if (board[s][0][0] == letter)
            count++;
        if (board[s+1][1][1] == letter)
            count++;
        if (board[s+2][2][2] == letter)
            count++;
        if (board[s+3][3][3] == letter)
            count++;

        return (int)(Math.pow(3,count));
    }

    public int specialDiagonalForwardSlash() // / diagonal
    {
        int s= 0;
        int count = 0;

        if (board[s][0][3] == letter)
            count++;
        if (board[s+1][1][2] == letter)
            count++;
        if (board[s+2][2][1] == letter)
            count++;
        if (board[s+3][3][0] == letter)
            count++;

        return (int)(Math.pow(3,count));
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

    //row thu done
    //col thru done
    //col same thru done

    //special diagonal \ done
    //special diagonal / done
    // diagonal sheet / done
}
