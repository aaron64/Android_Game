package com.mygdx.game.entities.main_area;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.RenderSystem;

public class MainAreaEntity extends Entity {

    protected SceneMainArea scene;
    public MainAreaEntity(SceneMainArea scene, Vector2 pos, String name) {
        super(pos, "main_area/", name);
        this.scene = scene;
    }

    public MainAreaEntity(SceneMainArea scene, Vector2 pos, String folder, String name) {
        super(pos, folder, name);
        this.scene = scene;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(RenderSystem rs) {
        super.render(rs);
    }

    public void touch(MainAreaEntity toucher) {

    }

    public void clickOn() {

    }

    public Rectangle getCollisionRect() {
        Vector2 pos = getPos();
        Vector2 size = getSize();
        return new Rectangle(pos.x, pos.y, size.x, size.y * 0.25f);
    }

    public SceneMainArea getScene() {
        return scene;
    }
}
