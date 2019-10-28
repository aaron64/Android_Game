package com.mygdx.game.GUI.components;

import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.GUI.GUIHPanel;
import com.mygdx.game.GUI.GUIImage;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.util.Vector2f;

public class CardIconComponent extends GUIImage {

    private Card card;
    private GUIHPanel iconHolder;

    public CardIconComponent(GUI gui, String name, GUIComponent parent, Vector2f size, Card card) {
        super(gui, name, parent, size, card.getIcon());
        this.card = card;
    }

    @Override
    public void render(RenderSystem rs) {
        renderBackground(rs);
        card.drawIcon(rs, pos, getSize(), getAlpha());
    }
}
