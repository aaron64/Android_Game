package com.mygdx.game.entities.main_area;

import com.badlogic.gdx.math.Rectangle;

import com.mygdx.game.entities.Entity;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vec2f;
import com.mygdx.game.util.Vec2i;

public abstract class MainAreaEntity extends Entity {

    protected boolean solid;
    protected SceneMainArea scene;

    // non-tile entities
    public MainAreaEntity(SceneMainArea scene, Vec2f pos, String name) {
        super(pos, "main_area", name);
        this.scene = scene;
        solid = true;
    }

    // tiles
    public MainAreaEntity(SceneMainArea scene, Vec2f pos, String folder, String name) {
        super(pos, folder, name);
        this.scene = scene;
        solid = true;
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
        Vec2f pos = getPos();
        Vec2i size = getSize();
        return new Rectangle(pos.x, pos.y, size.w(), size.h() * 0.25f);
    }

    public boolean collidesWith(MainAreaEntity e) {
        return e.getCollisionRect().overlaps(getCollisionRect());
    }

    public SceneMainArea getScene() {
        return scene;
    }

    public boolean isSolid() { return solid; }
}
