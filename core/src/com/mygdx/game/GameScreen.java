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
	private int inputDirection;
    private int height;
    private int width;
    private int stageCounter;
    private int turn;
    private Texture status;
    private Texture holeImg;
    private Texture boxImg;
    private Texture rockImg;
    private int[][] gameboard;
    private int reset;
    
    GameField gameField = new GameField();
    
	public GameScreen(ToDB toDB) {
        this.toDB = toDB;
        
		status = new Texture("statusboard.jpg");
		holeImg = new Texture("testhole.jpg");
		boxImg = new Texture("testbox.jpg");
		rockImg = new Texture("testrock.jpg");
		font = new BitmapFont();
	    font.setColor(Color.WHITE);
	    inputDirection = 0;
        stageCounter = 0;
        turn = 60;
        reset = 10;
    }

	public void create() {
		batch = new SpriteBatch();
	}
	
	@Override
	public void render(float delta) {
		
		update(delta);
		SpriteBatch batch = toDB.batch;
        Gdx.gl.glClearColor(0.85f, 0.85f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (stageCounter == 0){
        	
        	gameboard = gameField.mapCreator();
            height = gameboard[1].length;
            width = gameboard[0].length;
          	stageCounter++;
          	
        }
        if (Gdx.input.isKeyPressed(Keys.R) && (reset != 0)){
        	gameboard = gameField.mapCreator();
            height = gameboard[1].length;
            width = gameboard[0].length;
            reset--;
            addDelay(500);
			
        }
        	batch.begin();
        	//maprender
        	for (int r = 0; r < height; r++) {
        		for (int c = 0; c < width; c++) {
        			int x = c * 100;
        			int y = 600 - (r * 100) - 100;
        			
        			if (gameboard[c][r] == 1) {
        				batch.draw(holeImg, x, y);
        			} else if (gameboard[c][r] == 2) {
        				batch.draw(boxImg, x, y);
        			} else if (gameboard[c][r] == 3) {
        				batch.draw(rockImg, x, y);
        			}
        		}
        	}
        	batch.draw(status, 0, 600);
        	System.out.println(turn);
        	batch.end();
        	gameboard = gameField.mapUpdate(gameboard, inputDirection);
        	inputDirection = 0;
        	
	}
	
	private void update(float delta) {
        if (Gdx.input.isKeyPressed(Keys.UP)) {
        	inputDirection = 1;
        	turn--;
        	addDelay(200);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
        	inputDirection = 2;
        	turn--;
        	addDelay(200);
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
        	inputDirection = 3;
        	turn--;
        	addDelay(200);
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
        	inputDirection = 4;
        	turn--;
        	addDelay(200);
        }
    }
    	
	@Override
	public void dispose() {
		batch.dispose();
	}
	
	public void addDelay(int delayTime){
		try {
			Thread.sleep(delayTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

