package com.mygdx.game.scenes.game_menu.deck;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.GUI.components.ExitGameMenuButtonComponent;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.items.cards.CardLoader;
import com.mygdx.game.items.cards.Deck;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.util.Window;

public class SceneDeckEditor extends Scene implements GestureHandler {

    private BitmapFont font;

    private Deck hand, deck;

    private Vector2f leftSideOffset;
    private Vector2f rightSideOffset;

    private Vector2i cardSize;

    private int spacing;

    public SceneDeckEditor() {
        super();

        Vector2f menuButtonPos = new Vector2f(Window.percLeft(0.05f), Window.percTop(0.05f));
        gui.addComponent(new ExitGameMenuButtonComponent(gui, menuButtonPos));

        hand = new Deck(5);
        deck = new Deck(5);

        hand.addCard(CardLoader.buildCard("Bow"));
        deck.addCard(CardLoader.buildCard("Bow"));
        hand.addCard(CardLoader.buildCard("Bow"));
        deck.addCard(CardLoader.buildCard("Bow"));
        hand.addCard(CardLoader.buildCard("Bow"));
        deck.addCard(CardLoader.buildCard("Bow"));

        gestureHandler = new GestureUtil(this);


        leftSideOffset = new Vector2f(Window.percLeft(0.05f), Window.percTop(0.15f));
        rightSideOffset = new Vector2f(Window.percLeft(0.55f), Window.percTop(0.15f));

        cardSize = new Vector2i(Window.percWidth(0.4f), Window.percHeight(0.15f));
        spacing = Window.percHeight(0.02f);
    }

    public void update() {
        super.update();
    }

    @Override
    public void render() {
        rs.begin();

        for(int i = 0; i < hand.getSize(); i++) {
            Card card = hand.peekCard(i);
            Vector2f pos = new Vector2f(leftSideOffset.x, leftSideOffset.y - (i+1) * (cardSize.y + spacing));

            card.drawDeckScene(rs, pos, cardSize);
        }

        for(int i = 0; i < hand.getSize(); i++) {
            Card card = hand.peekCard(i);
            Vector2f pos = new Vector2f(rightSideOffset.x, rightSideOffset.y - (i+1) * (cardSize.y + spacing));

            card.drawDeckScene(rs, pos, cardSize);
        }

        gui.render(rs);

        rs.end();
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
        gui.tap(x, y);
    }
}
