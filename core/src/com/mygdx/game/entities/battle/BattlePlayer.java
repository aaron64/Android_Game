package com.mygdx.game.entities.battle;


import com.mygdx.game.items.cards.Card;
import com.mygdx.game.items.cards.CardLoader;
import com.mygdx.game.items.cards.Deck;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.util.Vector2i;

public class BattlePlayer extends BattleLiving  {

    private Deck deck;
    public BattlePlayer(SceneBattle scene, SceneBattleGrid grid, Vector2i pos, int health, int maxHealth) {
        super(scene, grid, pos, "player", Facing.RIGHT, health, maxHealth);
        acceptedTileTypes = new SceneBattleTileType[]{SceneBattleTileType.FRIENDLY, SceneBattleTileType.NEUTRAL};

        setSize(scene.getGrid().getTile(0,0).getSize());

        // TODO:
        deck = new Deck(5);
        deck.addCard(CardLoader.buildCard("Bow"));
        deck.addCard(CardLoader.buildCard("Bow"));
        deck.addCard(CardLoader.buildCard("Magic"));
        deck.addCard(CardLoader.buildCard("Sword"));
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

    @Override
    public void trigger(String name) {

    }
}
