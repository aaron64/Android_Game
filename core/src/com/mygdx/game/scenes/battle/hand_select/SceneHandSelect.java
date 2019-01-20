package com.mygdx.game.scenes.battle.hand_select;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GUI.components.HandSelectionGoButton;
import com.mygdx.game.Game;
import com.mygdx.game.PlayerVars;
import com.mygdx.game.entities.battle.BattlePlayer;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.items.cards.Deck;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.util.Window;

import java.util.ArrayList;

import static com.mygdx.game.items.cards.Card.cardBackground;

public class SceneHandSelect extends Scene implements GestureHandler {

    private BattlePlayer player;

    private Vector2f choosePos, handPos;
    private Deck deck;

    private int leftOffset;

    private Vector2i iconSize;

    private ArrayList<Card> deckSelection, handSelection;
    private int maxCards;

    public SceneHandSelect(BattlePlayer player) {
        super();
        gestureHandler = new GestureUtil(this);

        this.player = player;

        maxCards = 5;

        deck = new Deck(PlayerVars.deck);
        deck.shuffle();

        deckSelection = new ArrayList<Card>(maxCards);
        handSelection = new ArrayList<Card>(maxCards);

        leftOffset = Window.percWidth(0.1f);

        iconSize = new Vector2i(Window.percWidth(0.1f), Window.percWidth(0.1f));

        Vector2f goButtonPos = new Vector2f(Window.percRight(0.1f) - iconSize.x, Window.getCenter().y - iconSize.y - Window.percHeight(0.05f));
        gui.addComponent(new HandSelectionGoButton(gui, goButtonPos, iconSize, this));

<<<<<<< HEAD
        newHand();
=======
        //newHand();
>>>>>>> 87abaec8852798f4e074a8782d6747d134feba92
    }

    @Override
    public void update() {
        super.update();
        gui.update(this);
    }

    public void newHand() {
<<<<<<< HEAD
        deckSelection.clear();
        handSelection.clear();

        for(int i = 0; i < maxCards; i++) {
            deckSelection.add(deck.pop());
=======
        handSelection.clear();

        for(int i = 0; i < maxCards; i++) {
            Card card = deck.pop();
            if(card != null)
                deckSelection.add(deck.pop());
>>>>>>> 87abaec8852798f4e074a8782d6747d134feba92
            deck.refresh();
        }
    }

    @Override
    public void render() {
        Game.getLastScene().renderInBackground();

        rs.resetColor();
        rs.restart();

        int spacing = Window.percWidth(0.02f);

        for(int i = 0; i < 5; i++) {
            Vector2f deckPos = new Vector2f(leftOffset + (iconSize.x + spacing) * i, Window.getCenter().y + Window.percHeight(0.05f));

            rs.draw(cardBackground, deckPos, iconSize);
            if(i < deckSelection.size()) {
                Card card = deckSelection.get(i);
                if(card != null)
                    card.drawHandScene(rs, deckPos, iconSize);
            }
        }

        for(int i = 0; i < 5; i++) {
            Vector2f deckPos = new Vector2f(leftOffset + (iconSize.x + spacing) * i, Window.getCenter().y - iconSize.y - Window.percHeight(0.05f));

            rs.draw(cardBackground, deckPos, iconSize);

            if(i < handSelection.size()) {
                Card card = handSelection.get(i);
                if(card != null)
                    card.drawHandScene(rs, deckPos, iconSize);
            }
        }

        gui.render(rs);

        rs.end();
    }

    @Override
    public void onPushed() {
        newHand();
    }

    @Override
    public void onPopped() {
        newHand();
    }

    @Override
    public void onExit() {
        player.setHand(new Deck(handSelection));
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
    public void stopHold(float x, float y) {

    }

    @Override
    public void doubleTap(float x, float y) {

    }

    @Override
    public void tap(float x, float y) {
        int spacing = Window.percWidth(0.02f);

        for(int i = 0; i < 5; i++) {
            Vector2f deckPos = new Vector2f(leftOffset + (iconSize.x + spacing) * i, Window.getCenter().y + Window.percHeight(0.05f));

            if(i < deckSelection.size()) {
                Card card = deckSelection.get(i);
                if(new Rectangle(deckPos.x, deckPos.y, iconSize.w(), iconSize.h()).contains(x, y)) {
                    handSelection.add(card);
                    deckSelection.remove(i);
                }
            }
        }

        for(int i = 0; i < 5; i++) {
            Vector2f deckPos = new Vector2f(leftOffset + (iconSize.x + spacing) * i, Window.getCenter().y - iconSize.y - Window.percHeight(0.05f));

            if(i < deckSelection.size()) {
                Card card = deckSelection.get(i);
                if(new Rectangle(deckPos.x, deckPos.y, iconSize.w(), iconSize.h()).contains(x, y)) {
                    handSelection.remove(i);
                    deckSelection.add(card);
                }
            }
        }

        gui.tap(x, y);
    }
}
