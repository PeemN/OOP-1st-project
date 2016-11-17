package com.mygdx.game;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import javax.management.loading.PrivateClassLoader;

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
	private ToDB toDB;
	private int inputDirection;
    private int height;
    private int width;
    private int stageCounter;
    private int score;
    private int highestScore;
    private int turn;
    private int reset;
    private int numberofHole;
    private int numberofBox;
    private int numberofRock;
    private int numberofStage;
    private BitmapFont font;
    private Texture status;
    private Texture holeImg;
    private Texture boxImg;
    private Texture rockImg;
    private int[][] gameboard;
    
    private GameField gameField = new GameField();
    
	public GameScreen(ToDB toDB) {
        this.toDB = toDB;
        
		status = new Texture("statusboard.jpg");
		holeImg = new Texture("testhole.jpg");
		boxImg = new Texture("testbox.jpg");
		rockImg = new Texture("testrock.jpg");
		font = new BitmapFont();
	    font.setColor(Color.BLACK);
	    inputDirection = 0;
        stageCounter = 0;
        turn = 60;
        reset = 10;
        numberofStage = 5;
        numberofHole = 6;
        numberofBox = 6;
        numberofRock = 6;
    }

	public void create() {
		batch = new SpriteBatch();
	}
	
	@Override
	public void render(float delta) {
		
		
        Gdx.gl.glClearColor(0.85f, 0.85f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Keys.ENTER) && stageCounter == 0){
        	gameboard = gameField.mapCreator(numberofBox, numberofHole, numberofRock);
            height = gameboard[1].length;
            width = gameboard[0].length;
          	stageCounter++;
          	
        }
        if (stageCounter == 0)
        {
        	//displaystartScreen
        }
        if (stageCounter > 0 && stageCounter <= numberofStage){
        	update(delta);
    		gameboard = gameField.mapUpdate(gameboard, numberofBox, inputDirection);
    		System.out.println(turn);
    		inputDirection = 0;
    		SpriteBatch batch = toDB.batch;
        	batch.begin();
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
       		//batch.draw(status, 0, 600);
       		batch.end();
       		if (clearStageChecker(gameboard)){
    			scoreCalculation();
    			numberofRock++;
            	gameboard = gameField.mapCreator(numberofBox, numberofHole, numberofRock);
                height = gameboard[1].length;
                width = gameboard[0].length;
    			stageCounter++;
    		}
        }
        if (stageCounter > numberofStage){
        	//displayresult & highscore
        }
        if (gameOver()){
        	//gameoverscreen & score
        }
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
        if (Gdx.input.isKeyPressed(Keys.R) && (reset != 0)){
        	gameboard = gameField.mapCreator(numberofBox, numberofHole, numberofRock);
            height = gameboard[1].length;
            width = gameboard[0].length;
            reset--;
            addDelay(500);
			
        }
    }
	
	public void startScreen(){
		
	}
    	
	public boolean clearStageChecker(int[][] Map){
		int boxLeft;
		boolean stageClear = false;
		
		ArrayList<Integer> boxPosition = gameField.findBox(Map, 0);
		boxLeft = boxPosition.size();
		if (boxLeft == 0){
			stageClear = true;
		}
		return stageClear;
	}
	
	public boolean gameOver(){
		boolean noTurnReset = false;
		if (turn == 0){
			noTurnReset = true;
		}
		if (reset == 0){
			noTurnReset = true;
		}
		return noTurnReset;
	}
	
	public int scoreCalculation(){
		int score = (100*turn) + (500*reset);
		
		return score;
	}
	
	public void addDelay(int delayTime){
		try {
			Thread.sleep(delayTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
	
}

