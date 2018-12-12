package com.mygdx.game.scenes.deck;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.items.cards.BowCard;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.items.cards.CardLoader;
import com.mygdx.game.items.cards.Deck;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.Window;

public class SceneDeck extends Scene implements GestureHandler {

    private Deck hand, deck;

    private Vector2 leftSideOffset;
    private Vector2 rightSideOffset;

    private Vector2 cardSize;

    private Texture cardBackground;

    private int spacing;

    public SceneDeck() {
        super();
        hand = new Deck(5);
        deck = new Deck(5);

        hand.addCard(CardLoader.buildCard("Bow"));
        deck.addCard(CardLoader.buildCard("Bow"));
        hand.addCard(CardLoader.buildCard("Bow"));
        deck.addCard(CardLoader.buildCard("Bow"));
        hand.addCard(CardLoader.buildCard("Bow"));
        deck.addCard(CardLoader.buildCard("Bow"));

        gestureHandler = new GestureUtil(this);


        leftSideOffset = new Vector2(Window.percLeft(0.05f), Window.percTop(0.1f));
        rightSideOffset = new Vector2(Window.percLeft(0.55f), Window.percTop(0.1f));

        cardSize = new Vector2(Window.percWidth(0.4f), Window.percHeight(0.1f));
        spacing = Window.percHeight(0.02f);

        cardBackground = new Texture("misc/icon_holder_battle.png");
    }

    public void update() {
        super.update();
    }

    @Override
    public void render() {
        rs.begin();

        drawLeftSide();
        drawRightSide();

        rs.end();
    }

    private void drawLeftSide() {
        for(int i = 0; i < hand.getSize(); i++) {
            Card card = hand.getCard(i);
            Vector2 pos = new Vector2(leftSideOffset.x, leftSideOffset.y - i * (cardSize.y + spacing));

            rs.draw(cardBackground, pos, cardSize);
        }
    }

    private void drawRightSide() {
        for(int i = 0; i < deck.getSize(); i++) {
            Card card = deck.getCard(i);
            Vector2 pos = new Vector2(rightSideOffset.x, rightSideOffset.y - i * (cardSize.y + spacing));

            rs.draw(cardBackground, pos, cardSize);
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void touchDown(float x, float y, int pointer, int button) {

    }

    @Override
    public void fling(float vx, float vy, int button) {

    }

    @Override
    public void zoom(float initialDistance, float distance) {

    }

    @Override
    public void hold(float x, float y) {

    }

    @Override
    public void doubleTap(float x, float y) {

    }

    @Override
    public void tap(float x, float y) {

    }
}
