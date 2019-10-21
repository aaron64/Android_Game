package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.graphics.Image;

public abstract class Item {

    protected String name;
    protected Quality quality;
    protected String description;
    protected Texture icon;

    public Item(String name, String folder, String description, Quality quality) {
        this.name = name;
        this.description = description;

        this.quality = quality;
        if(quality == null) {
            this.quality = Quality.STANDARD;
        }

        icon = Image.getImage("items/" + folder + "/" + name + "_icon");
    }

    public Item(String name, String description, Quality quality) {
        this.name = name;
        this.description = description;

        this.quality = quality;
        if(quality == null) {
            this.quality = Quality.STANDARD;
        }
    }

    public String getName() {
        String s = "";
        if(quality != Quality.STANDARD) {
            s += quality.getStr() + " ";
        }

        return s + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }
}
