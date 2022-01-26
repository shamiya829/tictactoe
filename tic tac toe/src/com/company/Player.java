package com.company;

public class Player implements PlayerInt{

    String name="";
    char letter;
    Player(String name, char letter)
    {
        this.name = name;
        this.letter = letter;
    }
    @Override
    public char getLetter() {
        return letter;
    }

    @Override
    public Location getMove(char[][][] board) {
        return null; // not sure what this methods used for
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void reset() {
    }
}
