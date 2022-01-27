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
        setSize(400,750);
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
        char[][][] board = game.getBoard();
    }


    public void paint(Graphics g)
    {
        Graphics b = buffer.createGraphics();
        b.setColor(Color.BLACK);
        b.fillRect(0,0,400,750);
        g.drawImage(buffer, 0, 0, null);

        for(int sheet=0;sheet<4;sheet++)
        {
            for(int row=0;row<4;row++)
            {
                for(int col=0;col<4;col++)
                {
                    //draw player moves (x and o here)
                }
            }
        }

        for (int y =50;y<700;y+=225) //boxes drawn
        {
            g.setColor(Color.WHITE);
            g.drawRect(100,y,200,200);
            for (int yx=150;yx<300;yx+=50) //verticle lines
            {
                g.drawLine(yx,y,yx,y+200);
            }
            for (int hx=y+50;hx<y+250;hx+=50)
            {
                g.drawLine(100,hx,300,hx);
            }
        }

        //lines
        //go thorugh board say at start of each row draw first line, if position equals
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
