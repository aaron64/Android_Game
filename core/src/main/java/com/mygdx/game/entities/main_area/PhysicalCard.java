package com.mygdx.game.entities.main_area;

import com.mygdx.game.Game;
import com.mygdx.game.PlayerVars;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.Vector2f;

public class PhysicalCard extends ChestItem {

    private Vector2f velocity;
    private float yTrigger;
    private Card card;

    public PhysicalCard(SceneMainArea scene, Vector2f pos, Card card) {
        super(scene, pos, "physical_card");
        solid = false;

        yTrigger = pos.y - 20;
        velocity = new Vector2f((float) (Math.random() * 10 - 5), 7);

        this.card = card;

        setImage(card.getIcon());
        resetSize();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void clickOn() {
        if (grounded && MathUtil.getDistance(getPos(), scene.getPlayer().getPos()) < PlayerVars.clickDistance) {

        }
    }

    @Override
    public void initializeImage() {

    }
}
