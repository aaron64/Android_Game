package com.mygdx.game.items.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.util.FileUtil;

import java.util.HashMap;

public class CardLoader {

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

    public static Card buildCard(String name) {

        JsonValue element = cardMap.get(name);
        String category = element.getString("Category");
        CardType.valueOf(category);
        switch(CardType.valueOf(category)) {
            case MAGIC: {
                int damage = element.getInt("Base_Damage");
                return new MagicCard(name, damage, Quality.STANDARD, Element.FIRE);
            }
            case MELEE: {
                int damage = element.getInt("Base_Damage");
                int width = element.getInt("Width");
                int height = element.getInt("Height");
                return new MeleeCard(name, damage, width, height, Quality.STANDARD, null);
            }
            case BOW: {
                int damage = element.getInt("Base_Damage");
                return new BowCard(name, damage, Quality.STANDARD, null);
            }
            case GUN: {
                int damage = element.getInt("Base_Damage");
                return new GunCard(name, damage, Quality.STANDARD, null);
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
}
