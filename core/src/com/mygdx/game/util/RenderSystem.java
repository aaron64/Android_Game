package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class RenderSystem {

    private SpriteBatch batch;

    public RenderSystem() {
        batch = new SpriteBatch();
    }

    public void draw(Texture image, Vector2 pos) {
        batch.draw(image, pos.x, pos.y, 256, 192);
    }

    public void draw(Texture image, Vector2 pos, Vector2 size) {
        batch.draw(image, pos.x, pos.y, size.x, size.y);
    }


    public void begin() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
    }

    public void end() {
        batch.end();
    }
}
