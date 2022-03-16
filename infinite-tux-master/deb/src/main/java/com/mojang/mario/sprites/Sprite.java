package com.mojang.mario.sprites;

import java.awt.Graphics;
import java.awt.Image;

import com.mojang.mario.level.SpriteTemplate;
import com.mojang.sonar.SoundSource;
import java.awt.Color;

public class Sprite implements SoundSource
{
    public static SpriteContext spriteContext;
    
    public float xOld, yOld, x, y, xa, ya;
    
    public int xPic, yPic;
    public int wPic = 32;
    public int hPic = 32;
    public int xPicO, yPicO;
    public boolean xFlipPic = false;
    public boolean yFlipPic = false;
    public Image[][] sheet;
    public boolean visible = true;
    
    public int layer = 1;

    public SpriteTemplate spriteTemplate;
    private Color translucent = new Color(0, 0, 0, 0);
    private Color opaque = new Color(0, 0, 0, 0.0f);
    
    public void move()
    {
        x+=xa;
        y+=ya;
    }
    
    public void render(Graphics og, float alpha)
    {
        if (!visible) return;
        
        int xPixel = (int)(xOld+(x-xOld)*alpha)-xPicO;
        int yPixel = (int)(yOld+(y-yOld)*alpha)-yPicO;


        
        og.drawImage(sheet[xPic][yPic], xPixel+(xFlipPic?wPic:0), yPixel+(yFlipPic?hPic:0), xFlipPic?-wPic:wPic, yFlipPic?-hPic:hPic, null);
    }

    public final void tick()
    {
        xOld = x;
        yOld = y;
        move();
    }

    public final void tickNoMove()
    {
        xOld = x;
        yOld = y;
    }

    public float getX(float alpha)
    {
        return (xOld+(x-xOld)*alpha)-xPicO;
    }

    public float getY(float alpha)
    {
        return (yOld+(y-yOld)*alpha)-yPicO;
    }

    public void collideCheck()
    {
    }

    public void bumpCheck(int xTile, int yTile)
    {
    }

    public boolean shellCollideCheck(Shell shell)
    {
        return false;
    }

    public void release(Mario mario)
    {
    }

    public boolean fireballCollideCheck(Fireball fireball)
    {
        return false;
    }
}