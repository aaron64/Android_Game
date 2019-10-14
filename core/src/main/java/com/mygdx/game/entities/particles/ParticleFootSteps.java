package com.mygdx.game.entities.particles;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.scenes.main_area.SceneMainAreaTileType;
import com.mygdx.game.util.Vector2f;

public class ParticleFootSteps extends ParticleSystem {

    private Entity entity;
    private SceneMainArea scene;

    private Texture grass, water;

    public ParticleFootSteps(Vector2f pos, String name, int n, int nDiff, float xDiff, float yDiff, int size, int sizeDiff, float xv, float xvDiff, float yv, float yvDiff, int life, int lifeDiff, Entity entity, SceneMainArea scene) {
        super(pos, name, n, nDiff, xDiff, yDiff, size, sizeDiff, xv, xvDiff ,yv, yvDiff, life, lifeDiff, 4);

        grass = getImage();
        water = new Texture("entities/particles/water.png");

        this.entity = entity;
        this.scene = scene;
    }

    @Override
    public void update() {
        super.update();
        SceneMainAreaTileType tileType = scene.getGrid().getTile(getPos()).getType();
        switch(tileType) {
            case WATER:
                setImage(water);
                break;
            case NORMAL:
                setImage(grass);
                break;
        }
    }
}
