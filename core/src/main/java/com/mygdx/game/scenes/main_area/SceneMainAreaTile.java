package com.mygdx.game.scenes.main_area;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entities.main_area.MainAreaEntity;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2f;

public class SceneMainAreaTile extends MainAreaEntity {

    private SceneMainAreaTileType type;

    private Vector2f renderPos;
    private Vector2f endPos;

    private float offsetLevel;

    private float distSoftner;

    private Texture endTexture;
    private float yOffset;
    public SceneMainAreaTile(SceneMainArea scene, Vector2f pos, String name, SceneMainAreaTileType type) {
        super(scene, pos, "tiles/main_area", name);
        setPos(Vector2f.multiplyVectors(getPos(), getSize()));

        distSoftner = 25;

        int tileBottom = (int)(Math.random() * 3);
        endTexture = new Texture("entities/tiles/main_area/tile_end" + tileBottom + ".png");

        offsetLevel = (float)(Math.random() * 1 + 1);
    }

    @Override
    public void update() {
        Vector2f playerPos = scene.getPlayer().getPos();
        float dx = playerPos.x - getPos().x;
        float dy = playerPos.y - getPos().y;
        float dist = (float) Math.sqrt(dx * dx + dy * dy)/distSoftner;
        yOffset = dist;

        renderPos = new Vector2f(getPos());
        renderPos.y -= dist * offsetLevel;

        endPos = new Vector2f(getPos().x, getPos().y - endTexture.getHeight() - dist * offsetLevel);
    }

    @Override
    public void render(RenderSystem rs) {
        rs.draw(getImage(), renderPos);
        rs.draw(endTexture, endPos);
    }

    public SceneMainAreaTileType getType() {
        return type;
    }

    public void setType(SceneMainAreaTileType type) {
        this.type = type;
    }
}
