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
    private static final int startTurn = 100;
    private static final int startResetCount = 10;
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
    private int currentNumberofBox;
    private BitmapFont font;
    private Texture status;
    private Texture holeImg;
    private Texture boxImg;
    private Texture rockImg;
    private int[][] gameboard;
    
    private GameField gameField = new GameField();
    
	public GameScreen(ToDB toDB) {
        this.toDB = toDB;
        turn = startTurn;
        reset = startResetCount;
        
		status = new Texture("statusboard.jpg");
		holeImg = new Texture("testhole.jpg");
		boxImg = new Texture("testbox.jpg");
		rockImg = new Texture("testrock.jpg");
		font = new BitmapFont();
	    inputDirection = 0;
        stageCounter = 0;
        numberofStage = 6;
        numberofHole = 6;
        numberofBox = 6;
        numberofRock = 6;
        highestScore = 0;
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
        	startScreen();
        }
        if (stageCounter > 0 && stageCounter <= numberofStage){
        	update(delta);
    		gameboard = gameField.mapUpdate(gameboard, numberofBox, inputDirection);
    		ArrayList<Integer> boxPosition = gameField.findBox(gameboard, GameField.codeBox, 0);
    		currentNumberofBox = boxPosition.size();
    		System.out.println(turn);
    		inputDirection = 0;
    		font.setColor(Color.BLACK);
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
       		batch.draw(status, 0, 600);
       		font.draw(batch, "Turn: " + turn, 50, 680);
       		font.draw(batch, "Reset: " + reset, 50, 640);
       		font.draw(batch, "Score: " + score, 50, 660);
       		batch.end();
       		if (clearStageChecker(gameboard)){
    			 score += scoreCalculation();
    			numberofRock++;
            	gameboard = gameField.mapCreator(numberofBox, numberofHole, numberofRock);
                height = gameboard[1].length;
                width = gameboard[0].length;
                turn += 10;
                reset += 1;
    			stageCounter++;
    		}
        }
        if (stageCounter > numberofStage){
        	addHeightScore(score);
           	SpriteBatch batch = toDB.batch;
           	
        	font.setColor(Color.CORAL);
        	batch.begin();
        	font.draw(batch, "Congratulation", 250, 400);
        	font.draw(batch, "Your score is " + score, 240, 380);
        	font.draw(batch, "High score " + highestScore, 250, 360);
        	batch.end();
        	gameRestart();
        	
        }
        if (gameOver()){
        	addHeightScore(score);
           	SpriteBatch batch = toDB.batch;
           	            
           	Gdx.gl.glClearColor(0.85f, 0.85f, 0.85f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        	font.setColor(Color.BLACK);
        	
        	batch.begin();
        	font.draw(batch, "Game Over", 260, 400);
        	font.draw(batch, "Your score is " + score, 250, 380);
        	font.draw(batch, "High score " + highestScore, 250, 360);
        	batch.end();
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
        	gameboard = gameField.mapCreator(currentNumberofBox, currentNumberofBox, numberofRock);
            height = gameboard[1].length;
            width = gameboard[0].length;
            reset--;
            addDelay(500);
			
        }
    }
	
	public void startScreen(){
		SpriteBatch batch = toDB.batch;
    	font.setColor(Color.BLACK);
    	batch.begin();
    	font.draw(batch, "Press Enter to Start", 250, 400);
    	batch.end();
	}
    	
	public boolean clearStageChecker(int[][] Map){
		int boxLeft;
		boolean stageClear = false;
		
		ArrayList<Integer> boxPosition = gameField.findBox(Map, GameField.codeBox, 0);
		boxLeft = boxPosition.size();
		if (boxLeft == 0){
			stageClear = true;
		}
		return stageClear;
	}
	
	public boolean gameOver(){
		boolean noTurnAndReset = false;
		if (turn <= 0){
			noTurnAndReset = true;
		}
		if (reset == 0){
			noTurnAndReset = true;
		}
		return noTurnAndReset;
	}
	
	public int scoreCalculation(){
		int score = (100*turn) + (500*reset);
		return score;
	}
	
	public void gameRestart(){
    	if (Gdx.input.isKeyPressed(Keys.ENTER)){
    		stageCounter = 0;
			turn = startTurn;
			reset = startResetCount;
			score = 0;
			numberofRock = 6;
    	}
	}
	
	public void addHeightScore(int endGamescore){
		if (endGamescore >= highestScore){
			highestScore = endGamescore;
		}
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

