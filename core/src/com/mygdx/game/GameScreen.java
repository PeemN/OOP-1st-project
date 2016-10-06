package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements ApplicationListener {
	public SpriteBatch batch;
	//public BitmapFont font;
	Texture status;
	Texture hole;
	Texture box;
	
	
    @Override
	public void create () {
		batch = new SpriteBatch();
		hole = new Texture("testhole.jpg");
		status = new Texture("statusboard.jpg");
		box = new Texture("testbox.jpg");
	    //font = new BitmapFont();
	    //font.setColor(Color.WHITE);


	}
	
	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void render () {
        Gdx.gl.glClearColor(0.8f, 0.85f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //batch.begin();
        //font.draw(batch, "Start ?", 400, 360);
        //batch.end();
	    batch.begin();
	    batch.draw(status, 0, 600);
	    batch.draw(box, 100 + 300, 300);
	    batch.end();
        batch.begin();
        for (int i = 0; i < 6; i++){
        	if (i % 2 != 0){
        		for (int j = 0; j < 6; j++){
        		batch.draw(hole, 100 + 100*i, 0);
        		}
        	}
        		
        }
        batch.end();
	}

	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}
    	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
}
