package com.mygdx.game.util;


import com.badlogic.gdx.math.Vector2;

public class Vector2i extends Vector2a {

    public int x, y;
    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i() {
        this(0, 0);
    }

    public Vector2i(Vector2a v) {
        this((int)v.getValueX(), (int)v.getValueY());
    }


    public static Vector2i addVectors(Vector2a v1, Vector2a v2) {
        return new Vector2i((int)(v1.getValueX() + v2.getValueX()), (int)(v1.getValueY() + v2.getValueY()));
    }
    public static Vector2i subtractVectors(Vector2a v1, Vector2a v2) {
        return new Vector2i((int)(v1.getValueX() - v2.getValueX()), (int)(v1.getValueY() - v2.getValueY()));
    }
    public static Vector2i multiplyVectors(Vector2a v1, Vector2a v2) {
        return new Vector2i((int)(v1.getValueX() * v2.getValueX()), (int)(v1.getValueY() * v2.getValueY()));
    }
    public static Vector2i divideVectors(Vector2a v1, Vector2a v2) {
        return new Vector2i((int)(v1.getValueX() / v2.getValueX()), (int)(v1.getValueY() / v2.getValueY()));
    }
    public static Vector2i multiplyVector(Vector2a v, float m) {
        return new Vector2i((int)(v.getValueX() * m), (int)(v.getValueY() * m));
    }
    public static Vector2i divideVector(Vector2a v, float m) {
        return new Vector2i((int)(v.getValueX() / m), (int)(v.getValueY() / m));
    }


    public Vector2 toVec2() {
        return new Vector2(x, y);
    }


    public void add(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void subtract(int x, int y) {
        this.x -= x;
        this.y -= y;
    }

    public void add(Vector2a v) {
        this.x += v.getValueX();
        this.y += v.getValueY();
    }

    public void subtract(Vector2a v) {
        this.x -= v.getValueX();
        this.y -= v.getValueY();
    }

    public void multiply(Vector2a v) {
        this.x *= v.getValueX();
        this.y *= v.getValueY();
    }

    public void divide(Vector2a v) {
        this.x /= v.getValueX();
        this.y /= v.getValueY();
    }

    public void multiply(float m) {
        this.x *= m;
        this.y *= m;
    }

    public void divide(float m) {
        this.x /= m;
        this.y /= m;
    }


    public int w() {
        return x;
    }

    public int h() {
        return y;
    }


    @Override
    float getValueX() {
        return x;
    }

    @Override
    float getValueY() {
        return y;
    }
}
