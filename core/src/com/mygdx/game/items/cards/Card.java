package com.mygdx.game.items.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    private static Texture cardBackground = new Texture("misc/icon_holder_battle.png");

    private CardType type;
    private Element element;

    private BitmapFont nameFont, descriptionFont;
    private Vector2f nameSize, descriptionSize;

    private Texture element_overlay_color;

    public Card(String name, String folder, String description, CardType type, Quality quality, Element element) {
        super(name, "cards/" + folder, description, quality);
        this.description = description;
        this.type = type;
        this.element = element;

        if(element != null) {
            element_overlay_color = new Texture("items/cards/element_overlays/" + element.getStr() + "_overlay.png");
        }

        nameFont = FontUtil.getFont(48);
        descriptionFont = FontUtil.getFont(20);

        nameSize = FontUtil.getTextSize(nameFont, name);
        descriptionSize = FontUtil.getTextSize(descriptionFont, description);
    }

    public abstract void use(SceneBattle scene, BattleLiving user);

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void drawIcon(RenderSystem rs, Vector2f pos, Vector2i size) {
        rs.iconShader.setUniformi("u_texture", 0);
        rs.setShader(rs.iconShader);
        /*if(getElement() != null) {
            rs.iconShader.begin();
            getIcon().bind(1);
            rs.iconShader.setUniformi("u_texture", 1); //passing first texture!!!

            element_overlay_color.bind(0);
            rs.iconShader.setUniformi("u_texture2", 0);
            //rs.iconShader.setUniformMatrix("u_projTrans", rs.getCamera().combined);
        }*/
        rs.draw(icon, pos, size);
        rs.setShader(null);
    }

    public void drawDeckScene(RenderSystem rs, Vector2f pos, Vector2i size) {
        Vector2i iconSize = new Vector2i(size.y, size.y);

        Vector2f namePos = new Vector2f(pos);
        namePos.x += iconSize.x + 6;
        namePos.y += size.h() - nameSize.h();

        Vector2f descriptionPos = new Vector2f(pos);
        descriptionPos.x += iconSize.x * 2;

        rs.draw(cardBackground, pos, size);
        rs.draw(getIcon(), pos, iconSize);
        rs.drawText(nameFont, getName(), namePos);
        rs.drawText(descriptionFont, getDescription(), descriptionPos);
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
}
