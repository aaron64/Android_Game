package com.mygdx.game.GUI.components;

import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.GUI.GUIVPanel;
import com.mygdx.game.entities.battle.BattlePlayer;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Vec2f;
import com.mygdx.game.util.Vec2i;

import java.util.ArrayList;

public class BattleCurrentCardsComponent extends GUIVPanel {

    private BattlePlayer player;
    private ArrayList<CardIconComponent> cardsIcons;

    public BattleCurrentCardsComponent(GUI gui, GUIComponent parent, Vec2f size, BattlePlayer player) {
        super(gui, "BATTLE_CARDS", parent, size);

        this.player = player;
        cardsIcons = new ArrayList<CardIconComponent>();
    }

    public void setCards() {
        for(int i = 0; i < player.getCards().getSize(); i++) {
            Card card = player.getCards().getCard(i);
            CardIconComponent cardIcon = new CardIconComponent(gui, "CARD_ICON", null, new Vec2f(0, 1), card);
            cardIcon.setSize(new Vec2i(getSize().w()/2, getSize().w()/2));
            cardsIcons.add(cardIcon);
        }
    }

    public void useCard() {
        cardsIcons.remove(cardsIcons.size() - 1);
    }

    public void clear() {
        cardsIcons.clear();
    }

    @Override
    public void update(Scene scene) {
        super.update(scene);
    }

    @Override
    public void render(RenderSystem rs) {
        for(int i = 0; i < cardsIcons.size(); i++) {
            CardIconComponent cardIcon = cardsIcons.get(i);
            cardIcon.setPos(new Vec2f(pos.x, pos.y + size.h()/2 - i * 16));
            cardIcon.render(rs);
        }
        super.render(rs);
    }
}
