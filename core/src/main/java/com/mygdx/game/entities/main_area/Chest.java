package com.mygdx.game.entities.main_area;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.PlayerVars;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.scenes.main_area.SceneMainAreaTile;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.Vec2f;

import java.util.ArrayList;


public class Chest extends MainAreaEntity {

    protected static int[] lowQualityDistribution = {35, 35, 25, 4, 1, 0};
    protected static int[] standardQualityDistribution = {5, 15, 40, 20, 15, 5};
    protected static int[] standardGuarenteedQualityDistribution = {0, 0, 70, 30, 0, 0};
    protected static int[] highQualityDistribution = {0,  0, 0, 20, 60, 20};

    public enum ChestType {
        LOW("chest_low", lowQualityDistribution, 1f),
        STANDARD("chest_standard", standardGuarenteedQualityDistribution, 1.5f),
        HIGH("chest_high", highQualityDistribution, 2f),
        ELEMENTAL("chest_standard", standardQualityDistribution, 1f);

        private String res;
        private float multiplier;
        private int[] cardDistribution;

        ChestType(String res, int[] cardDistribution, float multiplier) {
            this.res = res;
            this.cardDistribution = cardDistribution;
            this.multiplier = multiplier;
        }
    }



    private boolean open;
    private Texture openTexture;
    private ArrayList<Entity> contents;

    public Chest(SceneMainArea scene, Vec2f pos, SceneMainAreaTile tile, ChestType chestType) {
        super(scene, pos, chestType.res);
        setSize(tile.getSize());
        contents = new ArrayList<Entity>();
        open = false;

        openTexture = Image.getImage("entities/main_area/" + chestType.res + "_open");

        for(int i = 0; i < 3 * chestType.multiplier; i++) {
            contents.add(new HealthBlob(scene, new Vec2f(getPos())));
        }

        //Card card = CardFactory.buildRandomCard(chestType.cardDistribution);
        //contents.add(new PhysicalCard(scene, new Vec2f(getPos()), card));
    }

    @Override
    public void update() {

    }

    @Override
    public void clickOn() {
        if(MathUtil.getDistance(getPos(), scene.getPlayer().getPos()) < PlayerVars.clickDistance && !open) {
            open();
        }
    }

    private void open() {
        open = true;
        setImage(openTexture);

        for(Entity e : contents) {
            scene.addEntity(e);
        }

        contents.clear();
    }
}
