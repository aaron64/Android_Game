package com.mygdx.game.scenes.main_area;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.TimedSpriteSheet;
import com.mygdx.game.util.Vector2f;

public class SceneMainAreaTilePathWater extends SceneMainAreaTilePath {

    private TimedSpriteSheet spriteSheet;
    public SceneMainAreaTilePathWater(SceneMainArea scene, Vector2f pos, SceneMainAreaTileType type) {
        super(scene, pos, "tile_water", type);
        spriteSheet = new TimedSpriteSheet(getImage(), 4, 10);
        getSize().x /= 4;
        setPos(Vector2f.multiplyVectors(pos, getSize()));

        overlayElement = Element.WATER;
    }

    @Override
    public void update() {
        super.update();
        spriteSheet.update();
    }

    @Override
    public void render(RenderSystem rs) {
        spriteSheet.render(rs, renderPos, getSize());
        rs.draw(endTexture, endPos, getSize());
    }
}
