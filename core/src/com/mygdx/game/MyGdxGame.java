package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;

public class MyGdxGame extends ApplicationAdapter {
	Scene currentScene;
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		currentScene = new SceneBattle();
	}

	@Override
	public void render () {
		currentScene.update();
		currentScene.render();
	}
	
	@Override
	public void dispose () {
		currentScene.dispose();
	}
}
