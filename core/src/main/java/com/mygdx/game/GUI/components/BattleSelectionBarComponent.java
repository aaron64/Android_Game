package com.mygdx.game.GUI.components;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.graphics.Window;

public class BattleSelectionBarComponent extends GUIComponent {

    private int time, duration;
    private Texture barTexture, barTextureReady, renderTexture;
    private Vector2f barPos;
    private Vector2i barSize;
    private int maxWidth;

    public BattleSelectionBarComponent(GUI gui, int duration) {
        super(gui, "SELECTION_BAR");

        barTexture = new Texture("gui/selection_bar.png");
        barTextureReady = new Texture("gui/selection_bar_ready.png");
        renderTexture = barTexture;

        this.duration = duration;
        time = 0;

        maxWidth = Window.getWidth();

        barPos = new Vector2f(0, Window.percTop(0.05f));
        barSize = new Vector2i(0, Window.percHeight(0.05f));
    }

    @Override
    public void update(Scene scene) {
        time++;
        barSize.x = (int)((float)time/duration * maxWidth);

        if(time >= duration) {
            //Game.getCurrentScene().getGUI().getComponent("BATTLE_GO_TO_SELECTION_BUTTON").on();
            renderTexture = barTextureReady;
        }
    }

    @Override
    public void render(RenderSystem rs) {
        rs.draw(renderTexture, barPos, barSize);
    }

    public void resetBar() {
        time = 0;
        renderTexture = barTexture;
    }

    public boolean ready() {
        return time >= duration;
    }
}
