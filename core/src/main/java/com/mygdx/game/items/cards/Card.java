package com.mygdx.game.items.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.items.Item;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.util.Vec2f;
import com.mygdx.game.util.Vec2i;

public abstract class Card extends Item {

    private CardType type;
    private Element element;

    private int pointsCost;

    protected BitmapFont nameFont, descriptionFont, pointsFont, amountFont;
    protected Vec2f nameSize, descriptionSize, pointsSize, amountSize;

    protected Texture overlay_texture;

    private int lockInitial, lockFinal;

    public Card(String name, int lockInitial, int lockFinal, String folder, String description, CardType type, int pointsCost, Quality quality, Element element) {
        super(name, "cards/" + folder, description, quality);
        this.description = description;
        this.pointsCost = pointsCost;
        this.type = type;
        this.element = element;

        if(element != null) {
            overlay_texture = Image.getImage("items/cards/" + folder + "/" + name + "_overlay");
        }

        nameFont = FontUtil.getFont(40);
        descriptionFont = FontUtil.getFont(24);
        pointsFont = FontUtil.getFont(40);
        amountFont = FontUtil.getFont(40);

        nameSize = FontUtil.getTextSize(nameFont, getName());
        descriptionSize = FontUtil.getTextSize(descriptionFont, description);
        pointsSize = FontUtil.getTextSize(pointsFont, "" + getPointsCost());

        this.lockInitial = lockInitial;
        this.lockFinal = lockFinal;
    }

    public Card(String name, int lockInitial, int lockFinal, String description, CardType type, int pointsCost, Quality quality, Element element) {
        super(name, description, quality);
        this.description = description;
        this.pointsCost = pointsCost;
        this.type = type;
        this.element = element;

        nameFont = FontUtil.getFont(40);
        descriptionFont = FontUtil.getFont(24);
        pointsFont = FontUtil.getFont(40);
        amountFont = FontUtil.getFont(40);

        nameSize = FontUtil.getTextSize(nameFont, getName());
        descriptionSize = FontUtil.getTextSize(descriptionFont, description);
        pointsSize = FontUtil.getTextSize(pointsFont, "" + getPointsCost());

        this.lockInitial = lockInitial;
        this.lockFinal = lockFinal;
    }

    public abstract void use(SceneBattle scene, BattleLiving user);

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void drawIcon(RenderSystem rs, Vec2f pos, Vec2i size, float alpha) {
        rs.setColor(1f, 1f, 1f, alpha);
        Element.drawTextureWithOverlay(rs, icon, overlay_texture, pos, size, getElement());
        rs.resetColor();
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

    public abstract String getAmount();

    public int getInitialLock() {
        return lockInitial;
    }

    public int getFinalLock() {
        return lockFinal;
    }
}
