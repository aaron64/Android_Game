package com.mygdx.game.scenes.main_area;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entities.main_area.MainAreaEntity;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2f;

public class SceneMainAreaTile extends MainAreaEntity {

    private SceneMainAreaTileType type;

    protected Vector2f renderPos;
    protected Vector2f endPos;

    private float offsetLevel;

    private float distSoftner;

    protected Texture endTexture;
    private float yOffset;

    int scale = 2;
    public SceneMainAreaTile(SceneMainArea scene, Vector2f pos, String name, SceneMainAreaTileType type) {
        super(scene, pos, "tiles", name);
        getSize().multiply(scale);
        setPos(Vector2f.multiplyVectors(getPos(), getSize()));

        distSoftner = 25;

        int tileBottom = (int)(Math.random() * 3);
        endTexture = Image.getImage("entities/tiles/tile_end" + tileBottom);

        offsetLevel = (float)(Math.random() * 1 + 1);

        this.type = type;
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

        endPos = new Vector2f(getPos().x, getPos().y - getSize().h() - dist * offsetLevel);
    }

    @Override
    public void render(RenderSystem rs) {
        rs.draw(getImage(), renderPos, getSize());
        rs.draw(endTexture, endPos, getSize());
    }

    public SceneMainAreaTileType getType() {
        return type;
    }

    public void setType(SceneMainAreaTileType type) {
        this.type = type;
    }
}
