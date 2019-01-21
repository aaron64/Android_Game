package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.crypto.Data;

public class FileUtil {
    public static JsonValue readJSONFromAsset(String name) {

        JsonReader json = new JsonReader();
        JsonValue base = json.parse(Gdx.files.internal("JSON/" + name + ".json"));

        return base;
    }
}
