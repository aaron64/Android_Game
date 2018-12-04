package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;

public class RenderSystem {

    private OrthographicCamera camera;
    private SpriteBatch batch;

    public RenderSystem() {
        batch = new SpriteBatch();
    }

    public void draw(Entity e) {
        batch.draw(e.getImage(), e.getPos().x, e.getPos().y, e.getSize().x, e.getSize().y);
    }

    public void draw(Texture image, Vector2 pos) {
        batch.draw(image, pos.x, pos.y);
    }

    public void draw(Texture image, Vector2 pos, Vector2 size) {
        batch.draw(image, pos.x, pos.y, size.x, size.y);
    }


    public void begin() {
        if(camera != null) {
            camera.update();
            batch.setProjectionMatrix(camera.combined);
        }
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
    }

    public void end() {
        batch.end();
    }

    public void centerCameraOn(Entity e) {
        Vector2 pos = e.getPos();
        Vector2 size = e.getSize();
        centerCameraOn(new Vector2(pos.x + size.x/2, pos.y + size.y/2));
    }

    public void centerCameraOn(Vector2 pos) {
        camera.position.set(pos.x, pos.y, 0);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
}
