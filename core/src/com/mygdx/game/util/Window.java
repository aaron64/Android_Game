package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Window {

    private static int width = Gdx.graphics.getWidth();
    private static int height = Gdx.graphics.getHeight();
    private static Vector2 size = new Vector2(width, height);

    public static int percLeft(float perc) {
        return (int) (width * perc);
    }

    public static int percRight(float perc) {
        return width - (int) (width * perc);
    }

    public static int percTop(float perc) {
        return height - (int) (height * perc);
    }

    public static int percBottom(float perc) {
        return (int) (height * perc);
    }

    public static Vector2 getCenter() {
        return new Vector2(width/2, height/2);
    }

    public static Vector2 getTopLeft() {
        return new Vector2(0, height);
    }

    public static Vector2 getTopRight() {
        return new Vector2(width, height);
    }

    public static Vector2 getBottomLeft() {
        return new Vector2(0, 0);
    }

    public static Vector2 getBottomRight() {
        return new Vector2(width, 0);
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        Window.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        Window.height = height;
    }

    public static Vector2 getSize() {
        return size;
    }

    public static void setSize(Vector2 size) {
        Window.size = size;
    }
}
