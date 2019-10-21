package com.mygdx.game.entities.battle.misc;

import com.mygdx.game.Game;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.items.cards.BombCard;
import com.mygdx.game.items.cards.ThrowableSize;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class Bomb extends BattleEntity {

    private int throwTime;
    private int throwTimeCounter;

    private Vector2f velocity;
    private float distance;

    private float yTrigger;

    private BattleLiving user;

    private BombCard card;

    private Vector2i dest;

    public Bomb(SceneBattle scene, BombCard card, Vector2i indexPos, Vector2i dest, ThrowableSize size, BattleLiving user) {
        super(scene, indexPos, "misc/bomb");
        moveY(scene.getGrid().getTile(0,0).getSize().y/2);

        this.dest = dest;

        this.card = card;

        yTrigger = getPos().y - 1;

        this.user = user;

        this.distance = scene.getGrid().getAbsoluteTilePosition(dest).x - scene.getGrid().getAbsoluteTilePosition(indexPos).x;
        throwTime = 25;
        throwTimeCounter = 0;

        velocity = new Vector2f(0,0);

        float vMag = distance / throwTime;

        float theta = (float) Math.asin(((2 * Game.getGravity() * distance / (vMag * vMag)/2)%(2*Math.PI)))/2f;

        velocity.x = (float) (vMag * Math.cos(theta));
        velocity.y = (float) (vMag * Math.sin(theta)) * -1;

        float scaleVel = vMag / velocity.x;

        velocity.x *= scaleVel;
        velocity.y *= scaleVel;
    }

    @Override
    public void update() {
        throwTimeCounter++;
        move(velocity);
        velocity.y += Game.getGravity();

        if(getPos().y < yTrigger) {
            BattleEntity be = scene.getTile(dest).getEntity();
            if(be != null) {
                be.hit(card.calculateDamage(be), card.getElement());
            }
            scene.removeEntity(this);
            scene.addEntity(new Explosion(scene, dest));
        }
    }

    public void explode() {

    }
}
