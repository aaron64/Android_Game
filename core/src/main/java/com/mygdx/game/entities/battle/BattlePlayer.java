package com.mygdx.game.entities.battle;


import com.mygdx.game.Game;
import com.mygdx.game.PlayerVars;
import com.mygdx.game.action.ActionLock;
import com.mygdx.game.action.ActionUseCard;
import com.mygdx.game.animation.BattleMoveAnimation;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.items.cards.Deck;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.util.Vector2i;

public class BattlePlayer extends BattleLiving {

    private Deck hand;
    private Card secondary;

    public BattlePlayer(SceneBattle scene, SceneBattleTile tile, int health, int maxHealth) {
        super(scene, tile, "player", Facing.RIGHT, health, maxHealth);
        acceptedTileTypes = new SceneBattleTileType[]{SceneBattleTileType.FRIENDLY, SceneBattleTileType.NEUTRAL};

        getSize().multiply(scene.getGrid().getTile(0,0).getSize().w() / getSize().w()* 0.6f);

        hand = new Deck(5);
        secondary = PlayerVars.secondaryAttack;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(RenderSystem rs) {
        super.render(rs);

        rs.setShader(RenderSystem.elementOverlayShader);
        rs.beginShader();

        rs.setUniform1f("u_time", (float) Game.time);
        rs.setUniformTexture("u_texture_overlay", Image.getImage("misc/Grass_overlay"), 1);
        super.render(rs);
        rs.setShader(null);
        //rs.endShader();
    }

    public void useCard() {
        if(!hand.isEmpty() && canUseItem()) {
            Card card = hand.pop();
            getActionQueue().add(new ActionLock(this, card.getInitialLock()));
            getActionQueue().add(new ActionUseCard(this, scene, card));
            getActionQueue().add(new ActionLock(this, card.getFinalLock()));
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
    public void moveTo(Vector2i iPos) {
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
