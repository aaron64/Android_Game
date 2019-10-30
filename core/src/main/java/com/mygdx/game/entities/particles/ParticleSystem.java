package com.mygdx.game.entities.particles;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.SpriteSheet;
import com.mygdx.game.util.Vec2f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class ParticleSystem extends Entity {

    protected ArrayList<Float> xList;
    protected ArrayList<Float> yList;
    protected ArrayList<Integer> sizeList;
    protected ArrayList<Integer> textureList;

    protected ArrayList<Float> xVelocityList;
    protected ArrayList<Float> yVelocityList;

    protected ArrayList<Boolean> flipXList;
    protected ArrayList<Boolean> flipYList;

    protected ArrayList<Integer> lifeList;

    protected ArrayList<Integer> removeList;

    protected SpriteSheet sheet;

    protected float xDiff, yDiff;
    protected int size, sizeDiff;
    protected float xVelocity, xVelocityDiff, yVelocity, yVelocityDiff;
    protected int life, lifeDiff;
    protected int textures;

    public ParticleSystem(Vec2f pos, String name, int n, int nDiff, float xDiff, float yDiff, int size, int sizeDiff, float xVelocity, float xVelocityDiff, float yVelocity, float yVelocityDiff, int life, int lifeDiff, int textures) {
        super(pos, "particles", name);

        xList = new ArrayList<Float>();
        yList = new ArrayList<Float>();
        sizeList = new ArrayList<Integer>();
        textureList = new ArrayList<Integer>();
        xVelocityList = new ArrayList<Float>();
        yVelocityList = new ArrayList<Float>();
        flipXList = new ArrayList<Boolean>();
        flipYList = new ArrayList<Boolean>();
        lifeList = new ArrayList<Integer>();

        removeList = new ArrayList<Integer>();

        n += (int)((float)Math.random() * nDiff - nDiff/2);
        this.xDiff = xDiff;
        this.yDiff = yDiff;
        this.size = size;
        this.sizeDiff = sizeDiff;
        this.xVelocity = xVelocity;
        this.xVelocityDiff = xVelocityDiff;
        this.yVelocity = yVelocity;
        this.yVelocityDiff = yVelocityDiff;
        this.life = life;
        this.lifeDiff = lifeDiff;
        this.textures = textures;

        sheet = new SpriteSheet(getImage(), textures);

        addParticles(n);
    }

    public void addParticles(int n) {
        Random random = new Random();

        for(int i = 0; i < n; i++) {
            float x = getPos().x + ((float)Math.random() * xDiff - xDiff/2);
            float y = getPos().y + ((float)Math.random() * yDiff - yDiff/2);

            int s = size + (int)((float)Math.random() * sizeDiff - sizeDiff/2);

            float xv = xVelocity + ((float)Math.random() * xVelocityDiff - xVelocityDiff/2);
            float yv = yVelocity + ((float)Math.random() * yVelocityDiff - yVelocityDiff/2);

            int l = life + (int)((float)Math.random() * lifeDiff - lifeDiff/2);

            int t = (int)(Math.random() * textures);

            xList.add(x);
            yList.add(y);
            sizeList.add(s);
            textureList.add(t);
            xVelocityList.add(xv);
            yVelocityList.add(yv);
            flipXList.add(random.nextBoolean());
            flipYList.add(random.nextBoolean());
            lifeList.add(l);
        }
    }

    @Override
    public void update() {
        for(int i = 0; i < getParticleCount(); i++) {
            float dx = xList.get(i) + xVelocityList.get(i);
            float dy = yList.get(i) + yVelocityList.get(i);
            xList.set(i, dx);
            yList.set(i, dy);

            lifeList.set(i, lifeList.get(i) - 1);
            if(lifeList.get(i) < 0)
                removeList.add(i);
        }

        Collections.sort(removeList, Collections.<Integer>reverseOrder());
        for(Integer index : removeList) {
            int i = index;
            xList.remove(i);
            yList.remove(i);
            sizeList.remove(i);
            textureList.remove(i);
            xVelocityList.remove(i);
            yVelocityList.remove(i);
            flipXList.remove(i);
            flipYList.remove(i);
            lifeList.remove(i);
        }

        removeList.clear();
    }

    @Override
    public void render(RenderSystem rs) {
        for(int i = 0; i < getParticleCount(); i++) {
            sheet.render(rs, xList.get(i), yList.get(i),
                    sizeList.get(i), sizeList.get(i),
                    flipXList.get(i), flipYList.get(i),
                    textureList.get(i));
        }
    }

    protected int getParticleCount() {
        return xList.size();
    }

    public float getxDiff() {
        return xDiff;
    }

    public void setxDiff(float xDiff) {
        this.xDiff = xDiff;
    }

    public float getyDiff() {
        return yDiff;
    }

    public void setyDiff(float yDiff) {
        this.yDiff = yDiff;
    }

    public int getSizeDiff() {
        return sizeDiff;
    }

    public void setSizeDiff(int sizeDiff) {
        this.sizeDiff = sizeDiff;
    }

    public float getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public float getxVelocityDiff() {
        return xVelocityDiff;
    }

    public void setxVelocityDiff(float xVelocityDiff) {
        this.xVelocityDiff = xVelocityDiff;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    public float getyVelocityDiff() {
        return yVelocityDiff;
    }

    public void setyVelocityDiff(float yVelocityDiff) {
        this.yVelocityDiff = yVelocityDiff;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLifeDiff() {
        return lifeDiff;
    }

    public void setLifeDiff(int lifeDiff) {
        this.lifeDiff = lifeDiff;
    }

    @Override
    public void setImage(Texture texture) {
        sheet.setImage(texture);
    }
}
