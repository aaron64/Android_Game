package com.mygdx.game.items.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.util.FileUtil;

import java.util.HashMap;

public class CardBuilder {

    private static HashMap<String, JsonValue> cardMap;
    public static void init() {
        cardMap = new HashMap<String, JsonValue>();

        JsonValue cardFile = FileUtil.readJSONFromAsset("Cards");
        JsonValue categories = cardFile.get("Categories");

        for(int i = 0; i < categories.size; i++) {
            JsonValue currentCategory = categories.get(i).get("Cards");
            for(int j = 0; j < currentCategory.size; j++) {
                JsonValue element = currentCategory.get(j);
                String cardName = element.getString("Name");
                cardMap.put(cardName, element);
                Gdx.app.log("INFO", "SGD" + currentCategory.size + element.toString());
            }
        }

    }

    public static Card buildCard(String name, Element element, Quality quality) {

        JsonValue cardData = cardMap.get(name);
        String category = cardData.getString("Category");
        CardType.valueOf(category);

        if(quality == null)
            quality = Quality.STANDARD;

        switch(CardType.valueOf(category)) {
            case MAGIC: {
                int damage = cardData.getInt("Base_Damage");
                int cost = cardData.getInt("Base_Point_Cost");
                return new MagicCard(name, damage, cost, quality, element);
            }
            case MELEE: {
                int damage = cardData.getInt("Base_Damage");
                int width = cardData.getInt("Width");
                int height = cardData.getInt("Height");
                int cost = cardData.getInt("Base_Point_Cost");
                return new MeleeCard(name, damage, width, height, cost, quality, element);
            }
            case BOW: {
                int damage = cardData.getInt("Base_Damage");
                int cost = cardData.getInt("Base_Point_Cost");
                return new BowCard(name, damage, cost, quality, element);
            }
            case GUN: {
                int damage = cardData.getInt("Base_Damage");
                int cost = cardData.getInt("Base_Point_Cost");
                return new GunCard(name, damage, cost, quality, element);
            }
            case SUPPORT:

                break;
            case SPECIAL:

                break;
            case THROWABLE:

                break;
            default:

                break;
        }

        return null;
    }

    public static Card buildCard(String name) {
        return buildCard(name, null, null);
    }

    public static Card buildCard(String name, Element element) {
        return buildCard(name, element, null);
    }

    public static Card buildCard(String name, Quality quality) {
        return buildCard(name, null, quality);
    }
}
