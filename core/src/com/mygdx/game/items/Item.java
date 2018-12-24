package com.mygdx.game.items;

import com.mygdx.game.attributes.QualityType;

public abstract class Item {

    protected String name;
    protected QualityType quality;

    public Item(String name, QualityType quality) {
        this.name = name;

        this.quality = quality;
        if(quality == null) {
            this.quality = QualityType.STANDARD;
        }
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
