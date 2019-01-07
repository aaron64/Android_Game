package com.mygdx.game.items.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.items.Item;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.util.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public abstract class Card extends Item {

    public static Texture cardBackground = new Texture("misc/icon_holder_battle.png");

    private CardType type;
    private Element element;

    private int pointsCost;

    private BitmapFont nameFont, descriptionFont, pointsFont, damageFont;
    private Vector2f nameSize, descriptionSize, pointsSize, damageSize;

    private Texture element_overlay_texture;

    public Card(String name, String folder, String description, CardType type, int pointsCost, Quality quality, Element element) {
        super(name, "cards/" + folder, description, quality);
        this.description = description;
        this.pointsCost = pointsCost;
        this.type = type;
        this.element = element;

        if(element != null) {
            element_overlay_texture = new Texture("items/cards/" + folder + "/" + name + "_overlay.png");
        }

        nameFont = FontUtil.getFont(40);
        descriptionFont = FontUtil.getFont(24);
        pointsFont = FontUtil.getFont(40);
        damageFont = FontUtil.getFont(40);

        nameSize = FontUtil.getTextSize(nameFont, name);
        descriptionSize = FontUtil.getTextSize(descriptionFont, description);
        pointsSize = FontUtil.getTextSize(pointsFont, ""+getPointsCost());
        damageSize = FontUtil.getTextSize(damageFont, "" + getAmount());
    }

    public abstract void use(SceneBattle scene, BattleLiving user);

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void drawIcon(RenderSystem rs, Vector2f pos, Vector2i size) {
        rs.draw(icon, pos, size);

        if(getElement() != null) {
            rs.setColor(getElement().getColor());
            rs.draw(element_overlay_texture, pos, size);
        }
        rs.resetColor();
    }

    public void drawDeckScene(RenderSystem rs, Vector2f pos, Vector2i size) {
        Vector2i iconSize = new Vector2i(size.y, size.y);

        Vector2f namePos = new Vector2f(pos);
        namePos.x += iconSize.x + 12;
        namePos.y += size.h() - nameSize.h();

        Vector2f descriptionPos = new Vector2f(pos);
        descriptionPos.x += iconSize.x + 12;
        descriptionPos.y += size.h() - nameSize.h() * 2 - 12;

        Vector2f pointsPos = new Vector2f(pos);

        Vector2f damagePos = new Vector2f(pos);

        rs.draw(cardBackground, pos, size);
        drawIcon(rs, pos, iconSize);
        rs.drawText(nameFont, getName(), namePos);
        rs.drawText(descriptionFont, getDescription(), descriptionPos);
    }

    public void drawHandScene(RenderSystem rs, Vector2f pos, Vector2i size) {
        drawIcon(rs, pos, size);
    }

    @Override
    public String getName() {
        String s = super.getName();
        if(element != null) {
            s = element.getStr() + " " + s;
        }
        return s;
    }

    public Texture getIcon() {
        return icon;
    }

    public void setIcon(Texture icon) {
        this.icon = icon;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getPointsCost() {
        return pointsCost;
    }

    public void setPointsCost(int pointsCost) {
        this.pointsCost = pointsCost;
    }

    protected abstract int getAmount();
}
