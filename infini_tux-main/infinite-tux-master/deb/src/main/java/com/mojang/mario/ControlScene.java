package com.mojang.mario;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;

import com.mojang.mario.level.BgLevelGenerator;
import com.mojang.mario.level.LevelGenerator;
import com.mojang.mario.sprites.Mario;
import java.awt.Color;

public class ControlScene extends Scene
{
    private MarioComponent component;
    private int tick;
    private BgRenderer bgLayer0;
    private BgRenderer bgLayer1;
    private Color translucent = new Color(0,0,0,0);
    
    public ControlScene(MarioComponent component, GraphicsConfiguration gc)
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
        g.drawImage(Art.showControls, 0, yo, translucent,null);
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


    private boolean wasDown = true;
    public void tick()
    {
        tick++;
        if (!wasDown && keys[Mario.KEY_JUMP])
        {
            component.startGame();
        }
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
