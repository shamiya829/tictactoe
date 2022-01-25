package com.company;

public class Player implements PlayerInt{

    String name="";
    Player(String name)
    {
        this.name = name;
    }
    @Override
    public char getLetter() {
        return 0;
    }

    @Override
    public Location getMove(char[][][] board) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void reset() {

    }
}
