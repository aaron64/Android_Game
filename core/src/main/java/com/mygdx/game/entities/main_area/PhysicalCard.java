package com.mygdx.game.entities.main_area;

import com.mygdx.game.Game;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.Vector2f;

public class PhysicalCard extends MainAreaInteractive {

    private Vector2f velocity;
    private float yTrigger;
    private Card card;

    public PhysicalCard(SceneMainArea scene, Vector2f pos, Card card) {
        super(scene, pos, "physical_card");
        solid = false;

        yTrigger = pos.y - 20;
        velocity = new Vector2f((float) (Math.random() * 10 - 5), 7);

        this.card = card;
    }

    @Override
    public void update() {
        if (getPos().y > yTrigger) {
            move(velocity);
            velocity.y += Game.getGravity();
        } else {
            if (collidesWith(scene.getPlayer()) && !scene.getPlayer().atMaxHealth()) {
                scene.getPlayer().heal(10);
                scene.removeEntity(this);
            }
        }
    }

    @Override
    public void clickOn() {
        if (checkDistance()) {

        }
    }
}
