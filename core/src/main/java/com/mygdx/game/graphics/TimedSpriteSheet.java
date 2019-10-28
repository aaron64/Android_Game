package com.mygdx.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class TimedSpriteSheet extends SpriteSheet {

    private int animationSpeed;
    private int animationCount;


    public TimedSpriteSheet(Texture texture, int n, int time) {
        super(texture, n);
        animationSpeed = time;
        animationCount = 0;
    }

    public void update() {
        animationCount++;
        srcPos.x = ((animationCount/animationSpeed)%n) * srcSize.w();
    }

    public void reset() {
        srcPos.x = 0;
    }

    @Override
    public void render(RenderSystem rs, Vector2f pos) {
        rs.draw(spriteSheet, pos, size, srcPos, srcSize, false, false);
    }

    @Override
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
