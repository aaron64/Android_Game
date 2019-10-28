package com.mygdx.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class SpriteSheet {

    protected Texture spriteSheet;
    protected int n;

    protected Vector2i size;

    protected Vector2i srcPos, srcSize;

    public SpriteSheet(Texture texture, Vector2i size, int n) {
        spriteSheet = texture;

        this.n = n;
        srcPos = new Vector2i();
        srcSize = new Vector2i(spriteSheet.getWidth() / n, spriteSheet.getHeight());

        this.size = size;
    }

    public SpriteSheet(Texture texture, int n) {
        this(texture, new Vector2i(), n);
    }

    public void reset() {
        srcPos.x = 0;
    }

    public void render(RenderSystem rs, Vector2f pos) {
        render(rs, pos, 0);
    }

    public void render(RenderSystem rs, Vector2f pos, Vector2i size) {
        render(rs, pos, size, 0);
    }

    public void render(RenderSystem rs, Vector2f pos, int i) {
        srcPos.x = i * srcSize.w();
        rs.draw(spriteSheet, pos, size, srcPos, srcSize, false, false);
    }

    public void render(RenderSystem rs, Vector2f pos, Vector2i size, int i) {
        srcPos.x = i * srcSize.w();
        rs.draw(spriteSheet, pos, size, srcPos, srcSize, false, false);
    }

    public void render(RenderSystem rs, float x, float y, int w, int h, boolean flipX, boolean flipY, int i) {
        srcPos.x = i * srcSize.w();
        rs.draw(spriteSheet, x, y, w, h, srcPos, srcSize, flipX, flipY);
    }

    public Vector2i getSize() {
        return size;
    }

    public void setSize(Vector2i size) {
        this.size = size;
    }

    public void setImage(Texture texture) {
        spriteSheet = texture;
    }

    public Vector2i getSrcSize() {
        return srcSize;
    }
}
