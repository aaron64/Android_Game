package com.mygdx.game.scenes.game_menu.deck;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Game;
import com.mygdx.game.PlayerVars;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.items.cards.Deck;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.graphics.Window;

public class SceneDeckEditor extends Scene implements GestureHandler {

    private BitmapFont font;

    private Deck deck, pack;

    private Vector2f leftSideOffset;
    private Vector2f rightSideOffset;

    private Card cardHeld;
    private Vector2f cardHeldPos;
    private Vector2f cardHeldOffset;

    private Vector2i cardSize;

    private int spacing;

    private BitmapFont deckHeader, packHeader;
    private Vector2f deckHeaderPos, packHeaderPos;

    public SceneDeckEditor() {
        super();

        deck = PlayerVars.deck;
        pack = PlayerVars.pack;

        cardHeld = null;
        cardHeldPos = new Vector2f();
        cardHeldOffset = new Vector2f();

        gestureHandler = new GestureUtil(this);


        leftSideOffset = new Vector2f(Window.percLeft(0.05f), Window.percTop(0.15f));
        rightSideOffset = new Vector2f(Window.percLeft(0.55f), Window.percTop(0.15f));

        cardSize = new Vector2i(Window.percWidth(0.4f), Window.percHeight(0.15f));
        spacing = Window.percHeight(0.02f);

        deckHeader = FontUtil.getFont(48);
        packHeader = FontUtil.getFont(48);

        deckHeaderPos = new Vector2f(leftSideOffset.x + cardSize.w()/2, leftSideOffset.y);
        packHeaderPos = new Vector2f(rightSideOffset.x + cardSize.w()/2, rightSideOffset.y);
    }

    public void update() {
        super.update();
        deck.refresh();
        pack.refresh();
    }

    @Override
    public void render() {
        Game.getLastScene().renderInBackground();
        rs.resetColor();

        rs.restart();

        rs.drawTextCentered(deckHeader, "Deck", deckHeaderPos);
        rs.drawTextCentered(packHeader, "Pack", packHeaderPos);

        for(int i = 0; i < deck.getSize(); i++) {
            Card card = deck.peekCard(i);

            Vector2f pos = new Vector2f(leftSideOffset.x, leftSideOffset.y - (i+1) * (cardSize.h() + spacing));

            if(cardHeld != null && cardHeldPos.x < Window.getCenter().x && cardHeldPos.y > pos.y) {
                pos.y -= cardSize.h() + spacing;
            }

            //card.drawDeckScene(rs, pos, cardSize);
        }

        for(int i = 0; i < pack.getSize(); i++) {
            Card card = pack.peekCard(i);

            Vector2f pos = new Vector2f(rightSideOffset.x, rightSideOffset.y - (i+1) * (cardSize.h() + spacing));
            if(cardHeld != null && cardHeldPos.x > Window.getCenter().x && cardHeldPos.y > pos.y) {
                pos.y -= cardSize.h() + spacing;
            }

            //card.drawDeckScene(rs, pos, cardSize);
        }

        //if(cardHeld != null)
            //cardHeld.drawDeckScene(rs, cardHeldPos, cardSize);

        gui.render(rs);

        rs.end();
    }

    @Override
    public void onPushed() {

    }

    @Override
    public void onPopped() {

    }

    @Override
    public void onExit() {

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
        if(cardHeld != null) {
            cardHeldPos.x = x - cardHeldOffset.x;
            cardHeldPos.y = y - cardHeldOffset.y;
        } else {
            if(x > Window.getCenter().x) {
                for (int i = 0; i < pack.getSize(); i++) {
                    Card card = pack.peekCard(i);
                    Vector2f pos = new Vector2f(rightSideOffset.x, rightSideOffset.y - (i + 1) * (cardSize.h() + spacing));

                    if (new Rectangle(pos.x, pos.y, cardSize.w(), cardSize.h()).contains(x, y)) {
                        pack.remove(i);
                        cardHeld = card;
                        cardHeldOffset.x = x - pos.x;
                        cardHeldOffset.y = y - pos.y;
                    }
                }
            } else {
                for (int i = 0; i < deck.getSize(); i++) {
                    Card card = deck.peekCard(i);
                    Vector2f pos = new Vector2f(leftSideOffset.x, leftSideOffset.y - (i+1) * (cardSize.h() + spacing));

                    if (new Rectangle(pos.x, pos.y, cardSize.w(), cardSize.h()).contains(x, y)) {
                        deck.remove(i);
                        cardHeld = card;
                        cardHeldOffset.x = x - pos.x;
                        cardHeldOffset.y = y - pos.y;
                    }
                }
            }
        }
    }

    @Override
    public void stopHold(float x, float y) {
        if(x > Window.getCenter().x) {
            boolean cardPlaced = false;

            for(int i = 0; i < pack.getSize(); i++) {
                Vector2f pos = new Vector2f(rightSideOffset.x, rightSideOffset.y - (i + 1) * (cardSize.h() + spacing));
                if(pos.y < cardHeldPos.y) {
                    pack.addCard(cardHeld, i);
                    cardHeld = null;
                    cardPlaced = true;
                    break;
                }
            }

            // place card on the end
            if(!cardPlaced) {
                pack.addCard(cardHeld);
                cardHeld = null;
                cardPlaced = true;
            }
        } else {
            boolean cardPlaced = false;

            for(int i = 0; i < deck.getSize(); i++) {
                Vector2f pos = new Vector2f(leftSideOffset.x, leftSideOffset.y - (i+1) * (cardSize.h() + spacing));
                if(pos.y < cardHeldPos.y) {
                    deck.addCard(cardHeld, i);
                    cardHeld = null;
                    cardPlaced = true;
                    break;
                }
            }

            // place card on the end
            if(!cardPlaced) {
                deck.addCard(cardHeld);
                cardHeld = null;
                cardPlaced = true;
            }
        }
    }

    @Override
    public void doubleTap(float x, float y) {

    }

    @Override
    public void tap(float x, float y) {
        gui.tap(x, y);
    }
}
