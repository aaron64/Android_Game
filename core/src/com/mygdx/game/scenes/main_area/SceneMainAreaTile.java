package com.mygdx.game.scenes.main_area;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.main_area.MainAreaEntity;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.RenderSystem;

public class SceneMainAreaTile extends MainAreaEntity {

    private SceneMainAreaTileType type;

    private Vector2 renderPos;
    private Vector2 endPos;

    private float distSoftner;

    private Texture endTexture;
    private float yOffset;
    public SceneMainAreaTile(SceneMainArea scene, Vector2 pos, String name, SceneMainAreaTileType type) {
        super(scene, pos, "tiles/main_area", name);
        setPos(MathUtil.multiplyVec(getPos(), getSize()));

        distSoftner = 25;

        endTexture = new Texture("entities/tiles/main_area/tile_end.png");
    }

    @Override
    public void update() {
        Vector2 playerPos = scene.getPlayer().getPos();
        float dx = playerPos.x - getPos().x;
        float dy = playerPos.y - getPos().y;
        float dist = (float) Math.sqrt(dx * dx + dy * dy)/distSoftner;
        yOffset = dist;

        renderPos = new Vector2(getPos());
        renderPos.y -= dist;

        endPos = new Vector2(getPos().x, getPos().y - endTexture.getHeight() - dist);
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
