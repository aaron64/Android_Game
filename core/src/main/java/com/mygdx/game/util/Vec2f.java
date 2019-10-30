package com.mygdx.game.util;


import com.badlogic.gdx.math.Vector2;

public class Vec2f extends Vec2a {

    public float x, y;
    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2f() {
        this(0, 0);
    }

    public Vec2f(Vec2a v) {
        this(v.getValueX(), v.getValueY());
    }

    public static Vec2f addVectors(Vec2a v1, Vec2a v2) {
        return new Vec2f((int)(v1.getValueX() + v2.getValueX()), (int)(v1.getValueY() + v2.getValueY()));
    }
    public static Vec2f subtractVectors(Vec2a v1, Vec2a v2) {
        return new Vec2f((int)(v1.getValueX() - v2.getValueX()), (int)(v1.getValueY() - v2.getValueY()));
    }
    public static Vec2f multiplyVectors(Vec2a v1, Vec2a v2) {
        return new Vec2f((int)(v1.getValueX() * v2.getValueX()), (int)(v1.getValueY() * v2.getValueY()));
    }
    public static Vec2f divideVectors(Vec2a v1, Vec2a v2) {
        return new Vec2f((int)(v1.getValueX() / v2.getValueX()), (int)(v1.getValueY() / v2.getValueY()));
    }
    public static Vec2f multiplyVector(Vec2a v, float m) {
        return new Vec2f((int)(v.getValueX() * m), (int)(v.getValueY() * m));
    }
    public static Vec2f divideVector(Vec2a v, float m) {
        return new Vec2f((int)(v.getValueX() / m), (int)(v.getValueY() / m));
    }


    public Vector2 toVec2() {
        return new Vector2(x, y);
    }


    public void add(Vec2f v) {
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

    public void add(Vec2a v) {
        this.x += v.getValueX();
        this.y += v.getValueY();
    }

    public void subtract(Vec2a v) {
        this.x -= v.getValueX();
        this.y -= v.getValueY();
    }

    public void multiply(Vec2a v) {
        this.x *= v.getValueX();
        this.y *= v.getValueY();
    }

    public void divide(Vec2a v) {
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
