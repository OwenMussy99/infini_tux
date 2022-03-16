package com.mojang.mario;

import java.awt.*;
import javax.swing.*;

public class FullScreenFrameLauncher
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Infinite Tux");
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
        GraphicsDevice device = env.getDefaultScreenDevice();
        device.setFullScreenWindow(frame);  
        MarioComponent mario = new MarioComponent(320*frame.getHeight()/240, frame.getHeight());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout( new GridBagLayout() );
        frame.add(mario, new GridBagConstraints());        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        mario.start();
        frame.addKeyListener(mario);
        frame.addFocusListener(mario);
    }
}
