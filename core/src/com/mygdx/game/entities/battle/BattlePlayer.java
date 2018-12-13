package com.mygdx.game.entities.battle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.items.cards.CardLoader;
import com.mygdx.game.items.cards.Deck;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTileType;

public class BattlePlayer extends BattleLiving  {

    private Deck deck;
    public BattlePlayer(SceneBattle scene, SceneBattleGrid grid, Vector2 pos, int health) {
        super(scene, grid, pos, "player", Facing.RIGHT, health);
        acceptedTileTypes = new SceneBattleTileType[]{SceneBattleTileType.FRIENDLY, SceneBattleTileType.NEUTRAL};
        setSize(scene.getGrid().getTile(0,0).getSize());

        // TODO:
        deck = new Deck(5);
        deck.addCard(CardLoader.buildCard("Bow"));
        deck.addCard(CardLoader.buildCard("Bow"));
        deck.addCard(CardLoader.buildCard("Magic"));
    }

    @Override
    public void update() {
        super.update();
    }

    public void useCard() {
        if(!deck.isEmpty()) {
            lockFor(25);
            deck.pop().use(scene, this);
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

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
