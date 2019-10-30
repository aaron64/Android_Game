package com.mygdx.game.util;


import com.badlogic.gdx.math.Vector2;

public class Vec2i extends Vec2a {

    public int x, y;
    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2i() {
        this(0, 0);
    }

    public Vec2i(Vec2a v) {
        this((int)v.getValueX(), (int)v.getValueY());
    }


    public static Vec2i addVectors(Vec2a v1, Vec2a v2) {
        return new Vec2i((int)(v1.getValueX() + v2.getValueX()), (int)(v1.getValueY() + v2.getValueY()));
    }
    public static Vec2i subtractVectors(Vec2a v1, Vec2a v2) {
        return new Vec2i((int)(v1.getValueX() - v2.getValueX()), (int)(v1.getValueY() - v2.getValueY()));
    }
    public static Vec2i multiplyVectors(Vec2a v1, Vec2a v2) {
        return new Vec2i((int)(v1.getValueX() * v2.getValueX()), (int)(v1.getValueY() * v2.getValueY()));
    }
    public static Vec2i divideVectors(Vec2a v1, Vec2a v2) {
        return new Vec2i((int)(v1.getValueX() / v2.getValueX()), (int)(v1.getValueY() / v2.getValueY()));
    }
    public static Vec2i multiplyVector(Vec2a v, float m) {
        return new Vec2i((int)(v.getValueX() * m), (int)(v.getValueY() * m));
    }
    public static Vec2i divideVector(Vec2a v, float m) {
        return new Vec2i((int)(v.getValueX() / m), (int)(v.getValueY() / m));
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
