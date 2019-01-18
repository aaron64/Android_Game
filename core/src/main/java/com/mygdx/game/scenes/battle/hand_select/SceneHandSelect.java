package com.mygdx.game.scenes.battle.hand_select;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GUI.components.HandSelectionPoints;
import com.mygdx.game.graphics.ContentBackground;
import com.mygdx.game.GUI.components.HandSelectionGoButton;
import com.mygdx.game.Game;
import com.mygdx.game.PlayerVars;
import com.mygdx.game.entities.battle.BattlePlayer;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.items.cards.Deck;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.graphics.Window;

import java.util.ArrayList;

import javax.swing.text.AbstractDocument;


public class SceneHandSelect extends Scene implements com.mygdx.game.util.GestureHandler {

    private com.mygdx.game.entities.battle.BattlePlayer player;

    private Deck deck;

    private int leftOffset;

    private Vector2i smallIconSize;

    private ArrayList<com.mygdx.game.items.cards.Card> deckSelection, handSelection;
    private int maxCards;

    private com.mygdx.game.items.cards.Card selected;
    private Vector2f selectedPos;
    private Vector2i selectedSize;
    private Vector2f selectedImagePos;
    private Vector2i selectedImageSize;

    private Vector2f selectedTitlePos;
    private BitmapFont selectedTitleFont;

    private Vector2f selectedAmountPos;
    private BitmapFont selectedAmountFont;

    private Vector2f selectedCostPos;
    private BitmapFont selectedCostFont;

    private com.mygdx.game.GUI.components.HandSelectionPoints pointsComponent;

    public SceneHandSelect(com.mygdx.game.entities.battle.BattlePlayer player) {
        super();
        gestureHandler = new com.mygdx.game.util.GestureUtil(this);

        this.player = player;

        maxCards = 5;

        deck = new Deck(com.mygdx.game.PlayerVars.deck);
        deck.shuffle();

        deckSelection = new ArrayList<com.mygdx.game.items.cards.Card>(maxCards);
        handSelection = new ArrayList<com.mygdx.game.items.cards.Card>(maxCards);

        leftOffset = Window.percLeft(0.05f);

        smallIconSize = new Vector2i(Window.percWidth(0.05f), Window.percWidth(0.05f));

        Vector2f goButtonPos = new Vector2f(Window.percRight(0.1f) - smallIconSize.w(), Window.getCenter().y - smallIconSize.h() - Window.percHeight(0.05f));
        gui.addComponent(new com.mygdx.game.GUI.components.HandSelectionGoButton(gui, goButtonPos, smallIconSize, this));

        Vector2f pointsComponentPos = new Vector2f(Window.percRight(0.1f) - smallIconSize.w(), Window.getCenter().y + Window.percHeight(0.05f));
        pointsComponent = new com.mygdx.game.GUI.components.HandSelectionPoints(gui, pointsComponentPos, smallIconSize, this);
        gui.addComponent(pointsComponent);

        selected = null;
        selectedPos = new Vector2f(Window.percLeft(0.05f), Window.percBottom(0.20f));
        selectedSize = new Vector2i(Window.percWidth(0.25f), Window.percHeight(0.65f));

        selectedImageSize = new Vector2i(Window.percWidth(0.21f), Window.percWidth(0.21f));
        selectedImagePos = new Vector2f(Window.percLeft(0.07f), selectedPos.y + selectedSize.h() - selectedImageSize.h() - Window.percHeight(0.02f));

        selectedTitleFont = FontUtil.getFont(36);
        selectedTitlePos = new Vector2f(selectedPos.x + selectedSize.w()/2, selectedImagePos.y - FontUtil.getTextSize(selectedTitleFont, "TEST").h());

        selectedAmountFont = FontUtil.getFont(32);
        selectedAmountPos = new Vector2f(selectedPos.x, selectedPos.y + FontUtil.getTextSize(selectedAmountFont, "TEST").h());

        selectedCostFont = FontUtil.getFont(32);
        selectedCostPos = new Vector2f(selectedPos.x + selectedSize.w(), selectedPos.y + FontUtil.getTextSize(selectedCostFont, "TEST").h());
    }

    @Override
    public void update() {
        super.update();
        gui.update(this);
    }

    public void newHand() {
        handSelection.clear();

        for(int i = 0; i < maxCards; i++) {
            com.mygdx.game.items.cards.Card card = deck.pop();
            if(card != null)
                deckSelection.add(deck.pop());
            deck.refresh();
        }
    }

