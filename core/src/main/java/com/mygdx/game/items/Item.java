package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.attributes.Quality;

public abstract class Item {

    protected String name;
    protected com.mygdx.game.attributes.Quality quality;
    protected String description;
    protected Texture icon;

    public Item(String name, String folder, String description, Quality quality) {
        this.name = name;
        this.description = description;

        this.quality = quality;
        if(quality == null) {
            this.quality = com.mygdx.game.attributes.Quality.STANDARD;
        }

        icon = new Texture("items/" + folder + "/" + name + "_icon.png");
    }

    public String getName() {
        String s = "";
        if(quality != com.mygdx.game.attributes.Quality.STANDARD) {
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

    public void setQuality(com.mygdx.game.attributes.Quality quality) {
        this.quality = quality;
    }
}
