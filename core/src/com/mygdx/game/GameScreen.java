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
    private int x;
    private int y;
    private int stageCounter;
    private int currentstage;
    private Texture status;
    private Texture holeImg;
    private Texture boxImg;
    private Texture rockImg;
    
	public GameScreen(ToDB toDB) {
        this.toDB = toDB;
		status = new Texture("statusboard.jpg");
		holeImg = new Texture("testhole.jpg");
		boxImg = new Texture("testbox.jpg");
		rockImg = new Texture("testrock.jpg");
		font = new BitmapFont();
	    font.setColor(Color.WHITE);
        x = 0;
        y = 0;
        stageCounter = 0;
        
    }

	public void create () {
		batch = new SpriteBatch();
	}
	
	@Override
	public void render (float delta) {
		//update(delta);
		SpriteBatch batch = toDB.batch;
        Gdx.gl.glClearColor(0.85f, 0.85f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (stageCounter == currentstage){
        	int [][] gameboard = mapCreator();
            int height = gameboard[1].length;
            int width = gameboard[0].length;
            System.out.println(height + "  " + width);
            
        	/*batch.begin();
        	for(int r = 0; r < height; r++) {
        		for(int c = 0; c < width; c++) {
        			int x = c * 100;
        			int y = 600 - (r * 100) - 100;
        			
        			if(gameboard[c][r] == 1) {
        				batch.draw(holeImg, x, y);
        			} else if(gameboard[c][r] == 2) {
        				batch.draw(boxImg, x, y);
        			} else if(gameboard[c][r] == 3) {
        				batch.draw(rockImg, x, y);
        			}
        		}
        	}
        	//batch.draw(box, 100 + 300 + x , 300 + y );
        	batch.draw(status, 0, 600);
        	batch.end();*/
        	stageCounter++;
        }
	}
    
	public int[][] mapCreator(){
		Random random = new Random();
		int Map [][] = new int [][]{
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0}
		};
        int height = Map[1].length;
        int width = Map[0].length;
        int numberofHole = 0;
        int numberofBox = 0;
        int numberofRock = 0;
		
		while (numberofHole < 6){
			int mapR = random.nextInt(height);
			int mapC = random.nextInt(width);
			if(Map[mapR][mapC] == 0){
				Map[mapR][mapC] = 1;
			}
		}
		while (numberofBox < 6){
			int mapR = random.nextInt(height);
			int mapC = random.nextInt(width);
			if(Map[mapR][mapC] == 0){
				Map[mapR][mapC] = 2;
			}
		}
		while (numberofRock < 6){
			int mapR = random.nextInt(height);
			int mapC = random.nextInt(width);
			if(Map[mapR][mapC] == 0){
				Map[mapR][mapC] = 3;
			}
		}
		return Map;
	}
	
	private void update(float delta) {
        if(Gdx.input.isKeyPressed(Keys.UP)) {
            y += 10;
        }
        if(Gdx.input.isKeyPressed(Keys.LEFT)) {
            x -= 10;
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
            x += 10;
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)) {
            y -= 10;
        }

    }
    	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
}
