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
    private boolean autofit;

    private Vec2f drawPos;

    private RenderSystem.TextAlign align = LEFT;
    private TextAnchor anchor = TextAnchor.BOTTOM;

    public enum TextAnchor {
        TOP,
        BOTTOM
    }

    public GUIText(GUI gui, String name, GUIComponent parent, Vec2f size, int fontSize, String text, boolean autofit) {
        super(gui, name, parent, size);
        this.text = text;

        this.autofit = autofit;
        setFontSize(fontSize);
        pos.y -= getSize().y;

        drawPos = new Vec2f();
    }

    public GUIText(GUI gui, String name, GUIComponent parent, Vec2f size, int fontSize, String text) {
        this(gui, name, parent, size, fontSize, text, false);
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
                rs.drawText(font, text, drawPos);
                break;
            case RIGHT:
                Vec2f rPos = new Vec2f(drawPos);
                rPos.x += getSize().x;
                rs.drawTextRight(font, text, rPos);
                break;
            case CENTER:
                rs.drawTextCentered(font, text, drawPos);
                break;
        }
        rs.resetColor();
        super.render(rs);
    }

    private void autofit() {
        Vec2f charSize = FontUtil.getCharSize(font);
        float totalWidth = charSize.w() * text.length();

        if(totalWidth > getSize().w()) {
            float ratio = totalWidth / getSize().w();
            fontSize /= ratio;
            font = FontUtil.getFont(fontSize);
        }

        getSize().y = (int) charSize.y;
    }

    private void wrap() {
        Vec2f charSize = FontUtil.getCharSize(font);
        int lineWidth = (int)(getSize().w() / charSize.w());

        text = getWrappedText(text, lineWidth);

        getSize().y = new Vec2i(FontUtil.getTextSize(font, text)).y;
    }

    private String getWrappedText(String text, int lineSize) {
        if(text.length() < lineSize)
            return text;
        int split = text.substring(0, lineSize).lastIndexOf(' ');
        if(split == -1)
            return text;
        return text.substring(0, split) + "\n" + getWrappedText(text.substring(split+1), lineSize);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFontSize(int size) {
        this.fontSize = size;
        font = FontUtil.getFont(fontSize);

        if(autofit) {
            autofit();
        } else {
            wrap();
        }
    }

    public Vec2f getTextSize() {
        return FontUtil.getTextSize(font, text);
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

    public void setPos(Vec2f pos) {
        this.pos = pos;
        this.drawPos = new Vec2f(pos.x, pos.y + getSize().h());
    }
}
