package com.mojang.mario;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;

import com.mojang.mario.level.BgLevelGenerator;
import com.mojang.mario.level.LevelGenerator;
import com.mojang.mario.sprites.Mario;
import java.awt.Color;
import javax.swing.JOptionPane;

public class TitleScene extends Scene
{
    private MarioComponent component;
    private int tick;
    private BgRenderer bgLayer0;
    private BgRenderer bgLayer1;
    private Color translucent = new Color(0,0,0,0);
    
    public TitleScene(MarioComponent component, GraphicsConfiguration gc)
    {
        this.component = component;
        bgLayer0 = new BgRenderer(BgLevelGenerator.createLevel(2048, 15, false, LevelGenerator.TYPE_OVERGROUND), gc, 320, 240, 1);        
        bgLayer1 = new BgRenderer(BgLevelGenerator.createLevel(2048, 15, true, LevelGenerator.TYPE_OVERGROUND), gc, 320, 240, 2);
    }

    public void init()
    {
        Art.startMusic(4);
    }

    public void render(Graphics g, float alpha)
    {
        bgLayer0.setCam(tick+160, 0);
        bgLayer1.setCam(tick+160, 0);
        bgLayer1.render(g, tick, alpha);
        bgLayer0.render(g, tick, alpha);
        int yo = 16-Math.abs((int)(Math.sin((tick+alpha)/6.0)*8));
        g.drawImage(Art.logo, 0, yo, translucent,null);
        g.drawImage(Art.titleScreen, 0, 120, translucent, null);
    }

    @SuppressWarnings("unused")
	private void drawString(Graphics g, String text, int x, int y, int c)
    {
        char[] ch = text.toCharArray();
        for (int i = 0; i < ch.length; i++)
        {
            g.drawImage(Art.font[ch[i] - 32][c], x + i * 8, y,translucent, null);
        }
    }
    
    public static void controlBox(String controlMsg, String titleBar) {
    	// Create a message box to house the controls of the game.
    	JOptionPane.showMessageDialog(null, controlMsg, "" + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }


    private boolean wasDown = true;
    public void tick()
    {
        tick++;
        if (!wasDown && keys[Mario.KEY_JUMP])
        {
        	controlBox("S - Jump\n Left Arrow - Left\n Right Arrow - Right\n Down Arrow - Crouch\n"
        			+ " A - Use PowerUp\n" + "Q - Quit game", "Controls"); // Trigger popup window with controls labeled.
            component.startGame();
        }
        /*if (!wasDown && keys[Mario.KEY_CONTROLS]) {
        	controlBox("S - Jump\n Left Arrow - Left\n Right Arrow - Right\n Down Arrow - Crouch\n"
        			+ " A - Use PowerUp", "Controls");
        }*/
        if (keys[Mario.KEY_JUMP])
        {
            wasDown = false;
        }
        
    }

    public float getX(float alpha)
    {
        return 0;
    }

    public float getY(float alpha)
    {
        return 0;
    }

}
