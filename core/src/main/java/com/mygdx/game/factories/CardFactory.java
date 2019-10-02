package com.mygdx.game.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.items.cards.BombCard;
import com.mygdx.game.items.cards.BowCard;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.items.cards.CardType;
import com.mygdx.game.items.cards.GunCard;
import com.mygdx.game.items.cards.HealCard;
import com.mygdx.game.items.cards.MagicCard;
import com.mygdx.game.items.cards.MeleeCard;
import com.mygdx.game.items.cards.ThrowableSize;
import com.mygdx.game.util.FileUtil;
import com.mygdx.game.util.MathUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class CardFactory {

    private static HashMap<String, JsonValue> cardMap;
    private static ArrayList<String> nameList;

    public static void init() {
        cardMap = new HashMap<String, JsonValue>();
        nameList = new ArrayList<String>();

        JsonValue cardFile = FileUtil.readJSONFromAsset("Cards");
        JsonValue categories = cardFile.get("Categories");

        for(int i = 0; i < categories.size; i++) {
            JsonValue currentCategory = categories.get(i).get("Cards");
            for(int j = 0; j < currentCategory.size; j++) {
                JsonValue element = currentCategory.get(j);
                String cardName = element.getString("Name");
                cardMap.put(cardName, element);
                nameList.add(cardName);
            }
        }

    }

    public static Card buildCard(String name, Element element, Quality quality) {
        Gdx.app.log("INFO", "Building: " + name);
        JsonValue cardData = cardMap.get(name);
        String category = cardData.getString("Category");
        int cost = cardData.getInt("Base_Point_Cost");
        int lockInitial = cardData.getInt("LockI");
        int lockFinal = cardData.getInt("LockF");
        CardType.valueOf(category);

        if(quality == null)
            quality = Quality.STANDARD;

        switch(CardType.valueOf(category)) {
            case MAGIC: {
                int damage = cardData.getInt("Base_Damage");
                return new MagicCard(name, lockInitial, lockFinal, damage, cost, quality, element);
            }
            case MELEE: {
                int damage = cardData.getInt("Base_Damage");
                int width = cardData.getInt("Width");
                int height = cardData.getInt("Height");
                return new MeleeCard(name, lockInitial, lockFinal, damage, width, height, cost, quality, element);
            }
            case BOW: {
                int damage = cardData.getInt("Base_Damage");
                return new BowCard(name, lockInitial, lockFinal, damage, cost, quality, element);
            }
            case GUN: {
                int damage = cardData.getInt("Base_Damage");
                return new GunCard(name, lockInitial, lockFinal, damage, cost, quality, element);
            }
            case POTION: {
                int amount = cardData.getInt("Base_Amount");
                return new HealCard(name, lockInitial, lockFinal, amount, cost, quality);
            }
            case SUPPORT:
                break;
            case SPECIAL:

                break;
            case THROWABLE:
                int damage = cardData.getInt("Base_Damage");
                int range = cardData.getInt("Range");
                String type = cardData.getString("Type");
                ThrowableSize size = parseThrowableSize(cardData.getString("Size"));

                if(type.equals("bomb")) {
                    return new BombCard(name, lockInitial, lockFinal, damage, size, range, cost, quality, element);
                }
                break;
            default:

                break;
        }

        return null;
    }

    public static ThrowableSize parseThrowableSize(String s){
        if(s.equals("small")) {
            return ThrowableSize.SMALL;
        }
        if(s.equals("medium")) {
            return ThrowableSize.MEDIUM;
        }
        if(s.equals("large")) {
            return ThrowableSize.LARGE;
        }
        if(s.equals("horizontal")) {
            return ThrowableSize.HORIZONTAL;
        }
        if(s.equals("vertical")) {
            return ThrowableSize.VERTICAL;
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

    public static Card buildRandomCard(int[] qualityWeights) {
        String name = nameList.get((int)(Math.random() * nameList.size()));
        int qualityI = MathUtil.getWeightedRandom(qualityWeights);

        return buildCard(name, Element.getRandomElement(true), Quality.getQuality(qualityI));
    }


}
