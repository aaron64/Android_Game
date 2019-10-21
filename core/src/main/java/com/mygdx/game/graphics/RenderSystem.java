package com.mygdx.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class RenderSystem {

    private OrthographicCamera camera;
    private SpriteBatch batch;

    private ShapeRenderer shapeRenderer;

    public static ShaderProgram iconShader;
    public static ShaderProgram elementOverlayShader;

    private FrameBuffer fbo;
    private TextureRegion fbo_tr;

    public enum TextAlign {
        LEFT,
        RIGHT,
        CENTER
    }

    public RenderSystem() {
        batch = new SpriteBatch();
        ShaderProgram.pedantic = false;
        iconShader = new ShaderProgram(Gdx.files.internal("shaders/vertex_default.glsl").readString(),
                Gdx.files.internal("shaders/fragment_card_icon.glsl").readString());
        elementOverlayShader = new ShaderProgram(Gdx.files.internal("shaders/vertex_default.glsl").readString(),
                Gdx.files.internal("shaders/fragment_element_overlay.glsl").readString());

        shapeRenderer = new ShapeRenderer();

        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, Window.getWidth(), Window.getHeight(), false);
        fbo_tr = new TextureRegion(fbo.getColorBufferTexture());
        fbo_tr.flip(false, true);
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

    public void draw(Texture image, float x, float y, int w, int h, Vector2i srcPos, Vector2i srcSize, boolean flipX, boolean flipY) {
        batch.draw(image, x, y, w, h, srcPos.x, srcPos.y, srcSize.w(), srcSize.h(), flipX, flipY);
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

    public void drawStatic(Texture texture, Vector2f pos, Vector2i size) {
        if(camera != null) {
            Matrix4 uiMatrix = camera.combined.cpy();
            uiMatrix.setToOrtho2D(0, 0, Window.getWidth(), Window.getHeight());
            batch.setProjectionMatrix(uiMatrix);

            batch.draw(texture, pos.x, pos.y, size.w(), size.h());

            camera.update();
            batch.setProjectionMatrix(camera.combined);
        }
    }

    public void beginFBO() {
        fbo.begin();
    }

    public void endFBO() {
        fbo.end();
    }

    public void drawFBO() {
        if(camera != null) {
            Matrix4 uiMatrix = camera.combined.cpy();
            uiMatrix.setToOrtho2D(0, 0, Window.getWidth(), Window.getHeight());
            batch.setProjectionMatrix(uiMatrix);
        }
        batch.draw(fbo_tr, 0, 0, Window.getWidth(), Window.getHeight());
    }


    public void begin() {
        if(camera != null) {
            Matrix4 uiMatrix = camera.combined.cpy();
            uiMatrix.setToOrtho2D(0, 0, Window.getWidth(), Window.getHeight());
            batch.setProjectionMatrix(uiMatrix);
        }
        Gdx.gl.glClearColor(0f, 0f, 0f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
    }

    public void beginMainContent() {
        if(camera != null) {
            camera.update();
            batch.setProjectionMatrix(camera.combined);
        }
    }

    public void beginGUI() {
        if(camera != null) {
            Matrix4 uiMatrix = camera.combined.cpy();
            uiMatrix.setToOrtho2D(0, 0, Window.getWidth(), Window.getHeight());
            batch.setProjectionMatrix(uiMatrix);
        }
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

    public void setColor(Color color, float alpha) {
        setColor(color.r, color.g, color.b, alpha);
    }

    public void setColor(Color color) {
        batch.setColor(color);
    }

    public void resetColor() {
        batch.setColor(1, 1, 1 ,1);
    }

    public void setShader(ShaderProgram shader) {
        if(shader != null) {
            Gdx.app.log("INFO", "SHADER LOG: " + shader.getLog());
            shader.setUniformMatrix("u_projTrans", batch.getProjectionMatrix());
        } else {
            Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        }
        batch.setShader(shader);
    }

    public void setUniformTexture(String name, Texture texture, int pos) {
        if(batch.getShader() != null) {
            texture.bind(pos);

            //Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0 + pos);
            batch.getShader().setUniformi(name, pos);
            //Gdx.graphics.getGL20().glActiveTexture(GL20.GL_TEXTURE0);
        } else {

        }
    }

    public void setUniform4f(String name, float v0, float v1, float v2, float v3) {
        if(batch.getShader() != null) {
            batch.getShader().setUniformf(name, v0, v1, v2, v3);
        }
    }

    public void setUniform1f(String name, float v0) {
        if(batch.getShader() != null) {
            batch.getShader().setUniformf(name, v0);
        }
    }

    public void beginShader() {
        batch.getShader().begin();
    }

    public void endShader() {
        batch.getShader().end();
    }

    public void setOverlayMode() {
        batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);
    }

    public void setNormalMode() {
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void setLightMode() {
        batch.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_SRC_ALPHA);
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
