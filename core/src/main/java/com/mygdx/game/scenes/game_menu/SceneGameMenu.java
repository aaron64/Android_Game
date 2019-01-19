package com.mygdx.game.scenes.game_menu;

import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.SceneContainer;
import com.mygdx.game.scenes.game_menu.deck.SceneDeckEditor;
import com.mygdx.game.scenes.game_menu.shard_editor.SceneShardEditor;

public class SceneGameMenu extends Scene implements SceneContainer {

    private Scene currentTab;

    private SceneDeckEditor deckScene;
    private SceneShardEditor shardEditorScene;

    public SceneGameMenu() {
        deckScene = new SceneDeckEditor();
        shardEditorScene = new SceneShardEditor();

        currentTab = deckScene;
    }

    @Override
    public void update() {
        currentTab.update();
    }

    @Override
    public void render() {
        currentTab.render();
    }

    @Override
    public void onPushed() {

    }

    @Override
    public void onPopped() {

    }

    @Override
    public void onExit() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public Scene getCurrentScene() {
        return currentTab;
    }
}
