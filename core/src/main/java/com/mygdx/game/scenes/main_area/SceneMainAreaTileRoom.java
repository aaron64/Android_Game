package com.mygdx.game.scenes.main_area;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vec2f;
import com.mygdx.game.util.Vec2i;

public class SceneMainAreaTileRoom extends SceneMainAreaTile {

    private Texture endTexture;
    private Vec2f endPos;
    private Vec2i endSize;

    public SceneMainAreaTileRoom(SceneMainArea scene, Vec2f pos, String name, SceneMainAreaTileType type) {
        super(scene, pos, "tiles", name, type);

        if(name.equals("tile_roomBL")) {
            endTexture = Image.getImage("entities/tiles/tile_room_endL");
        } else if(name.equals("tile_roomB")) {
            endTexture = Image.getImage("entities/tiles/tile_room_endC");
        } else if(name.equals("tile_roomBR")) {
            endTexture = Image.getImage("entities/tiles/tile_room_endR");
        }

        if(endTexture != null) {
            endSize = new Vec2i(getSize());
            endSize.y *= 4;

            endPos = new Vec2f(getPos().x, getPos().y - endSize.h());
        }
    }

    @Override
    public void render(RenderSystem rs) {
        rs.draw(getImage(), getPos(), getSize());
        if(endTexture != null) {
            rs.draw(endTexture, endPos, endSize);
        }
    }
}
