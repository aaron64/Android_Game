package com.mygdx.game.scenes.game_menu;

import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.game_menu.deck.SceneDeck;
import com.mygdx.game.scenes.game_menu.shard_editor.SceneShardEditor;

public class SceneGameMenu extends Scene {

    private Scene currentTab;

    private SceneDeck deckScene;
    private SceneShardEditor shardEditorScene;

    public SceneGameMenu() {
        deckScene = new SceneDeck();
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
    public void dispose() {

    }
}
