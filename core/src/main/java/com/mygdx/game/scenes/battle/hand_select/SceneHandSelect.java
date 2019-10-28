package com.mygdx.game.scenes.battle.hand_select;

import com.mygdx.game.GUI.GUIButton;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.GUI.GUIHPanel;
import com.mygdx.game.GUI.components.SelectHandComponent;
import com.mygdx.game.Game;
import com.mygdx.game.PlayerVars;
import com.mygdx.game.animation.GUIFadeInAnimation;
import com.mygdx.game.animation.GUIMoveAnimation;
import com.mygdx.game.entities.battle.BattlePlayer;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.items.cards.Deck;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.util.Vector2f;

import java.util.ArrayList;


public class SceneHandSelect extends Scene implements GestureHandler {

    private BattlePlayer player;

    private Deck deck;
    private ArrayList<Card> handSelection;

    private int maxCards;

    private SelectHandComponent selectHand;
    private GUIHPanel selectHandContainer;
    private GUIHPanel rightPanel;
    private GUIButton goButton;

    public SceneHandSelect(BattlePlayer player) {
        super();
        gestureHandler = new GestureUtil(this);

        this.player = player;

        maxCards = 2;

        deck = new Deck(PlayerVars.deck);
        deck.shuffle();

        handSelection = new ArrayList<Card>();

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(deck.pop());
        cards.add(deck.pop());
        cards.add(deck.pop());

        new GUIHPanel(gui, "LEFT_PANEL", gui.getNode(), new Vector2f(0.15f, 1));

        selectHandContainer = new GUIHPanel(gui, "HAND_CONTAINER", gui.getNode(), new Vector2f(0.7f, 1));
        selectHandContainer.setVerticalAnchor(GUIHPanel.VerticalAnchor.CENTER);
        selectHand = new SelectHandComponent(gui, "CARD_HAND", selectHandContainer, new Vector2f(1, 0.6f), cards, this);

        rightPanel = new GUIHPanel(gui, "RIGHT_PANEL", gui.getNode(), new Vector2f(0.15f, 1));
        rightPanel.setHorizontalAnchor(GUIComponent.HorizontalAnchor.CENTER);
        rightPanel.setVerticalAnchor(GUIComponent.VerticalAnchor.BOTTOM);
        goButton = new GUIButton(gui, "GO_BUTTON", rightPanel, new Vector2f(0.75f, 0), Image.getImage("gui/button/button_icon_go"), this);
        goButton.bottomMargin(16);

        addAnimation(new GUIMoveAnimation(new Vector2f(0, 50), 10, selectHandContainer));
        addAnimation(new GUIFadeInAnimation(10, selectHandContainer));
    }

    @Override
    public void update() {
        super.update();
        gui.update(this);
    }

    @Override
    public void render() {
        Game.getLastScene().renderInBackground();

        rs.resetColor();
        rs.restart();

        gui.render(rs);

        rs.end();
    }

    public void selectCard(Card c) {
        handSelection.add(c);
    }

    public void unselectCard(Card c) {
        handSelection.remove(c);
    }

    @Override
    public void buttonPress(String button) {
        if(button.equals("GO_BUTTON")) {
            for(Card card : handSelection) {
                deck.remove(card);
            }
            Game.endScene();
        }
    }

    public int getPointsUsed() {
        return 0;
    }

    @Override
    public void onPushed() {
    }

    @Override
    public void onPopped() {
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
        gui.hold(x, y);
    }

    @Override
    public void stopHold(float x, float y) {
        gui.stopHold(x, y);
    }

    @Override
    public void doubleTap(float x, float y) {

    }

    @Override
    public void tap(float x, float y) {
        gui.tap(x, y);
    }
}
