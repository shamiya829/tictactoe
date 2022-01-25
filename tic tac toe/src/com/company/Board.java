package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Board extends JPanel implements MouseListener {

    BufferedImage buffer;
    Board()
    {
        setSize(1000,700);
        addMouseListener(this);

        try
        {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void reset()
    {
        Game game = new Game();
        String[][][] board = game.getBoard();
    }


    public void paint(Graphics g)
    {
        for (int s=0;s<3;s++)
        {

        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) //use this one
    {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
