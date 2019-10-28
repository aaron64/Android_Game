package com.mygdx.game.scenes.main_area;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.attributes.Element;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2f;

public class SceneMainAreaTilePath extends SceneMainAreaTile {



    protected Vector2f renderPos;
    protected Vector2f endPos;

    private float offsetLevel;
    private float distSoftner;

    protected Texture endTexture;
    protected Texture endTextureOverlay;
    protected Element overlayElement;
    private float yOffset;

    private float dist;

    public SceneMainAreaTilePath(SceneMainArea scene, Vector2f pos, String name, SceneMainAreaTileType type) {
        super(scene, pos, "tiles", name, type);

        distSoftner = 25;

        int tileBottom = (int)(Math.random() * 3);
        endTexture = Image.getImage("entities/tiles/tile_end" + tileBottom);
        endTextureOverlay = Image.getImage("entities/tiles/tile_end" + tileBottom + "_map");
        overlayElement = Element.GRASS;

        offsetLevel = (float)(Math.random() * 1 + 1);
    }

    @Override
    public void update() {
        Vector2f playerPos = scene.getPlayer().getPos();
        float dx = playerPos.x - getPos().x;
        float dy = playerPos.y - getPos().y;
        dist = (float) Math.sqrt(dx * dx + dy * dy)/distSoftner;
        yOffset = dist;

        renderPos = new Vector2f(getPos());
        renderPos.y -= dist * offsetLevel;

        endPos = new Vector2f(getPos().x, getPos().y - getSize().h() - dist * offsetLevel);
    }

    @Override
    public void render(RenderSystem rs) {
        rs.draw(getImage(), renderPos, getSize());

        rs.draw(endTexture, endPos, getSize());

        float intensity = 1;
        if(dist != 0)
            intensity = 1 / dist;
        rs.setColor(overlayElement.getHighlight(), intensity);
        rs.draw(endTextureOverlay, endPos, getSize());
        rs.resetColor();
    }
}
