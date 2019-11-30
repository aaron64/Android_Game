package com.mygdx.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.util.Vec2f;
import com.mygdx.game.util.Vec2i;

public class SpriteSheet {

    protected Texture spriteSheet;
    protected int frames;

    protected Vec2i size;

    protected Vec2i srcPos, srcSize;

    public SpriteSheet(Texture texture, Vec2i size, int frames) {
        spriteSheet = texture;

        this.frames = frames;
        srcPos = new Vec2i();
        srcSize = new Vec2i(spriteSheet.getWidth() / frames, spriteSheet.getHeight());

        this.size = size;
    }

    public SpriteSheet(Texture texture, int n) {
        this(texture, new Vec2i(), n);
    }

    public void reset() {
        srcPos.x = 0;
    }

    public void render(RenderSystem rs, Vec2f pos) {
        render(rs, pos, 0);
    }

    public void render(RenderSystem rs, Vec2f pos, Vec2i size) {
        render(rs, pos, size, 0);
    }

    public void render(RenderSystem rs, Vec2f pos, int i) {
        srcPos.x = i * srcSize.w();
        rs.draw(spriteSheet, pos, size, srcPos, srcSize, false, false);
    }

    public void render(RenderSystem rs, Vec2f pos, Vec2i size, int i) {
        srcPos.x = i * srcSize.w();
        rs.draw(spriteSheet, pos, size, srcPos, srcSize, false, false);
    }

    public void render(RenderSystem rs, float x, float y, int w, int h, boolean flipX, boolean flipY, int i) {
        srcPos.x = i * srcSize.w();
        rs.draw(spriteSheet, x, y, w, h, srcPos, srcSize, flipX, flipY);
    }

    public Vec2i getSize() {
        return size;
    }

    public void setSize(Vec2i size) {
        this.size = size;
    }

    public void setImage(Texture texture) {
        spriteSheet = texture;
    }

    public Vec2i getSrcSize() {
        return srcSize;
    }
}
