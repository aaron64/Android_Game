package com.mygdx.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

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

    public void draw(Texture image, Vector2f pos) {
        batch.draw(image, pos.x, pos.y);
    }

    public void draw(Texture image, Vector2f pos, Vector2i size) {
        batch.draw(image, pos.x, pos.y, size.w(), size.h());
    }

    public void draw(Texture image, float x, float y, int w, int h) {
        batch.draw(image, x, y, w, h);
    }

    public void draw(Texture image, Vector2f pos, Vector2i size, Vector2i srcPos, Vector2i srcSize, boolean flipX, boolean flipY) {
        batch.draw(image, pos.x, pos.y, size.w(), size.h(), srcPos.x, srcPos.y, srcSize.w(), srcSize.h(), flipX, flipY);
    }

    public void drawText(BitmapFont font, String text, Vector2f pos) {
        font.draw(batch, text, pos.x, pos.y);
    }

    public void drawTextCentered(BitmapFont font, String text, Vector2f centerPos) {
        Vector2f pos = new Vector2f(centerPos);
        pos.x -= FontUtil.getTextSize(font, text).x/2;
        drawText(font, text, pos);
    }

    public void drawTextRight(BitmapFont font, String text, Vector2f rightPos) {
        Vector2f pos = new Vector2f(rightPos);
        pos.x -= FontUtil.getTextSize(font, text).x;
        drawText(font, text, pos);
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

    public void restart() {
        batch.begin();
    }

    public void end() {
        batch.end();
    }

    public void setColor(float r, float g, float b, float a) {
        batch.setColor(r, g, b, a);
    }

    public void setColor(Color color) {
        batch.setColor(color);
    }

    public void resetColor() {
        batch.setColor(1, 1, 1 ,1);
    }

    public void beginGUI() {
        if(camera != null) {
            Matrix4 uiMatrix = camera.combined.cpy();
            uiMatrix.setToOrtho2D(0, 0, Window.getWidth(), Window.getHeight());
            batch.setProjectionMatrix(uiMatrix);
        }
    }

    public void setShader(ShaderProgram shader) {
        if(shader != null) {
            Gdx.app.log("INFO", "SHADER LOG: " + shader.getLog());
            shader.setUniformMatrix("u_projTrans", batch.getProjectionMatrix());
        }
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

    public Vector2f getWorldPos(int x, int y) {
        Vector3 pos = camera.unproject(new Vector3(x, y, 0));
        return new Vector2f(pos.x, pos.y);
    }

    public void centerCameraOn(Entity e) {
        Vector2f pos = e.getPos();
        Vector2i size = e.getSize();
        centerCameraOn(new Vector2f(pos.x + size.w()/2, pos.y + size.h()/2));
    }

    public void centerCameraOn(Vector2f pos) {
        camera.position.set(pos.x, pos.y, 0);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
}
