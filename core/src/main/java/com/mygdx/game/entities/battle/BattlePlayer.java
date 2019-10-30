package com.mygdx.game.entities.battle;


import com.mygdx.game.GUI.components.BattleCurrentCardsComponent;
import com.mygdx.game.PlayerVars;
import com.mygdx.game.action.ActionWait;
import com.mygdx.game.action.ActionUseCard;
import com.mygdx.game.animation.BattleMoveAnimation;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.items.cards.Deck;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.stats.BattleStats;
import com.mygdx.game.util.Vec2i;

public class BattlePlayer extends BattleLiving {

    private Deck hand;
    private Card secondary;

    public BattlePlayer(SceneBattle scene, SceneBattleTile tile, int health, int maxHealth) {
        super(scene, tile, "player", Facing.RIGHT, health, maxHealth, BattleStats.BASE_STATS_PLAYER);
        acceptedTileTypes = new SceneBattleTileType[]{SceneBattleTileType.FRIENDLY, SceneBattleTileType.NEUTRAL};

        hand = new Deck(5);
        secondary = PlayerVars.secondaryAttack;
    }

    @Override
    public void update() {
        super.update();
        spriteSheet.update();
    }

    @Override
    public void render(RenderSystem rs) {
        super.render(rs);
    }

    public void useCard(BattleCurrentCardsComponent battleCurrentCards) {
        if(!hand.isEmpty() && canUseItem()) {
            Card card = hand.pop();
            getActionQueue().add(new ActionWait(this, card.getInitialLock()));
            getActionQueue().add(new ActionUseCard(this, scene, card));
            getActionQueue().add(new ActionWait(this, card.getFinalLock()));

            battleCurrentCards.useCard();
        }
    }

    public Deck getCards() {
        return hand;
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
    public void moveTo(Vec2i iPos) {
        SceneBattleTile oldTile = scene.getTile(getIndexPos());
        SceneBattleTile newTile = scene.getTile(iPos);

        scene.addAnimation(new BattleMoveAnimation(oldTile, newTile, this));
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
