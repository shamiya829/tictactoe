package com.company;

public class Location
{
    int sheet;
    int row;
    int col;

    /** Sets x, y, z to the recieved values */
    public Location(int sheet,int row,int col)
    {
        this.col    = col;
        this.row    = row;
        this.sheet     = sheet;
    }

    public int getCol()
    {   return col;   }


    public int getRow()
    {   return row;   }


    public int getSheet()
    {   return sheet;   }

    public void setSheet(int sheet){
        this.sheet = sheet;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setCol(int col){
        this.col = col;
    }

    public String toString()
    {   return "(x=" + col+",y="+row+ ",z="+sheet+ ")";   }
}
