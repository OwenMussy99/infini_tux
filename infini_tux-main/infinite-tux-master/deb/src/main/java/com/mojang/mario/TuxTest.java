package com.mojang.mario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.mojang.mario.sprites.*;
import org.junit.Test;

public class TuxTest {
	
	MarioComponent comp = new MarioComponent(0, 0);
	
	@Test
	public void testJumpPressed() {
		System.out.println("Executing test #1: KEY PRESSED KEY S");
		assertSame("Test if jump key pressed", Mario.KEY_JUMP, comp.toggleKey(Mario.KEY_JUMP, true));
	}
	
	@Test
	public void testRightPressed() {
		System.out.println("Executing test #2: KEY PRESSED RIGHT ARROW");
		assertEquals("Test if right movement key pressed", Mario.KEY_RIGHT, comp.toggleKey(Mario.KEY_RIGHT, true));
	}
	
	@Test
	public void testLeftPressed() {
		System.out.println("Executing test #3: KEY PRESSED LEFT ARROW");
		assertEquals("Test if left movement key pressed", Mario.KEY_LEFT, comp.toggleKey(Mario.KEY_LEFT, true));
	}
	
	@Test
	public void testAPressed() {
		System.out.println("Executing test #4: KEY PRESSED A");
		assertEquals("Test if power/speed key pressed", Mario.KEY_SPEED, comp.toggleKey(Mario.KEY_SPEED, true));
	}
	
	@Test
	public void testDownPressed() {
		System.out.println("Executing test #5: KEY PRESSED DOWN ARROW");
		assertEquals("Test if crouch key pressed", Mario.KEY_DOWN, comp.toggleKey(Mario.KEY_DOWN, true));
	}
	
	@Test
	public void testUpPressed() {
		System.out.println("Executing test #6: KEY PRESSED UP ARROW");
		assertEquals("Test if up key pressed", Mario.KEY_UP, comp.toggleKey(Mario.KEY_UP, true));
	}
	
	@Test
	public void testQPressed() {
		System.out.println("Executing test #7: KEY PRESSED Q");
		assertEquals("Test if quit key pressed", Mario.KEY_QUIT, comp.toggleKey(Mario.KEY_QUIT, true));
	}
	
	@Test
	public void testScoreGTHighScore() {
		Mario.score = 400;
		LevelScene.highScore = 300;
		LevelScene.checkScores(Mario.score, LevelScene.highScore);
		System.out.println("Executing test #8: SCORE > HIGHSCORE");
		assertEquals(400, LevelScene.highScore);
	}
	
	@Test
	public void testScoreLTHighScore() {
		Mario.score = 600;
		LevelScene.highScore = 800;
		LevelScene.checkScores(Mario.score, LevelScene.highScore);
		System.out.println("Executing test #9: SCORE < HIGHSCORE");
		assertEquals(800, LevelScene.highScore);
	}
	
	@Test
	public void testScoreEqualHighScore() {
		Mario.score = 1000;
		LevelScene.highScore = 1000;
		LevelScene.checkScores(Mario.score, LevelScene.highScore);
		System.out.println("Executing test #10: SCORE == HIGHSCORE");
		assertEquals(1000, LevelScene.highScore);
	}
	
	@Test
	public void testFireCollide() {
		System.out.println("Executing test #11: FIREBALL COLLISION ENV/ENEMY");
		List<Fireball> fireballsToCheck = new ArrayList<Fireball>();
		List<Sprite> sprites = new ArrayList<Sprite>();
		for (Fireball fireball : fireballsToCheck)
        {
            for (Sprite sprite : sprites)
            {
                if (sprite != fireball && !fireball.dead)
                {
                    if (sprite.fireballCollideCheck(fireball))
                    {
                        fireball.die();
                        assertTrue(sprite.fireballCollideCheck(fireball));
                    }
                }
            }
        }
        fireballsToCheck.clear();
	}
	
	@Test
	public void incorrectKey() {
		System.out.println("Executing test #12: KEY PRESSED KEY S, KEY RIGHT WOULD BE INCORRECT");
		assertNotSame("Test wrong input", Mario.KEY_RIGHT, comp.toggleKey(Mario.KEY_JUMP, true));
	}
}