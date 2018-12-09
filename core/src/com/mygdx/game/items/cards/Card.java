package com.mygdx.game.items.cards;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.items.Item;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;

public abstract class Card extends Item {

    private String name;
    private String description;
    private Texture icon;
    private CardType type;

    public Card(String name, String folder, CardType type) {
        this.type = type;
        icon = new Texture("items/cards/" + folder + "/" + name + "_icon.png");
    }

    public abstract void use(SceneBattle scene, BattleLiving user);

    public String getName() {
        return name;
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
}
