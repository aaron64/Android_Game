package com.mygdx.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class Image {

    private static HashMap<String, Texture> textureMap;

    public static Texture BACKGROUND_BG1 = new Texture("backgrounds/bg1.png");
    public static Texture BACKGROUND_DECOR_1 = new Texture("backgrounds/bg_decor1.png");

    public static Texture BATTLE_MAP_0 = new Texture("battle_maps/map0.png");
    public static Texture BATTLE_MAP_1 = new Texture("battle_maps/map1.png");
    public static Texture BATTLE_MAP_2 = new Texture("battle_maps/map2.png");
    public static Texture BATTLE_MAP_3 = new Texture("battle_maps/map3.png");
    public static Texture BATTLE_MAP_4 = new Texture("battle_maps/map4.png");
    public static Texture BATTLE_MAP_5 = new Texture("battle_maps/map5.png");
    public static Texture BATTLE_MAP_6 = new Texture("battle_maps/map6.png");
    public static Texture BATTLE_MAP_7 = new Texture("battle_maps/map7.png");


    public static Texture ENTITY_BATTLE_MISC_ARROW = new Texture("entities/battle/misc/arrow.png");
    public static Texture ENTITY_BATTLE_MISC_BOMB = new Texture("entities/battle/misc/bomb.png");
    public static Texture ENTITY_BATTLE_MISC_MAGIC = new Texture("entities/battle/misc/magic_projectile.png");
    public static Texture TILE_EXPLOSION = new Texture("entities/battle/misc/explosion.png");

    public static Texture ENTITY_BATTLE_ENEMY = new Texture("entities/battle/enemy.png");
    public static Texture ENTITY_BATTLE_MAGE = new Texture("entities/battle/mage.png");
    public static Texture ENTITY_BATTLE_PILLAR = new Texture("entities/battle/pillar.png");
    public static Texture ENTITY_BATTLE_PLAYER = new Texture("entities/battle/player.png");

    public static Texture ENTITY_MAIN_CHEST_STANDARD = new Texture("entities/main_area/chest_standard.png");
    public static Texture ENTITY_MAIN_CHEST_STANDARD_OPEN = new Texture("entities/main_area/chest_standard_open.png");
    public static Texture ENTITY_MAIN_CHEST_LOW = new Texture("entities/main_area/chest_low.png");
    public static Texture ENTITY_MAIN_CHEST_LOW_OPEN = new Texture("entities/main_area/chest_low_open.png");
    public static Texture ENTITY_MAIN_ENEMY = new Texture("entities/main_area/enemy.png");
    public static Texture ENTITY_MAIN_HEALTH_BLOB = new Texture("entities/main_area/health_blob.png");
    public static Texture ENTITY_MAIN_PLAYER = new Texture("entities/main_area/player.png");

    public static Texture ENTITY_PARTICLE_GRASS = new Texture("entities/particles/grass.png");
    public static Texture ENTITY_PARTICLE_WATER = new Texture("entities/particles/water.png");
    public static Texture ENTITY_PARTICLE_RAIN = new Texture("entities/particles/rain.png");
    public static Texture ENTITY_PARTICLE_SNOW = new Texture("entities/particles/snow.png");

    public static Texture ENTITY_TILE_BATTLE_ENEMY = new Texture("entities/tiles/battle/tile_enemy.png");
    public static Texture ENTITY_TILE_BATTLE_FRIENDLY = new Texture("entities/tiles/battle/tile_friendly.png");
    public static Texture ENTITY_TILE_BATTLE_LIGHT_UP = new Texture("entities/tiles/battle/tile_light_up.png");
    public static Texture ENTITY_TILE_BATTLE_NEUTRAL = new Texture("entities/tiles/battle/tile_neutral.png");
    public static Texture ENTITY_TILE_BATTLE_NONE = new Texture("entities/tiles/battle/tile_none.png");

    public static Texture ENTITY_TILE_BASIC = new Texture("entities/tiles/tile_basic.png");
    public static Texture ENTITY_TILE_END0 = new Texture("entities/tiles/tile_end0.png");
    public static Texture ENTITY_TILE_END1 = new Texture("entities/tiles/tile_end1.png");
    public static Texture ENTITY_TILE_END2 = new Texture("entities/tiles/tile_end2.png");
    public static Texture ENTITY_TILE_WATER = new Texture("entities/tiles/tile_water.png");

    public static Texture GUI_BOXB = new Texture("gui/box/boxB.png");
    public static Texture GUI_BOXBL = new Texture("gui/box/boxBL.png");
    public static Texture GUI_BOXBR = new Texture("gui/box/boxBR.png");
    public static Texture GUI_BOXC = new Texture("gui/box/boxC.png");
    public static Texture GUI_BOXL = new Texture("gui/box/boxL.png");
    public static Texture GUI_BOXR = new Texture("gui/box/boxR.png");
    public static Texture GUI_BOXT = new Texture("gui/box/boxT.png");
    public static Texture GUI_BOXTL = new Texture("gui/box/boxTL.png");
    public static Texture GUI_BOXTR = new Texture("gui/box/boxTR.png");

    public static Texture GUI_BUTTON = new Texture("gui/button/button.png");
    public static Texture GUI_BUTTON_ICON_EXIT = new Texture("gui/button/button_icon_exit.png");
    public static Texture GUI_BUTTON_ICON_GO = new Texture("gui/button/button_icon_go.png");
    public static Texture GUI_BUTTON_ICON_MENU = new Texture("gui/button/button_icon_menu.png");
    public static Texture GUI_BUTTON_ICON_SHARD = new Texture("gui/button/button_icon_shard.png");
    public static Texture GUI_BUTTON_PRESSED = new Texture("gui/button/button_pressed.png");

    public static Texture GUI_HEART = new Texture("gui/heart.png");
    public static Texture GUI_SELECTION_BAR = new Texture("gui/selection_bar.png");
    public static Texture GUI_SELECTION_BAR_READY = new Texture("gui/selection_bar_ready.png");

    public static Texture CARD_BOW_ICON = new Texture("items/cards/bows/Bow_icon.png");
    public static Texture CARD_BOW_OVERLAY = new Texture("items/cards/bows/Bow_overlay.png");
    public static Texture CARD_CROSSBOW_ICON = new Texture("items/cards/bows/Crossbow_icon.png");
    public static Texture CARD_CROSSBOW_OVERLAY = new Texture("items/cards/bows/Crossbow_overlay.png");
    public static Texture CARD_LONGBOW_ICON = new Texture("items/cards/bows/Longbow_icon.png");
    public static Texture CARD_LONGBOW_OVERLAY = new Texture("items/cards/bows/Longbow_overlay.png");

    public static Texture CARD_MAGIC_ICON = new Texture("items/cards/magic/Magic_icon.png");
    public static Texture CARD_MAGIC_OVERLAY = new Texture("items/cards/magic/Magic_overlay.png");

    public static Texture CARD_CLAYMORE_ICON = new Texture("items/cards/melee/Claymore_icon.png");
    public static Texture CARD_CLAYMORE_OVERLAY = new Texture("items/cards/melee/Claymore_overlay.png");
    public static Texture CARD_CUTLASS_ICON = new Texture("items/cards/melee/Cutlass_icon.png");
    public static Texture CARD_CUTLASS_OVERLAY = new Texture("items/cards/melee/Cutlass_overlay.png");
    public static Texture CARD_DAGGER_ICON = new Texture("items/cards/melee/Dagger_icon.png");
    public static Texture CARD_DAGGER_OVERLAY = new Texture("items/cards/melee/Dagger_overlay.png");
    public static Texture CARD_LONGSWORD_ICON = new Texture("items/cards/melee/Longsword_icon.png");
    public static Texture CARD_LONGSWORD_OVERLAY = new Texture("items/cards/melee/Longsword_overlay.png");
    public static Texture CARD_SWORD_ICON = new Texture("items/cards/melee/Sword_icon.png");
    public static Texture CARD_SWORD_OVERLAY = new Texture("items/cards/melee/Sword_overlay.png");

    public static Texture CARD_POTION_GRAND_ICON = new Texture("items/cards/potions/Potion_Grand_icon.png");
    public static Texture CARD_POTION_GRAND_OVERLAY = new Texture("items/cards/potions/Potion_Grand_overlay.png");
    public static Texture CARD_POTION_ICON = new Texture("items/cards/potions/Potion_icon.png");
    public static Texture CARD_POTION_OVERLAY = new Texture("items/cards/potions/Potion_overlay.png");
    public static Texture CARD_POTION_MAJOR_ICON = new Texture("items/cards/potions/Potion_Major_icon.png");
    public static Texture CARD_POTION_MAJOR_OVERLAY = new Texture("items/cards/potions/Potion_Major_overlay.png");
    public static Texture CARD_POTION_MINOR_ICON = new Texture("items/cards/potions/Potion_Minor_icon.png");
    public static Texture CARD_POTION_MINOR_OVERLAY = new Texture("items/cards/potions/Potion_Minor_overlay.png");
    public static Texture CARD_POTION_STRONG_ICON = new Texture("items/cards/potions/Potion_Strong_icon.png");
    public static Texture CARD_POTION_STRONG_OVERLAY = new Texture("items/cards/potions/Potion_Strong_overlay.png");
    public static Texture CARD_POTION_WEAK_ICON = new Texture("items/cards/potions/Potion_Weak_icon.png");
    public static Texture CARD_POTION_WEAK_OVERLAY = new Texture("items/cards/potions/Potion_Weak_overlay.png");

    public static Texture CARD_BOMB_ICON = new Texture("items/cards/throwables/Bomb_icon.png");
    public static Texture CARD_BOMB_OVERLAY = new Texture("items/cards/throwables/Bomb_overlay.png");

    public static Texture ELEMENT_FIRE_OVERLAY = new Texture("items/cards/element_overlays/Fire_overlay.png");
    public static Texture ELEMENT_POISON_OVERLAY = new Texture("items/cards/element_overlays/Poison_overlay.png");
    public static Texture ELEMENT_SHOCK_OVERLAY = new Texture("items/cards/element_overlays/Shock_overlay.png");
    public static Texture ELEMENT_WATER_OVERLAY = new Texture("items/cards/element_overlays/Water_overlay.png");
    public static Texture ELEMENT_GRASS_OVERLAY = new Texture("items/cards/element_overlays/Grass_overlay.png");

    public static Texture SHADER_GRASS_OVERLAY = new Texture("misc/Grass_overlay.png");

    public static Texture SPOT_LIGHT = new Texture("lighting/SPOT_LIGHT.png");
    public static Texture SHADOW = new Texture("lighting/SHADOW.png");


    public static void initialize() {
        textureMap = new HashMap<String, Texture>();

        textureMap.put("backgrounds/bg1", BACKGROUND_BG1);
        textureMap.put("backgrounds/bg_decor1", BACKGROUND_DECOR_1);

        textureMap.put("battle_maps/map0", BATTLE_MAP_0);
        textureMap.put("battle_maps/map1", BATTLE_MAP_1);
        textureMap.put("battle_maps/map2", BATTLE_MAP_2);
        textureMap.put("battle_maps/map3", BATTLE_MAP_3);
        textureMap.put("battle_maps/map4", BATTLE_MAP_4);
        textureMap.put("battle_maps/map5", BATTLE_MAP_5);
        textureMap.put("battle_maps/map6", BATTLE_MAP_6);
        textureMap.put("battle_maps/map7", BATTLE_MAP_7);

        textureMap.put("entities/battle/misc/arrow", ENTITY_BATTLE_MISC_ARROW);
        textureMap.put("entities/battle/misc/bomb", ENTITY_BATTLE_MISC_BOMB);
        textureMap.put("entities/battle/misc/magic_projectile", ENTITY_BATTLE_MISC_MAGIC);
        textureMap.put("entities/battle/misc/explosion", TILE_EXPLOSION);

        textureMap.put("entities/battle/enemy", ENTITY_BATTLE_ENEMY);
        textureMap.put("entities/battle/mage", ENTITY_BATTLE_MAGE);
        textureMap.put("entities/battle/pillar", ENTITY_BATTLE_PILLAR);
        textureMap.put("entities/battle/player", ENTITY_BATTLE_PLAYER);

        textureMap.put("entities/main_area/chest_standard", ENTITY_MAIN_CHEST_STANDARD);
        textureMap.put("entities/main_area/chest_standard_open", ENTITY_MAIN_CHEST_STANDARD_OPEN);
        textureMap.put("entities/main_area/chest_low", ENTITY_MAIN_CHEST_LOW);
        textureMap.put("entities/main_area/chest_low_open", ENTITY_MAIN_CHEST_LOW_OPEN);
        textureMap.put("entities/main_area/enemy", ENTITY_MAIN_ENEMY);
        textureMap.put("entities/main_area/health_blob", ENTITY_MAIN_HEALTH_BLOB);
        textureMap.put("entities/main_area/player", ENTITY_MAIN_PLAYER);

        textureMap.put("entities/particles/grass", ENTITY_PARTICLE_GRASS);
        textureMap.put("entities/particles/water", ENTITY_PARTICLE_WATER);
        textureMap.put("entities/particles/rain", ENTITY_PARTICLE_RAIN);
        textureMap.put("entities/particles/snow", ENTITY_PARTICLE_SNOW);

        textureMap.put("entities/tiles/battle/tile_enemy", ENTITY_TILE_BATTLE_ENEMY);
        textureMap.put("entities/tiles/battle/tile_friendly", ENTITY_TILE_BATTLE_FRIENDLY);
        textureMap.put("entities/tiles/battle/tile_light_up", ENTITY_TILE_BATTLE_LIGHT_UP);
        textureMap.put("entities/tiles/battle/tile_neutral", ENTITY_TILE_BATTLE_NEUTRAL);
        textureMap.put("entities/tiles/battle/tile_none", ENTITY_TILE_BATTLE_NONE);

        textureMap.put("entities/tiles/tile_basic", ENTITY_TILE_BASIC);
        textureMap.put("entities/tiles/tile_end0", ENTITY_TILE_END0);
        textureMap.put("entities/tiles/tile_end1", ENTITY_TILE_END1);
        textureMap.put("entities/tiles/tile_end2", ENTITY_TILE_END2);
        textureMap.put("entities/tiles/tile_water", ENTITY_TILE_WATER);

        textureMap.put("gui/box/boxB", GUI_BOXB);
        textureMap.put("gui/box/boxBL", GUI_BOXBL);
        textureMap.put("gui/box/boxBR", GUI_BOXBR);
        textureMap.put("gui/box/boxC", GUI_BOXC);
        textureMap.put("gui/box/boxL", GUI_BOXL);
        textureMap.put("gui/box/boxR", GUI_BOXR);
        textureMap.put("gui/box/boxT", GUI_BOXT);
        textureMap.put("gui/box/boxTL", GUI_BOXTL);
        textureMap.put("gui/box/boxTR", GUI_BOXTR);

        textureMap.put("gui/button/button", GUI_BUTTON);
        textureMap.put("gui/button/button_icon_exit", GUI_BUTTON_ICON_EXIT);
        textureMap.put("gui/button/button_icon_go", GUI_BUTTON_ICON_GO);
        textureMap.put("gui/button/button_icon_menu", GUI_BUTTON_ICON_MENU);
        textureMap.put("gui/button/button_icon_shard", GUI_BUTTON_ICON_SHARD);
        textureMap.put("gui/button/button_pressed", GUI_BUTTON_PRESSED);

        textureMap.put("gui/heart", GUI_HEART);
        textureMap.put("gui/selection_hard", GUI_SELECTION_BAR);
        textureMap.put("gui/selection_bar_ready", GUI_SELECTION_BAR_READY);

        textureMap.put("items/cards/bows/Bow_icon", CARD_BOW_ICON);
        textureMap.put("items/cards/bows/Bow_overlay", CARD_BOW_OVERLAY);
        textureMap.put("items/cards/bows/Crossbow_icon", CARD_CROSSBOW_ICON);
        textureMap.put("items/cards/bows/Crossbow_overlay", CARD_CROSSBOW_OVERLAY);
        textureMap.put("items/cards/bows/Longbow_icon", CARD_LONGBOW_ICON);
        textureMap.put("items/cards/bows/Longbow_overlay", CARD_LONGBOW_OVERLAY);

        textureMap.put("items/cards/magic/Magic_icon", CARD_MAGIC_ICON);
        textureMap.put("items/cards/magic/Magic_overlay", CARD_MAGIC_OVERLAY);

        textureMap.put("items/cards/melee/Claymore_icon", CARD_CLAYMORE_ICON);
        textureMap.put("items/cards/melee/Claymore_overlay", CARD_CLAYMORE_OVERLAY);
        textureMap.put("items/cards/melee/Cutlass_icon", CARD_CUTLASS_ICON);
        textureMap.put("items/cards/melee/Cutlass_overlay", CARD_CUTLASS_OVERLAY);
        textureMap.put("items/cards/melee/Dagger_icon", CARD_DAGGER_ICON);
        textureMap.put("items/cards/melee/Dagger_overlay", CARD_DAGGER_OVERLAY);
        textureMap.put("items/cards/melee/Longsword_icon", CARD_LONGSWORD_ICON);
        textureMap.put("items/cards/melee/Longsword_overlay", CARD_LONGSWORD_OVERLAY);
        textureMap.put("items/cards/melee/Sword_icon", CARD_SWORD_ICON);
        textureMap.put("items/cards/melee/Sword_overlay", CARD_SWORD_OVERLAY);

        textureMap.put("items/cards/potions/Potion_Grand_icon", CARD_POTION_GRAND_ICON);
        textureMap.put("items/cards/potions/Potion_Grand_overlay", CARD_POTION_GRAND_OVERLAY);
        textureMap.put("items/cards/potions/Potion_icon", CARD_POTION_ICON);
        textureMap.put("items/cards/potions/Potion_overlay", CARD_POTION_OVERLAY);
        textureMap.put("items/cards/potions/Potion_Major_icon", CARD_POTION_MAJOR_ICON);
        textureMap.put("items/cards/potions/Potion_Major_overlay", CARD_POTION_MAJOR_OVERLAY);
        textureMap.put("items/cards/potions/Potion_Minor_icon", CARD_POTION_MINOR_ICON);
        textureMap.put("items/cards/potions/Potion_Minor_overlay", CARD_POTION_MINOR_OVERLAY);
        textureMap.put("items/cards/potions/Potion_Strong_icon", CARD_POTION_STRONG_ICON);
        textureMap.put("items/cards/potions/Potion_Strong_overlay", CARD_POTION_STRONG_OVERLAY);
        textureMap.put("items/cards/potions/Potion_Weak_icon", CARD_POTION_WEAK_ICON);
        textureMap.put("items/cards/potions/Potion_Weak_overlay", CARD_POTION_WEAK_OVERLAY);

        textureMap.put("items/cards/throwables/Bomb_icon", CARD_BOMB_ICON);
        textureMap.put("items/cards/throwables/Bomb_overlay", CARD_BOMB_OVERLAY);

        textureMap.put("items/cards/element_overlays/Fire_overlay", ELEMENT_FIRE_OVERLAY);
        textureMap.put("items/cards/element_overlays/Grass_overlay", ELEMENT_GRASS_OVERLAY);
        textureMap.put("items/cards/element_overlays/Poison_overlay", ELEMENT_POISON_OVERLAY);
        textureMap.put("items/cards/element_overlays/Shock_overlay", ELEMENT_SHOCK_OVERLAY);
        textureMap.put("items/cards/element_overlays/Water_overlay", ELEMENT_WATER_OVERLAY);

        textureMap.put("misc/Grass_overlay", SHADER_GRASS_OVERLAY);

        textureMap.put("lighting/SPOT_LIGHT", SPOT_LIGHT);
        textureMap.put("lighting/SHADOW", SHADOW);
    }

    public static Texture getImage(String s) {
        if(!textureMap.containsKey(s))
            Gdx.app.log("IMAGE", "Could not find " + s + " in texture map.");
        return textureMap.get(s);
    }

}
