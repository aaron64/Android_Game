package com.mygdx.game.GUI.components;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.GUI.GUIHPanel;
import com.mygdx.game.GUI.GUIImage;
import com.mygdx.game.GUI.GUIText;
import com.mygdx.game.GUI.GUIVPanel;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.hand_select.SceneHandSelect;
import com.mygdx.game.util.Vec2f;

public class SelectCardComponent extends GUIVPanel {

    private GUIText points, title, description, amount;
    private CardIconComponent icon;
    private GUIHPanel topPanel;
    private GUIHPanel iconHolder;
    private GUIImage qualityIcon;
    private GUIImage elementIcon;

    private Card card;

    private SceneHandSelect scene;

    private boolean selected = false;

    public SelectCardComponent(GUI gui, String name, GUIComponent parent, Vec2f size, Card card, SceneHandSelect scene) {
        super(gui, name, parent, size);

        setMargin(20, 0);

        this.card = card;

        this.scene = scene;

        topPanel = new GUIHPanel(gui, "TOP_PANEL", this, new Vec2f(1, 0.1f));
        topPanel.setMargin(8, 16);

        points = new GUIText(gui, "POINTS", topPanel, new Vec2f(0f, 1f), 72, card.getPointsCost() + "");
        points.setAnchor(GUIText.TextAnchor.TOP);

        if(card.getQuality() != Quality.STANDARD)
            qualityIcon = new GUIImage(gui, "QUALITY_ICON", topPanel, new Vec2f(0, 1f), card.getQuality().getIcon());

        if(card.getElement() != null)
            elementIcon = new GUIImage(gui, "ELEMENT_ICON", topPanel, new Vec2f(0, 1f), card.getElement().getIcon());

        title = new GUIText(gui, "TITLE", this, new Vec2f(1, 0), 72, card.getName(), true);
        title.setMargin(8, 8);

        iconHolder = new GUIHPanel(gui, "ICON_HOLDER", this, new Vec2f(1, 0.3f));
        iconHolder.setVerticalAnchor(VerticalAnchor.CENTER);
        iconHolder.setHorizontalAnchor(HorizontalAnchor.CENTER);
        iconHolder.setMargin(8, 8);
        icon = new CardIconComponent(gui, "ICON", iconHolder, new Vec2f(0, 1), card);

        if(card.getAmount() != null) {
            amount = new GUIText(gui, "AMOUNT", this, new Vec2f(0, 0.1f), 72, card.getAmount());
            amount.setMargin(8, 8);
        }

        description = new GUIText(gui, "DESCRIPTION", this, new Vec2f(1, 0.4f), 48, card.getDescription());
        description.setMargin(8, 8);
    }

    @Override
    public void update(Scene scene) {
        super.update(scene);
    }

    @Override
    public void render(RenderSystem rs) {
        renderBackground(rs, selected);
        Gdx.app.log("CARD:", card.getQuality().name());
        Gdx.app.log("CARD:", card.getName());
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
