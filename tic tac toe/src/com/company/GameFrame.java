package com.company;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
    public GameFrame(String title)
    {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();

        Board p = new Board();

        Insets insets = getInsets();
        int width = p.getWidth() + insets.left + insets.right;
        int height = p.getHeight() + insets.top + insets.bottom;
        add(p);
        setPreferredSize(new Dimension(width,height));
        setLayout(null);
        pack();
        setVisible(true);
    }

}