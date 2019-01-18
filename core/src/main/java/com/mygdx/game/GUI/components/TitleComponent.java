package com.mygdx.game.GUI.components;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.CooldownInterface;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.graphics.Window;

public class TitleComponent extends com.mygdx.game.GUI.GUIComponent implements CooldownInterface {

    private BitmapFont font;
    private String text;
    private Vector2f textSize;
    private float alpha;

    private com.mygdx.game.util.Cooldown hold;

    private Vector2f pos;
    public TitleComponent(com.mygdx.game.GUI.GUI gui, String text) {
        super(gui, "TITLE");

        font = FontUtil.getFont(64);

        this.text = text;
        textSize = FontUtil.getTextSize(font, text);
        alpha = 1f;

        hold = new com.mygdx.game.util.Cooldown(this, "HOLD", false, 100);

        pos = new Vector2f(Window.getWidth()/2 - textSize.w()/2, Window.percTop(0.15f));
    }

    @Override
    public void update(Scene scene) {
        hold.update();
    }

    @Override
    public void render(com.mygdx.game.graphics.RenderSystem rs) {
        rs.drawText(font, text, new Vector2f(pos.x,pos.y));
    }

    @Override
    public void trigger(String name) {
        if(hold.ready()) {
            alpha -= 0.01f;
            font.setColor(1, 1, 1, alpha);
            if (alpha <= 0) {
                gui.removeComponent(this);
            }
        }
    }
}
