package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.items.cards.CardLoader;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.main_area.SceneMainArea;

import java.util.Stack;

public class Game extends ApplicationAdapter {
	private static Stack<Scene> sceneStack;
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		sceneStack = new Stack<Scene>();
		CardLoader.init();

		sceneStack.push(new SceneMainArea());
		//sceneStack.push(new SceneBattle());
	}

	@Override
	public void render () {
		getCurrentScene().update();
		getCurrentScene().render();
	}

	public static Scene getCurrentScene() {
		return sceneStack.peek();
	}

	public static void pushScene(Scene scene) {
		sceneStack.push(scene);
	}

	public static void endScene() {
		sceneStack.pop();
	}
	
	@Override
	public void dispose () {
		getCurrentScene().dispose();
	}
}
