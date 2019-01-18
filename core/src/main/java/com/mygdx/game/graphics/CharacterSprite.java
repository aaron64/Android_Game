package com.mygdx.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class CharacterSprite {

    private Texture spriteSheet;
    private Vector2i size;
    private int animationSpeed;
    private int animationCount;

    private Vector2i srcPos, srcSize;

    public CharacterSprite(Texture texture, Vector2i size) {
        spriteSheet = texture;

        animationSpeed = 10;
        animationCount = 0;

        this.size = size;
        srcPos = new Vector2i(0,0);
        srcSize = new Vector2i(spriteSheet.getWidth() / 4, spriteSheet.getHeight() / 4);
    }

    public void update(Vector2f vel) {
        if(Math.abs(vel.x) > Math.abs(vel.y)) {
            if(vel.x > 0) {
                srcPos.y = 2 * srcSize.h();
            } else if(vel.x < 0) {
                srcPos.y = 1 * srcSize.h();
            }
        } else {
            if (vel.y > 0) {
                srcPos.y = 3 * srcSize.h();
            } else if(vel.y < 0) {
                srcPos.y = 0 * srcSize.h();
            }
        }
        animationCount++;
        srcPos.x = ((animationCount/animationSpeed)%4) * srcSize.w();
    }

    public void reset() {
        srcPos.x = 0;
    }

    public void render(RenderSystem rs, Vector2f pos) {
        rs.draw(spriteSheet, pos, size, srcPos, srcSize, false, false);
    }

    public Vector2i getSize() {
        return size;
    }

    public void setSize(Vector2i size) {
        this.size = size;
    }
}
