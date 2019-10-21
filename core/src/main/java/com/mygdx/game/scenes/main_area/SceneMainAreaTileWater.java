package com.mygdx.game.scenes.main_area;

import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.TimedSpriteSheet;
import com.mygdx.game.util.Vector2f;

public class SceneMainAreaTileWater extends SceneMainAreaTile {

    private TimedSpriteSheet spriteSheet;
    public SceneMainAreaTileWater(SceneMainArea scene, Vector2f pos, String name, SceneMainAreaTileType type) {
        super(scene, pos, name, type);
        spriteSheet = new TimedSpriteSheet(getImage(), 4, 10);
        getSize().x /= 4;
        setPos(Vector2f.multiplyVectors(pos, getSize()));

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
