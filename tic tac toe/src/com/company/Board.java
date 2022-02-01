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
        setSize(600,975);
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
        b.fillRect(0,0,600,975);
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

        if (!game.won())
        {
            for (int sheet = 50; sheet <= 950; sheet += 225) //will run through each box drawn onto board
            {
                r = 0;
                if (s > 3)
                    break;

                for (int row = sheet; row <= sheet + 200; row += 50) {
                    c = 0;
                    if (r > 3)
                        break;
                    for (int col = 100; col <= 300; col += 50) {
                        if (c > 3)
                            break;
                        if (board[s][r][c] != '-') {
                            if (board[s][r][c] == 'x') {
                                g.drawImage(x, col + 1, row + 1, null);
                            } else
                                g.drawImage(o, col + 1, row + 1, null);
                        }
                        c++;
                    }
                    r++;
                }
                s++;
            }

            Font k = new Font("Dialog", Font.PLAIN, 20);
            g.setFont(k);
            if (player1turn)
            {
                //draw string "player one (x) play your turn"
                g.setColor(Color.cyan);
                g.fillRect(350,100,200,100);
                g.setColor(Color.black);
                g.drawString("Player 1 (x) your move.",350,160);
            }
            else
            {
                g.setColor(Color.red);
                g.fillRect(350,100,200,100);
                g.setColor(Color.black);
                g.drawString("Player 2 (o) your move.",350,160);
            }
        }
        else
        {

        }

            //draw string "player two (o) play your turn"
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) //use this one
    {
        int x = e.getX();
        int y = e.getY();

        System.out.println("x clicked: " + x);
        System.out.println("y clicked: " + y);

        int s=0,r=0,c=0;
        for(int sheet=50;sheet<=950;sheet+=225) //will run through each box drawn onto board
        {
            r=0;
            System.out.println("S : " + s);
            System.out.println("going through sheet");

            if (s>3)
                break;

            for(int row=sheet;row<=sheet+200;row+=50)
            {
                c=0;
                System.out.println("going through row number: " + r + " on sheet: " + s);

                if (r>3)
                    break;

                for(int col=100;col<=300;col+=50)
                {
                    System.out.println("going through col");
                    if (c>3)
                        break;
                    if ((x>=col && x <= col+50) && (y>=row && y<=row+50)) //if you clicked the box
                    {
                        if (board[s][r][c] == '-')
                        {
                            if (player1turn)
                            {
                                board[s][r][c] = 'x';
                                System.out.println("x placed");
                                player1turn = !player1turn;
                            }
                            else
                            {
                                board[s][r][c] = 'o';
                                player1turn = !player1turn;
                                System.out.println("o placed");
                            }
                        }
                    }
                    c++;
                }
                r++;
            }
            s++;
        }
        System.out.println("out of loop");
        displayBoard(board);
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
