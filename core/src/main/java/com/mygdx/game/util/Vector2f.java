package com.mygdx.game.util;


import com.badlogic.gdx.math.Vector2;

public class Vector2f extends Vector2a {

    public float x, y;
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f() {
        this(0, 0);
    }

    public Vector2f(Vector2a v) {
        this(v.getValueX(), v.getValueY());
    }

    public static Vector2f addVectors(Vector2a v1, Vector2a v2) {
        return new Vector2f((int)(v1.getValueX() + v2.getValueX()), (int)(v1.getValueY() + v2.getValueY()));
    }
    public static Vector2f subtractVectors(Vector2a v1, Vector2a v2) {
        return new Vector2f((int)(v1.getValueX() - v2.getValueX()), (int)(v1.getValueY() - v2.getValueY()));
    }
    public static Vector2f multiplyVectors(Vector2a v1, Vector2a v2) {
        return new Vector2f((int)(v1.getValueX() * v2.getValueX()), (int)(v1.getValueY() * v2.getValueY()));
    }
    public static Vector2f divideVectors(Vector2a v1, Vector2a v2) {
        return new Vector2f((int)(v1.getValueX() / v2.getValueX()), (int)(v1.getValueY() / v2.getValueY()));
    }
    public static Vector2f multiplyVector(Vector2a v, float m) {
        return new Vector2f((int)(v.getValueX() * m), (int)(v.getValueY() * m));
    }
    public static Vector2f divideVector(Vector2a v, float m) {
        return new Vector2f((int)(v.getValueX() / m), (int)(v.getValueY() / m));
    }


    public Vector2 toVec2() {
        return new Vector2(x, y);
    }


    public void add(Vector2f v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void subtract(float x, float y) {
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


    public float w() {
        return x;
    }

    public float h() {
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
