package com.mygdx.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class TimedSpriteSheet {

    private Texture spriteSheet;
    private int n;
    private int animationSpeed;
    private int animationCount;

    private Vector2i size;

    private Vector2i srcPos, srcSize;

    public TimedSpriteSheet(Texture texture, int n, int time) {
        spriteSheet = texture;

        animationSpeed = time;
        animationCount = 0;

        this.n = n;
        srcPos = new Vector2i(0,0);
        srcSize = new Vector2i(spriteSheet.getWidth() / n, spriteSheet.getHeight());

        size = new Vector2i(spriteSheet.getWidth()/n, spriteSheet.getHeight());
    }

    public void update() {
        animationCount++;
        srcPos.x = ((animationCount/animationSpeed)%n) * srcSize.w();
    }

    public void reset() {
        srcPos.x = 0;
    }

    public void render(RenderSystem rs, Vector2f pos) {
        rs.draw(spriteSheet, pos, size, srcPos, srcSize, false, false);
    }

    public void render(RenderSystem rs, Vector2f pos, Vector2i size) {
        rs.draw(spriteSheet, pos, size, srcPos, srcSize, false, false);
    }

    public Vector2i getSize() {
        return size;
    }

    public void setSize(Vector2i size) {
        this.size = size;
    }
}
