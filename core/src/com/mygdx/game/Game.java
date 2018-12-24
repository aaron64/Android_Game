package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.items.cards.CardLoader;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.game_menu.SceneGameMenu;
import com.mygdx.game.scenes.main_area.SceneMainArea;

import java.util.Stack;

public class Game extends ApplicationAdapter {
	private static Stack<Scene> sceneStack;

	protected static SceneGameMenu gameMenuScene;

    @Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		sceneStack = new Stack<Scene>();
		CardLoader.init();

		gameMenuScene = new SceneGameMenu();

		sceneStack.push(new SceneMainArea());
		//sceneStack.push(new SceneDeck());
	}

	@Override
	public void render () {
		getCurrentScene().update();
		getCurrentScene().render();
	}

	public static Scene getCurrentScene() {
		return sceneStack.peek();
	}

	public static Scene getLastScene() {
    	Scene current = sceneStack.pop();
    	Scene last = sceneStack.peek();
    	sceneStack.push(current);
    	return last;
	}

	public static void pushScene(Scene scene) {
		sceneStack.push(scene);
	}

	public static void endScene() {
		sceneStack.pop();
	}

	public static float getGravity() {
    	return -1f;
	}
	
	@Override
	public void dispose () {
		getCurrentScene().dispose();
	}
}
