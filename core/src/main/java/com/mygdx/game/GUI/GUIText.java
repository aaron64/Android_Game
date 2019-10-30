package com.mygdx.game.GUI;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.util.Vec2f;
import com.mygdx.game.util.Vec2i;

import static com.mygdx.game.graphics.RenderSystem.TextAlign.LEFT;

public class GUIText extends GUIHPanel {

    private BitmapFont font;
    private String text;
    private int fontSize;

    private RenderSystem.TextAlign align = LEFT;
    private TextAnchor anchor = TextAnchor.BOTTOM;

    public enum TextAnchor {
        TOP,
        BOTTOM
    }

    public GUIText(GUI gui, String name, GUIComponent parent, Vec2f size, int fontSize, String text) {
        super(gui, name, parent, size);
        this.text = text;
        font = FontUtil.getFont(fontSize);
        setFontSize(fontSize);
    }

    public GUIText(GUI gui, String name, GUIComponent parent, Vec2f size, int fontSize) {
        this(gui, name, parent, size, fontSize, "");
    }

    @Override
    public void update(Scene scene) {
        super.update(scene);
    }

    @Override
    public void render(RenderSystem rs) {
        rs.setColor(1f, 1f, 1f, getAlpha());
        font.setColor(1f, 1f, 1f, getAlpha());
        switch(align) {
            case LEFT:
                rs.drawText(font, text, pos);
                break;
            case RIGHT:
                Vec2f rPos = new Vec2f(pos);
                rPos.x += getSize().x;
                rs.drawTextRight(font, text, rPos);
                break;
            case CENTER:
                rs.drawTextCentered(font, text, pos);
                break;
        }
        rs.resetColor();
        super.render(rs);
    }

    public void autofit() {
        float charWidth = FontUtil.getTextSize(font, "A").x;
        float totalWidth = charWidth * text.length();
        if(totalWidth < getSize().x) {
            float ratio = totalWidth / getSize().x;
            fontSize *= ratio;
            font = FontUtil.getFont(fontSize);
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFontSize(int size) {
        this.fontSize = size;
        setSize(new Vec2i(FontUtil.getTextSize(font, text)));
    }

    public void setTextAlign(RenderSystem.TextAlign align) {
        this.align = align;
    }

    public void setAnchor(TextAnchor anchor) {
        this.anchor = anchor;
    }

    public int getFontSize() {
        return fontSize;
    }

    public BitmapFont getFont() {
        return font;
    }
}
