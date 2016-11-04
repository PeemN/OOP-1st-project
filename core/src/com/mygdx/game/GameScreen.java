package com.mygdx.game;

import java.awt.Font;
import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends ScreenAdapter {
	private SpriteBatch batch;
	private BitmapFont font;
	private ToDB toDB;
    private int boxPositionX;
    private int boxPositionY;
    private int height;
    private int width;
    private int stageCounter;
    private int currentstage;
    private int turn;
    private Texture status;
    private Texture holeImg;
    private Texture boxImg;
    private Texture rockImg;
    private int[][] gameboard;
    
	public GameScreen(ToDB toDB) {
        this.toDB = toDB;
		status = new Texture("statusboard.jpg");
		holeImg = new Texture("testhole.jpg");
		boxImg = new Texture("testbox.jpg");
		rockImg = new Texture("testrock.jpg");
		font = new BitmapFont();
	    font.setColor(Color.WHITE);
        boxPositionX = 0;
        boxPositionY = 0;
        stageCounter = 0;
        turn = 60;
    }

	public void create () {
		batch = new SpriteBatch();
	}
	
	@Override
	public void render (float delta) {
		update(delta);
		SpriteBatch batch = toDB.batch;
        Gdx.gl.glClearColor(0.85f, 0.85f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (stageCounter == currentstage){
        	gameboard = mapCreator();
            height = gameboard[1].length;
            width = gameboard[0].length;
            //System.out.println(height + "  " + width);
          	stageCounter++;
        }
        	batch.begin();
        	for (int r = 0; r < height; r++) {
        		for (int c = 0; c < width; c++) {
        			int x = c * 100;
        			int y = 600 - (r * 100) - 100;
        			
        			if (gameboard[c][r] == 1) {
        				batch.draw(holeImg, x, y);
        			} else if (gameboard[c][r] == 2) {
        				batch.draw(boxImg, x + boxPositionX, y + boxPositionY);
        			} else if (gameboard[c][r] == 3) {
        				batch.draw(rockImg, x, y);
        			}
        		}
        	}
        	batch.draw(status, 0, 600);
        	System.out.println(turn);
        	batch.end();
        	
	}
    
	public int[][] mapCreator() {
		Random random = new Random();
		int Map[][] = new int[6][6];
        int height = Map[1].length;
        int width = Map[0].length;
        int numberofHole = 6;
        int numberofBox = 6;
        int numberofRock = 6;
		
        //can minimize to 1 method parameter(numberof...) 
		for (int i = 0; i < numberofHole; ) {
			int mapR = random.nextInt(height);
			int mapC = random.nextInt(width);
			if(Map[mapR][mapC] == 0){
				Map[mapR][mapC] = 1;
				i++;
			}
		}
		for (int i = 0; i < numberofBox; ) {
			int mapR = random.nextInt(height);
			int mapC = random.nextInt(width);
			if(Map[mapR][mapC] == 0){
				Map[mapR][mapC] = 2;
				i++;
			}
		}
		for (int i = 0; i < numberofRock; ) {
			int mapR = random.nextInt(height);
			int mapC = random.nextInt(width);
			if(Map[mapR][mapC] == 0){
				Map[mapR][mapC] = 3;
				i++;
			}
		}
		return Map;
	}
	
	private void update(float delta) {
        if (Gdx.input.isKeyPressed(Keys.UP)) {
        	boxPositionY += 10;
        	turn--;
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
        	boxPositionX -= 10;
        	turn--;
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
        	boxPositionX += 10;
        	turn--;
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
        	boxPositionY -= 10;
        	turn--;
        }
    }
    	
	@Override
	public void dispose() {
		batch.dispose();
	}

}
