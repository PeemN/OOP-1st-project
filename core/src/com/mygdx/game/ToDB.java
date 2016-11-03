package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ToDB extends Game  {
	//wait for code re factory ;^)
    public SpriteBatch batch;
    

    public void create () {
        batch = new SpriteBatch();
        setScreen(new GameScreen(this));
    }
 

    public void render() {
    	super.render();
    }
    

    public void dispose () {
        batch.dispose();
    }
}
