package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Board extends JPanel implements MouseListener {

    //to do: set up g font and write string to tell players who's turn it is
    //set up starting screen that lets you pick if u wanna play player v player or player v ai (adjust code for ai)

    char[][][] board;
    Game3 game;
    Player player1,player2;
    boolean player1turn;

    BufferedImage buffer,x,o;
    Board()
    {
        setSize(400,975);
        addMouseListener(this);

        try
        {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
            x = ImageIO.read((new File("images\\x.png")));
            o = ImageIO.read(new File("images\\o.png"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        reset();
    }

    public void reset()
    {
        this.game = new Game3();
        this.board = game.getBoard();
        this.player1 = new Player("player1",'x');
        this.player2 = new Player("player2",'o');
        this.player1turn = true;
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
        for(int sheet=50;sheet<950;sheet+=225) //will run through each box drawn onto board
        {
            if (s>3)
                break;
            for(int row=sheet+50;row<sheet+250;row+=50)
            {
                if (r>3)
                    break;
                for(int col=150;col<300;col+=50)
                {
                    if (c>3)
                        break;
                    if (board[s][r][c] != '-')
                    {
                         if (board[s][r][c] == 'x')
                         {
                             g.drawImage(x,row+1,col+1,null);
                         }
                         else
                             g.drawImage(o,row+1,col+1,null);
                    }
                    c++;
                }
                r++;
            }
            s++;
        }

        if (player1turn)
        {
            //draw string "player one (x) play your turn"
        }
        else
        {

        }
            //draw string "player two (o) play your turn"
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    int clicked = 0;
    @Override
    public void mousePressed(MouseEvent e) //use this one
    {
        int x = e.getX();
        int y = e.getY();

        int s=0,r=0,c=0;
        for(int sheet=50;sheet<950;sheet+=225) //will run through each box drawn onto board
        {
            if (s>3)
                break;
            for(int row=sheet+50;row<sheet+250;row+=50)
            {
                if (r>3)
                    break;
                for(int col=150;col<300;col+=50)
                {
                    if (c>3)
                        break;
                    if ((x>=col && x <= col+50) && (y>=row && y<=row+50)) //if you clicked the box
                    {
                        if (board[s][r][c] != '-') {
                            if (player1turn) {
                                board[s][r][c] = 'x';
                                System.out.println("");
                            } else
                                board[s][r][c] = 'o';
                        }
                    }
                    c++;
                }
                r++;
            }
            s++;
        }
        repaint();
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
