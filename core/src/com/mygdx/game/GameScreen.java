package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter implements ApplicationListener {
	public SpriteBatch batch;
	
	public void create () {
		batch = new SpriteBatch();

	}

	public void render () {
		 
	}
	
	public void dispose () {
		batch.dispose();
	}
	
}
