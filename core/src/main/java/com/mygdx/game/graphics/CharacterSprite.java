package com.mygdx.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class CharacterSprite extends SpriteSheet {

    private int animationSpeed;
    private int animationCount;

    public CharacterSprite(Texture texture, Vector2i size, int animationSpeed) {
        super(texture, size, 4);

        this.animationSpeed = animationSpeed;
        animationCount = 0;

        srcSize.y /= 4;
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
        animationCount += 1 + (MathUtil.getDistance(vel) / 3);
        srcPos.x = ((animationCount/animationSpeed)%4) * srcSize.w();
    }

    @Override
    public void render(RenderSystem rs, Vector2f pos) {
        rs.draw(spriteSheet, pos, size, srcPos, srcSize, false, false);
    }
}
