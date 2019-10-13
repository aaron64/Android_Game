package com.mygdx.game.GUI.components;

import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.GUI.GUIHPanel;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.hand_select.SceneHandSelect;
import com.mygdx.game.util.Vector2f;

import java.util.ArrayList;

public class SelectHand extends GUIHPanel {

    private ArrayList<Card> cards;

    public SelectHand(GUI gui, String name, GUIComponent parent, Vector2f size, ArrayList<Card> cards, SceneHandSelect scene) {
        super(gui, name, parent, size);
        this.cards = cards;

        setHorizontalAnchor(HorizontalAnchor.CENTER);

        for(int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            Vector2f cardSize = new Vector2f(getSize().y * 0.7f, 1);
            new SelectCard(gui, "CARD" + i, this, new Vector2f(0.3f, 1), card, scene);
        }
    }

    @Override
    public void update(Scene scene) {
        super.update(scene);
    }

    @Override
    public void render(RenderSystem rs) {
        super.render(rs);
    }
}
