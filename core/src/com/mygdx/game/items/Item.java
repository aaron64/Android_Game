package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.attributes.QualityType;

public abstract class Item {

    protected String name;
    protected QualityType quality;
    protected String description;
    protected Texture icon;

    public Item(String name, String folder, String description, QualityType quality) {
        this.name = name;
        this.description = description;

        this.quality = quality;
        if(quality == null) {
            this.quality = QualityType.STANDARD;
        }

        icon = new Texture("items/cards/" + folder + "/" + name + "_icon.png");
    }

    public String getName() {
        String s = "";
        if(quality != QualityType.STANDARD) {
            s += quality.getStr() + " ";
        }

        return s + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QualityType getQuality() {
        return quality;
    }

    public void setQuality(QualityType quality) {
        this.quality = quality;
    }
}
