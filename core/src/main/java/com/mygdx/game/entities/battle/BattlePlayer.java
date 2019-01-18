package com.mygdx.game.entities.battle;


import com.mygdx.game.PlayerVars;
import com.mygdx.game.items.cards.Deck;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;

public class BattlePlayer extends BattleLiving {

    private Deck hand;
    private com.mygdx.game.items.cards.Card secondary;
    public BattlePlayer(SceneBattle scene, SceneBattleTile tile, int health, int maxHealth) {
        super(scene, tile, "player", Facing.RIGHT, health, maxHealth, null);
        acceptedTileTypes = new SceneBattleTileType[]{SceneBattleTileType.FRIENDLY, SceneBattleTileType.NEUTRAL};

        getSize().multiply(scene.getGrid().getTile(0,0).getSize().w() / getSize().w()* 0.6f);

        hand = new Deck(5);
        secondary = PlayerVars.secondaryAttack;
    }

    @Override
    public void update() {
        super.update();
        hand.refresh();
    }

    public void useCard() {
        if(!hand.isEmpty() && canUseItem()) {
            lockFor(15);
            hand.pop().use(scene, this);
        }
    }

    public void useSecondary() {
        if(canUseItem()) {
            secondary.use(scene, this);
            lockFor(10);
        }
    }

    public void move(float vx, float vy) {
        if(Math.abs(vx) > Math.abs(vy)) {
            if(vx > 0) moveRight(); else moveLeft();
        } else {
            if(vy > 0) moveDown(); else moveUp();
        }
    }

    @Override
    public void die() {

    }

    @Override
    public void trigger(String name) {

    }

    public Deck getHand() {
        return hand;
    }

    public void setHand(Deck hand) {
        this.hand = hand;
    }
}