    @Override
    public void render() {
        com.mygdx.game.Game.getLastScene().renderInBackground();

        rs.resetColor();
        rs.restart();

        int spacing = Window.percWidth(0.01f);

        for(int i = 0; i < 5; i++) {
            Vector2f deckPos = new Vector2f(leftOffset + (smallIconSize.w() + spacing) * i, Window.percBottom(0.05f));

            ContentBackground.drawBackground(rs, deckPos, smallIconSize);
            if(i < deckSelection.size()) {
                com.mygdx.game.items.cards.Card card = deckSelection.get(i);
                if(card != null)
                    card.drawHandScene(rs, deckPos, smallIconSize);
            }
        }

        for(int i = 0; i < 5; i++) {
            Vector2f deckPos = new Vector2f(leftOffset * 2 + (selectedSize.w() + spacing),  Window.percTop(0.1f) - smallIconSize.h() - i * (smallIconSize.h() + spacing));

            ContentBackground.drawBackground(rs, deckPos, smallIconSize);

            if(i < handSelection.size()) {
                com.mygdx.game.items.cards.Card card = handSelection.get(i);
                if(card != null)
                    card.drawHandScene(rs, deckPos, smallIconSize);
            }
        }

        if(selected != null) {
            ContentBackground.drawBackground(rs, selectedPos, selectedSize);
            selected.drawSelectedHandScene(rs, selectedImagePos, selectedImageSize);

            rs.drawTextCentered(selectedTitleFont, selected.getName(), selectedTitlePos);
            rs.drawText(selectedAmountFont, "" + selected.getAmount(), selectedAmountPos);
            rs.drawTextRight(selectedCostFont, "" + selected.getPointsCost(), selectedCostPos);
        }

        gui.render(rs);

        rs.end();
    }

    public int getPointsUsed() {
        int p = 0;
        for(com.mygdx.game.items.cards.Card c : handSelection) {
            p += c.getPointsCost();
        }

        return p;
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
        int spacing = Window.percWidth(0.01f);

        for(int i = 0; i < 5; i++) {
            Vector2f deckPos = new Vector2f(leftOffset + (smallIconSize.w() + spacing) * i, Window.percBottom(0.05f));

            if(i < deckSelection.size()) {
                com.mygdx.game.items.cards.Card card = deckSelection.get(i);
                if(new Rectangle(deckPos.x, deckPos.y, smallIconSize.w(), smallIconSize.h()).contains(x, y)) {
                    selected = card;
                }
            }
        }

        for(int i = 0; i < 5; i++) {
            Vector2f deckPos = new Vector2f(leftOffset * 2 + (selectedSize.w() + spacing),  Window.percTop(0.1f) - smallIconSize.h() - i * (smallIconSize.h() + spacing));

            if(i < handSelection.size()) {
                com.mygdx.game.items.cards.Card card = handSelection.get(i);
                if(new Rectangle(deckPos.x, deckPos.y, smallIconSize.w(), smallIconSize.h()).contains(x, y)) {
                    selected = card;
                }
            }
        }
    }

    @Override
    public void stopHold(float x, float y) {
        selected = null;
    }

    @Override
    public void doubleTap(float x, float y) {

    }

    @Override
    public void tap(float x, float y) {
        int spacing = Window.percWidth(0.01f);

        for(int i = 0; i < 5; i++) {
            Vector2f deckPos = new Vector2f(leftOffset + (smallIconSize.w() + spacing) * i, Window.percBottom(0.05f));

            if(i < deckSelection.size()) {
                com.mygdx.game.items.cards.Card card = deckSelection.get(i);
                if(new Rectangle(deckPos.x, deckPos.y, smallIconSize.w(), smallIconSize.h()).contains(x, y)) {
                    if(!pointsComponent.spent(card)) {
                        handSelection.add(card);
                        deckSelection.remove(i);
                    }
                }
            }
        }

        for(int i = 0; i < 5; i++) {
            Vector2f deckPos = new Vector2f(leftOffset * 2 + (selectedSize.w() + spacing),  Window.percTop(0.05f) - smallIconSize.h() - i * (smallIconSize.h() + spacing));

            if(i < handSelection.size()) {
                com.mygdx.game.items.cards.Card card = handSelection.get(i);
                if(new Rectangle(deckPos.x, deckPos.y, smallIconSize.w(), smallIconSize.h()).contains(x, y)) {
                    handSelection.remove(i);
                    deckSelection.add(card);
                }
            }
        }

        gui.tap(x, y);
    }
}
