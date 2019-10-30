package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontUtil {

    public static BitmapFont getFont(int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    public static Vec2f getTextSize(BitmapFont font, String text) {
        GlyphLayout layout = new GlyphLayout(font, text);
        return new Vec2f(layout.width, layout.height);
    }

    public static FreeTypeFontGenerator getGenerator() {
        return new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P.ttf"));
    }
}
