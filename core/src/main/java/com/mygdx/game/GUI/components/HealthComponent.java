package com.mygdx.game.GUI.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.entities.main_area.Player;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.graphics.Window;

public class HealthComponent extends com.mygdx.game.GUI.GUIComponent {

    private BitmapFont healthText;
    private Vector2f textOffset;

    private Texture heart;
    private Vector2f offset;
    private Vector2i size;

    private com.mygdx.game.entities.main_area.Player player;
    private int renderHealth;

    public HealthComponent(com.mygdx.game.GUI.GUI gui, Player player) {
        super(gui, "HEALTH");
        this.player = player;
        renderHealth = player.getHealth();

        heart = new Texture("gui/heart.png");
        offset = new Vector2f(Window.percLeft(0.03f), Window.percTop(0.1f));
        size = new Vector2i(Window.percHeight(0.07f), Window.percHeight(0.07f));

        healthText = FontUtil.getFont(52);
        textOffset = new Vector2f(offset.x + size.w() + size.w()/5, Window.percTop(0.05f));
    }

    @Override
    public void update(com.mygdx.game.scenes.Scene scene) {
        if(renderHealth > player.getHealth()) {
            renderHealth--;
        } else if(renderHealth < player.getHealth()) {
            renderHealth++;
        }
    }

    @Override
    public void render(com.mygdx.game.graphics.RenderSystem rs) {
        rs.draw(heart, offset, size);
        rs.drawText(healthText, "" + renderHealth, textOffset);
    }
}
