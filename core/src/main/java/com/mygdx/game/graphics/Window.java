package com.mygdx.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class Window {

    private static int width = Gdx.graphics.getWidth();
    private static int height = Gdx.graphics.getHeight();
    private static Vector2i size = new Vector2i(width, height);

    private static Rectangle rect = new Rectangle(0,0,width, height);

    public static boolean inWindow(com.mygdx.game.entities.Entity e) {
        com.mygdx.game.util.Vector2f pos = e.getPos();
        Vector2i size = e.getSize();

        return rect.contains(new Rectangle(pos.x, pos.y, size.w(), size.h()));
    }

    public static boolean inWindow(com.mygdx.game.util.Vector2f pos, Vector2i size) {
        return rect.contains(new Rectangle(pos.x, pos.y, size.w(), size.h()));
    }

    public static boolean inWindow(com.mygdx.game.util.Vector2f pos) {
        return rect.contains(pos.x, pos.y);
    }

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

    public static int percWidth(float perc) { return (int) (width * perc); }

    public static int percHeight(float perc) { return (int) (height * perc); }

    public static Vector2i getCenter() {
        return new Vector2i(width/2, height/2);
    }

    public static Vector2i getTopLeft() {
        return new Vector2i(0, height);
    }

    public static Vector2i getTopRight() {
        return new Vector2i(width, height);
    }

    public static Vector2i getBottomLeft() {
        return new Vector2i(0, 0);
    }

    public static Vector2i getBottomRight() {
        return new Vector2i(width, 0);
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

    public static Vector2i getSize() {
        return size;
    }

    public static void setSize(Vector2i size) {
        Window.size = size;
    }
}
