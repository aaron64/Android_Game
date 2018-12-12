package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.entities.Entity;

public class RenderSystem {

    private OrthographicCamera camera;
    private SpriteBatch batch;

    public static ShaderProgram iconShader;

    public RenderSystem() {
        batch = new SpriteBatch();
        iconShader = new ShaderProgram(Gdx.files.internal("shaders/vertex_default.glsl").readString(),
                Gdx.files.internal("shaders/fragment_card_icon.glsl").readString());
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

    public void drawText(BitmapFont font, String text, Vector2 pos) {
        font.draw(batch, text, pos.x, pos.y);
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

    public void beginGUI() {
        if(camera != null) {
            Matrix4 uiMatrix = camera.combined.cpy();
            uiMatrix.setToOrtho2D(0, 0, Window.getWidth(), Window.getHeight());
            batch.setProjectionMatrix(uiMatrix);
        }
    }

    public void setShader(ShaderProgram shader) {
        batch.setShader(shader);
    }

    public void setOverlayMode() {
        batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);
    }

    public void setNormalMode() {
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void setMultiplyMode() {
       // Gdx.gl.glBlendFunc( GL20.GL_ZERO, GL20.GL_SRC_COLOR );
        Gdx.gl.glBlendFunc( GL20.GL_SRC_ALPHA, GL20.GL_ONE );
    }

    public Vector2 getWorldPos(int x, int y) {
        Vector3 pos = camera.unproject(new Vector3(x, y, 0));
        return new Vector2(pos.x, pos.y);
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
