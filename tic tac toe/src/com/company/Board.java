package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Board extends JPanel implements MouseListener {

    char[][][] board;
    Game game = new Game();

    BufferedImage buffer;
    Board()
    {
        setSize(400,975);
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
        this.board = game.getBoard();
    }


    public void paint(Graphics g)
    {
        Graphics b = buffer.createGraphics();
        b.setColor(Color.BLACK);
        b.fillRect(0,0,400,975);
        g.drawImage(buffer, 0, 0, null);


        for (int y =50;y<950;y+=225) //boxes drawn
        {
            g.setColor(Color.WHITE);
            g.drawRect(100,y,200,200);
            for (int yx=150;yx<300;yx+=50) //verticle lines
            {
                g.drawLine(yx,y,yx,y+200);
            }
            for (int hx=y+50;hx<y+250;hx+=50) //horizontal lines
            {
                g.drawLine(100,hx,300,hx);
            }
        }

        int s=0,r=0,c=0; //used to check array values
       /* for(int sheet=50;sheet<700;sheet+=225) //will run through each box drawn onto board
        {
            for(int row=sheet+50;row<sheet+250;row+=50)
            {
                for(int col=150;col<300;col+=50)
                {
                    if (board[s][r][c] != '-')
                    {
                         //draw corripoinding player stuff
                    }
                    c++;
                }
                r++;
            }
            s++;
        }*/

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
