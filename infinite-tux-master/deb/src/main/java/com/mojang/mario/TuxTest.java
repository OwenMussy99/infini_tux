package com.mojang.mario;

import static org.junit.Assert.assertEquals;

import java.awt.event.KeyEvent;

import com.mojang.mario.Art;
import com.mojang.mario.sprites.*;
import com.mojang.mario.Scene;
import com.mojang.mario.level.*;
import com.mojang.mario.LevelScene;
import org.junit.Test;

public class TuxTest {
	
	//@Test
	//public void testJumpPressed() {
	//	assertSame("Test if jump key pressed", Mario.KEY_JUMP, KeyEvent.VK_S);
	//}
	
	//@Test
	//public void testRightPressed() {
	//	assertEquals("Test if right movement key pressed", Mario.KEY_RIGHT, KeyEvent.VK_RIGHT);
	//}
	
	//@Test
	//public void testLeftPressed() {
	//	assertEquals("Test if left movement key pressed", Mario.KEY_LEFT, KeyEvent.VK_LEFT);
	//}
	
	//@Test
	//public void testAPressed() {
	//	assertEquals("Test if power/speed key pressed", Mario.KEY_SPEED, KeyEvent.VK_A);
	//}
	
	//@Test
	//public void testDownPressed() {
	//	assertEquals("Test if crouch key pressed", Mario.KEY_DOWN, KeyEvent.VK_DOWN);
	//}
	
	//@Test
	//public void testUpPressed() {
	//	assertEquals("Test if up key pressed", Mario.KEY_UP, KeyEvent.VK_UP);
	//}
	
	@Test
	public void testScoreGTHighScore() {
		Mario.score = 400;
		LevelScene.highScore = 300;
		LevelScene.checkScores(Mario.score, LevelScene.highScore);
		assertEquals(400, LevelScene.highScore);
	}
	
	@Test
	public void testScoreLTHighScore() {
		Mario.score = 600;
		LevelScene.highScore = 800;
		LevelScene.checkScores(Mario.score, LevelScene.highScore);
		assertEquals(800, LevelScene.highScore);
	}

}
