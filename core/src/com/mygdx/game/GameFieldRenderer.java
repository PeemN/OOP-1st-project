package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameFieldRenderer {
	/*private SpriteBatch batch;
	private ToDB toDB;
    private Texture status;
	private BitmapFont font;
    private Texture holeImg;
    private Texture boxImg;
    private Texture rockImg;
    private GameScreen gameScreen;

	
	public GameFieldRenderer(ToDB toDB) {
		this.toDB = toDB;
		
		status = new Texture("statusboard.jpg");
		holeImg = new Texture("testhole.jpg");
		boxImg = new Texture("testbox.jpg");
		rockImg = new Texture("testrock.jpg");
		font = new BitmapFont();
	    font.setColor(Color.WHITE);
	    batch =new SpriteBatch();

	}
		
	public void render(float delta){
		gameScreen = new GameScreen(toDB);

		int[][] gameboard = gameScreen.getBoard();
		int height = gameScreen.getHeight();
		int width = gameScreen.getWidth();
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
    	batch.end();
	}*/
}
