package com.mygdx.game.items.cards;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.attributes.ElementType;
import com.mygdx.game.attributes.QualityType;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.items.Item;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public abstract class Card extends Item {

    private String name;
    private String description;
    private Texture icon;

    private CardType type;
    private QualityType quality;
    private ElementType element;

    private Texture element_overlay_color;

    public Card(String name, String folder, String description, CardType type, QualityType quality, ElementType element) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.quality = quality;
        this.element = element;

        if(element != null) {
            element_overlay_color = new Texture("items/cards/element_overlays/" + element.getStr() + "_overlay.png");
        }

        if(quality == null) {
            this.quality = QualityType.STANDARD;
        }

        icon = new Texture("items/cards/" + folder + "/" + name + "_icon.png");
    }

    public abstract void use(SceneBattle scene, BattleLiving user);

    public String getName() {
        String s = "";
        if(quality != QualityType.STANDARD) {
            s += quality.getStr() + " ";
        }
        if(element != null) {
            s += element.getStr() + " ";
        }
        return s + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void drawIcon(RenderSystem rs, Vector2f pos, Vector2i size) {
/*        rs.setShader(rs.iconShader);
        if(getElement() != null) {
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

    public QualityType getQuality() {
        return quality;
    }

    public void setQuality(QualityType quality) {
        this.quality = quality;
    }

    public ElementType getElement() {
        return element;
    }

    public void setElement(ElementType element) {
        this.element = element;
    }
}
