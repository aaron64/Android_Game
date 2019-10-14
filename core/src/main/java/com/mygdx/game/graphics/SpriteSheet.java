package com.mygdx.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class SpriteSheet {

    private Texture spriteSheet;
    private int n;

    private Vector2i size;

    private Vector2i srcPos, srcSize;

    public SpriteSheet(Texture texture, int n) {
        spriteSheet = texture;

        this.n = n;
        srcPos = new Vector2i(0,0);
        srcSize = new Vector2i(spriteSheet.getWidth() / n, spriteSheet.getHeight());

        size = new Vector2i(spriteSheet.getWidth()/n, spriteSheet.getHeight());
    }

    public void reset() {
        srcPos.x = 0;
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
}
