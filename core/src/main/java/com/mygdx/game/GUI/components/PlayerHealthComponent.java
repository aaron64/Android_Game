package com.mygdx.game.GUI.components;

import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.GUI.GUIHPanel;
import com.mygdx.game.GUI.GUIImage;
import com.mygdx.game.GUI.GUIText;
import com.mygdx.game.entities.main_area.Player;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Vec2f;

public class PlayerHealthComponent extends GUIHPanel {

    private Player player;
    private GUIImage healthImage;
    private GUIText healthText;
    private GUIHPanel rightPanel;

    private int health, healthTarget;

    public PlayerHealthComponent(GUI gui, String name, GUIComponent parent, Vec2f size, Player player) {
        super(gui, name, parent, size);
        this.player = player;

        this.health = player.getHealth();
        this.healthTarget = player.getHealth();

        healthImage = new GUIImage(gui, "IMAGE_HEALTH", this, new Vec2f(0, 1), "heart");

        float rightPanelWidth = (((float)getSize().x - healthImage.getSize().x) / getSize().x);
        rightPanel = new GUIHPanel(gui, "RIGHT_PANEL", this, new Vec2f(rightPanelWidth, 1));
        rightPanel.setHorizontalAnchor(HorizontalAnchor.RIGHT);

        healthText = new GUIText(gui, "TEXT_HEALTH", rightPanel, new Vec2f(1, 1), 72, health + "");
        healthText.rightMargin(8);
        healthText.setTextAlign(RenderSystem.TextAlign.RIGHT);
    }

    @Override
    public void update(Scene scene) {
        healthTarget = player.getHealth();
        if(health < healthTarget)
            health++;
        if(health > healthTarget)
            health--;
        healthText.setText(health + "");
        super.update(scene);
    }

    @Override
    public void render(RenderSystem rs) {
        renderBackground(rs);
        super.render(rs);
    }
}
