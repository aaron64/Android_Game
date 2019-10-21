package com.mygdx.game.GUI.components;

import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.GUI.GUIHPanel;
import com.mygdx.game.GUI.GUIText;
import com.mygdx.game.GUI.GUIVPanel;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.hand_select.SceneHandSelect;
import com.mygdx.game.util.Vector2f;

public class SelectCard extends GUIVPanel {

    private GUIText points, title, description;
    private CardIconComponent icon;
    private GUIHPanel iconHolder;

    private Card card;

    private SceneHandSelect scene;

    private boolean selected = false;

    public SelectCard(GUI gui, String name, GUIComponent parent, Vector2f size, Card card, SceneHandSelect scene) {
        super(gui, name, parent, size);

        setMargin(20, 0);

        this.card = card;

        this.scene = scene;

        points = new GUIText(gui, "POINTS", this, new Vector2f(0.3f, 0.1f), 52, card.getPointsCost() + "");
        points.setAnchor(GUIText.TextAnchor.TOP);

        title = new GUIText(gui, "TITLE", this, new Vector2f(1, 0), 52, card.getName());
        //gui.setChildPos();
        title.autofit();

        iconHolder = new GUIHPanel(gui, "ICON_HOLDER", this, new Vector2f(1, 0.3f));
        iconHolder.setVerticalAnchor(VerticalAnchor.CENTER);
        iconHolder.setHorizontalAnchor(HorizontalAnchor.CENTER);
        icon = new CardIconComponent(gui, "ICON", iconHolder, new Vector2f(0, 1), card);

        description = new GUIText(gui, "DESCRIPTION", this, new Vector2f(1, 0.4f), 24, card.getDescription());
    }

    @Override
    public void update(Scene scene) {
        super.update(scene);
    }

    @Override
    public void render(RenderSystem rs) {
        renderBackground(rs, selected);

        super.render(rs);
    }

    @Override
    public void tap(float x, float y) {
        selected = !selected;
        if(selected)
            scene.selectCard(card);
        else
            scene.unselectCard(card);
    }
}
