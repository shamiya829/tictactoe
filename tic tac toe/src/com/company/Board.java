package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class Board extends JPanel implements MouseListener, KeyListener,Runnable {


    char[][][] board;
    Game3 game;
    Player player1,player2;
    boolean player1turn;
    int selection1,selection2;
    public final int person=1,ai=2,pillow=3;
    RandomAI randomAi1,randomAi2;
    Pillowtown pillowtownx,pillowtowno;
    public int delaytime;
    int currentgamerunning=0;
    int gamesplaying =999;
    int delaytimewon;

    Scanner keyboard = new Scanner(System.in);

    BufferedImage buffer,x,o;
    Board()
    {
        setSize(600,975);
        addMouseListener(this);
        addKeyListener(this);

        try
        {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
            URL xUrl = new URL("https://github.com/shamiya829/tictactoe/blob/main/images/x.png?raw=true");
            URL oUrl = new URL("https://github.com/shamiya829/tictactoe/blob/main/images/o.png?raw=true");

            x = ImageIO.read(xUrl);
            o = ImageIO.read(oUrl);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        reset();
        Thread t = new Thread(this);
        t.start();
    }

    public void reset()
    {
        this.game = new Game3();
        this.board = game.getBoard();
        this.player1 = new Player("player1",'x');
        this.player2 = new Player("player2",'o');
        this.player1turn = true;

        if ((((selection1==ai && selection2==ai) || (selection1==pillow && selection2==pillow)) && currentgamerunning==gamesplaying) || selection1!=ai&&selection2!=ai) //if its not ai v ai reset choices or if it is an ai and ur on the last game, then reset
        {
            this.selection1 = 0;
            this.selection2 = 0;
            this.gamesplaying =999;
            this.currentgamerunning=0;
            this.delaytimewon=3000;
        }
        if(selection1==person||selection2==person)
        {
            this.selection1 = 0;
            this.selection2 = 0;
        }

        //make sure selection stays the same after ai games
        this.randomAi1 = new RandomAI();
        this.randomAi2 = new RandomAI();
        this.pillowtownx = new Pillowtown('x');
        this.pillowtowno = new Pillowtown('o');
        //repaint();
    }


    public void runAIRandomGame()
    {
        if ((selection1 == ai && selection2 ==ai) || (selection1 == pillow && selection2 == pillow))
        {

            if (gamesplaying == 999)
            {
                System.out.println("Enter how many games you want played?: ");
                gamesplaying = keyboard.nextInt();
                System.out.println("Enter how many seconds to wait (in milliseconds)?: ");
                delaytime = keyboard.nextInt();
                if (gamesplaying>1) {
                    System.out.println("Enter how many seconds to wait after a game(in milliseconds)?: ");
                    delaytimewon = keyboard.nextInt();
                }

            }


            if (currentgamerunning> gamesplaying)
            {
                reset();
                return;
            }

                if (game.won() == 'n')
                {
                    aimove();
                    System.out.println("RANDOM AI MOVING: " + player1turn);
                    //paint board
                }

                if (game.won()!='n')  //if someone won
                {
                    currentgamerunning++; //change number of games won
                    System.out.println("currentgame: "+currentgamerunning);
                    try {
                        Thread.sleep(delaytimewon);
                    } catch (InterruptedException m) {
                        m.printStackTrace();
                    } //sleep to see board\
                    repaint();
                    reset(); //reset game
                }
                //displayBoard(board);
            }
        }

        public void runPillowTown()
        {
            if(selection1==pillow && selection2==pillow)
            {
                //ai v ai
            }
        }



    public void paint(Graphics g)
    {
        Font k = new Font("Dialog", Font.PLAIN, 25);

        Graphics b = buffer.createGraphics();
        b.setFont(k);
        b.setColor(Color.BLACK);
        b.fillRect(0,0,600,975);



        for (int y =50;y<950;y+=225) //boxes drawn
        {
            b.setColor(Color.WHITE);
            b.drawRect(100,y,200,200);
            for (int yx=150;yx<300;yx+=50) //verticle lines
            {
                b.drawLine(yx,y,yx,y+200);
            }
            for (int hx=y+50;hx<y+250;hx+=50) //horizontal lines
            {
                b.drawLine(100,hx,300,hx);
            }
        }

        if (selection1==0) //if they havnt chosen who to be the first player
        {
            b.setColor(Color.gray);
            b.fillRect(350,300,200,145);
            b.setColor(Color.black);
            b.drawString("pick 1st player: ",350,330);

            b.fillRect(350,350,10,10);
            b.drawString("user",370,360);

            b.fillRect(350,380,10,10);
            b.drawString("random ai",370,390);

            b.fillRect(350,410,10,10);
            b.drawString("pillowtown",370,425);
        }

        if (selection2==0)
        {
            b.setColor(Color.gray);
            b.fillRect(350,450,200,145);
            b.setColor(Color.black);
            b.drawString("pick 2nd player: ",350,480);

            b.fillRect(350,500,10,10);
            b.drawString("user",370,510);

            b.fillRect(350,530,10,10);
            b.drawString("random ai",370,545);

            b.fillRect(350,560,10,10);
            b.drawString("pillowtown",370,580);
        }


        //let the pick a player for each, change ifs to check if no winner and turn == 'x' 'o' if the selection turn is ai run that class
        //if its a player run that if, if turn== 'x' (player

        //System.out.println("\n\nplayer x turn: " + player1turn);

        int s=0,r=0,c=0; //used to check array values
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
                    if (board[s][r][c] != '-')
                    {
                        if (board[s][r][c] == 'x')
                        {
                            //System.out.print("x painted");
                            b.drawImage(x, col + 1, row + 1, null);
                        }
                        else
                        {
                            //System.out.print("o painted");
                            b.drawImage(o, col + 1, row + 1, null);
                        }
                    }
                    c++;
                }
                r++;
            }
            s++;
        }

       // System.out.println("\n\nEnter how h")


       /* if ((game.won()=='n') && selection1!=0 && selection2!=0) {
            System.out.print("loops ai win");
            aimove();
        }*/

        if (game.won()=='n') //no winner shows whos turn it is
        {

            if(game.checkTie()){
                b.setColor(Color.green);
                b.fillRect(350, 100, 200, 100);
                b.setColor(Color.black);
                b.drawString("There's a tie!", 370, 160);
                /*try {
                    Thread.sleep(delaytimewon);
                } catch (InterruptedException m) {
                    m.printStackTrace();
                }
                reset();*/
            }

            else if (player1turn)
            {
                //draw string "player one (x) play your turn"
                b.setColor(Color.cyan);
                b.fillRect(350,100,200,100);
                b.setColor(Color.black);
                b.drawString("X's move.",370,160);
            }
            else
            {
                b.setColor(Color.red);
                b.fillRect(350,100,200,100);
                b.setColor(Color.black);
                b.drawString("O's move.",370,160);
            }
        }
        else //there is a winner
        {
            char winner = game.won();

            if(winner=='x')
            {
                b.setColor(Color.green);
                b.fillRect(350, 100, 200, 100);
                b.setColor(Color.black);
                b.drawString("X won!", 370, 160);
                g.drawImage(buffer, 0, 0, null);
            }

            if(winner=='o')
            {
                b.setColor(Color.green);
                b.fillRect(350, 100, 200, 100);
                b.setColor(Color.black);
                b.drawString("O won!", 370, 160);
                g.drawImage(buffer, 0, 0, null);
            }
            System.out.println("winer!");

        }
        g.drawImage(buffer, 0, 0, null);
    }

    Location move;


    public void aigamexmove()
    {
        System.out.println("making new move x");
        move = randomAi1.generateRandomLocation();

        //System.out.println("sheet: "+move.getSheet()+" row: " + move.getRow()+" col: " + move.getCol() );
        if (selection1==ai)
        {
            if (board[move.getSheet()][move.getRow()][move.getCol()] != '-') {
                move = randomAi1.generateRandomLocation();
                if (board[move.getSheet()][move.getRow()][move.getCol()] != '-') {
                    move = randomAi1.generateRandomLocation();
                    if (board[move.getSheet()][move.getRow()][move.getCol()] != '-') {
                        move = randomAi1.generateRandomLocation();
                        if (board[move.getSheet()][move.getRow()][move.getCol()] != '-') {
                            move = randomAi1.generateRandomLocation();
                        }
                    }
                }
            }
        }
        else
        {
            move = pillowtownx.bestMove();
        }

        board[move.getSheet()][move.getRow()][move.getCol()] = 'x';

        player1turn = false;

    }

    public void aigameomove()
    {
        System.out.println("making new move o");

        if (selection2==ai)
        {
            move = randomAi2.generateRandomLocation();

            if (board[move.getSheet()][move.getRow()][move.getCol()] != '-') {
                move = randomAi1.generateRandomLocation();
                if (board[move.getSheet()][move.getRow()][move.getCol()] != '-') {
                    move = randomAi1.generateRandomLocation();
                    if (board[move.getSheet()][move.getRow()][move.getCol()] != '-') {
                        move = randomAi1.generateRandomLocation();
                        if (board[move.getSheet()][move.getRow()][move.getCol()] != '-') {
                            move = randomAi1.generateRandomLocation();
                        }
                    }
                }
            }
        }
        else
        {
            System.out.println("pillow best move called");
             move = pillowtowno.bestMove();
        }

        board[move.getSheet()][move.getRow()][move.getCol()] = 'o';
        player1turn = true;
    }



    public void aimove() //ai move when playing against user
    {

        if (player1turn && (selection1==ai || selection2 == pillow))
            aigamexmove();
        else if (selection2==ai || selection2 == pillow)
            aigameomove();
        /*if((selection1==ai && selection2==ai) || (selection1==pillow && selection2==pillow))
        {
            System.out.println("IF WORKS");
            if(player1turn)
            {
                aigamexmove();
            }

            else
            {
                System.out.println("calling ai move o");
                aigameomove();
            }
        }/*
       /* else if (selection1==ai && selection2!=0)
        {
            Location move = randomAi1.generateRandomLocation();

            if (board[move.getSheet()][move.getRow()][move.getCol()]!='-')
                move= randomAi1.generateRandomLocation();

            board[move.getSheet()][move.getRow()][move.getCol()] = 'x';
            player1turn = false;
            //generate random ai location
            //seperate location values and adjust board for 'x'
        }
        else if (selection2==ai)
        {
            Location move = randomAi2.generateRandomLocation();
            System.out.println("s: " +move.getSheet());
            System.out.println("r: " +move.getRow());
            System.out.println("c: " +move.getCol());
            if (board[move.getSheet()][move.getRow()][move.getCol()]!='-')
                move= randomAi2.generateRandomLocation();
            board[move.getSheet()][move.getRow()][move.getCol()] = 'o';
            System.out.print("AI MOVESSSS");
            player1turn = true;
            //generate random ai location
            //seperate location values and adjust board for 'o'
        }
        //repaint();*/
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) //use this one
    {
        //System.out.print("player one turn: " + player1turn);
        int x = e.getX();
        int y = e.getY();

        System.out.println("x clicked: " + x);
        System.out.println("y clicked: " + y);

        System.out.println(game.won());

        if (selection1==0)
        {
            if ((x>=350&&x<=360) && (y>=350&&y<=360)) //if they selected user for first player (x)
            {
                selection1 = person;
            }
            else if ((x>=350&&x<=360) && (y>=380&&y<=390))
            {
                selection1 = ai;
            }
            else if ((x>=350&&x<=360) && (y>=410&&y<=420))
            {
                selection1 = pillow;
            }

        }
        if (selection2==0)
        {
            if ((x>=350&&x<=360) && (y>=500&&y<=510)) //if they selected user for first player (x)
            {
                selection2 = person;
            }
            else if ((x>=350&&x<=360) && (y>=530&&y<=540))
            {
                selection2 = ai;
            }
            else if ((x>=350&&x<=360) && (y>=560&&y<=570))
            {
                selection2 = pillow;
            }

        }
       // System.out.println("game won: " + game.won());
        else if (game.won()=='n')
        {
            if ((player1turn&& selection1==person) || (!player1turn && selection2==person)) //if x's turn is user input or o's turn is userinput do this. if it is not the persons
            {

                int s = 0, r = 0, c = 0;
                for (int sheet = 50; sheet <= 950; sheet += 225) //will run through each box drawn onto board
                {
                    r = 0;
                    //System.out.println("S : " + s);
                    //System.out.println("going through sheet");

                    if (s > 3)
                        break;

                    for (int row = sheet; row <= sheet + 200; row += 50)
                    {
                        c = 0;
                        //System.out.println("going through row number: " + r + " on sheet: " + s);

                        if (r > 3)
                            break;

                        for (int col = 100; col <= 300; col += 50)
                        {
                           // System.out.println("going through col");
                            if (c > 3)
                                break;
                            if ((x >= col && x <= col + 50) && (y >= row && y <= row + 50)) //if you clicked the box
                            {
                                if (board[s][r][c] == '-')
                                {
                                    if ((selection1 == person) && player1turn)
                                    {
                                        board[s][r][c] = 'x';
                                        player1turn = false;
                                    }
                                    else
                                    {
                                        board[s][r][c] = 'o';
                                        player1turn = true;
                                        //System.out.println("o placed");
                                    }
                                   //player1turn = !player1turn;

                                }
                            }
                            c++;
                        }
                        r++;
                    }
                    s++;
                }
            }

        }

        System.out.println("\nout of loop");

        if (!(player1turn && (selection2 == ai || selection2 == pillow)) && selection1 == person)
        {
            System.out.print("selection 2 pillow");
            aimove();
        }
        else if (player1turn && (selection1 == ai || selection2 == pillow) && selection2 == person)
        {
            aimove();
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

    @Override
    public void keyTyped(KeyEvent e)
    {
        char dir = e.getKeyChar();
        if (dir =='r')
        {
            reset();
            repaint();
            displayBoard(board);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }


    private final int updatesPerSecond = 50;
    private long updateCount;

    @Override
    public void run ()
    {
        int waitToUpdate = (1000/updatesPerSecond);
        long startTime = System.nanoTime();

        while(true)
        {
            boolean shouldRepaint = false;
            long currentTime = System.nanoTime();
            long updatesNeeded = (((currentTime-startTime)/1000000))/ waitToUpdate;

            for (long x = updateCount; x < updatesNeeded; x++)
            {
                //update();
                shouldRepaint = true;
                updateCount++;
            }

            if (shouldRepaint) {
                repaint();
                runAIRandomGame();
                try {
                    Thread.sleep(delaytime);
                } catch (InterruptedException m) {
                    m.printStackTrace();
                }
            }

            try
            {
                Thread.sleep(5);
            }
            catch (Exception e)
            {
                System.out.print("Error sleeping in run method: " + e.getMessage());
            }
        }
    }
}
