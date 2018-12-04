package com.mygdx.game.util;

import com.badlogic.gdx.math.Vector2;

public class MathUtil {

    public static int getWeightedRandom(int[] weights) {
        int sum = 0;
        for(int i = 0; i < weights.length; i++) {
            sum += weights[i];
        }

        double rand = Math.random() * sum;
        for(int i = 0; i < weights.length; i++) {
            rand -= weights[i];
            if(rand < 0)
                return i;
        }
        return weights.length -1;
    }

    public static float getDistance(Vector2 v) {
        return (float) Math.sqrt(v.x * v.x + v.y * v.y);
    }

    public static Vector2 getUnitVector(Vector2 vector) {
        float x = vector.x;
        float y = vector.y;
        float mag = getDistance(vector);

        return new Vector2(x/mag, y/mag);
    }

    public static Vector2 multiplyVec(Vector2 vec1, Vector2 vec2) {
        return new Vector2(vec1.x * vec2.x, vec1.y * vec2.y);
    }

    public static Vector2 multiplyVec(Vector2 vec1, float m) {
        return new Vector2(vec1.x * m, vec1.y * m);
    }

    public static Vector2 divideVec(Vector2 vec1, Vector2 vec2) {
        return new Vector2(vec1.x / vec2.x, vec1.y / vec2.y);
    }

    public static Vector2 divideVec(Vector2 vec1, float m) {
        return new Vector2(vec1.x / m, vec1.y / m);
    }

    public static Vector2 addVec(Vector2 vec1, Vector2 vec2) {
        return new Vector2(vec1.x + vec2.x, vec1.y + vec2.y);
    }

    public static Vector2 addVec(Vector2 vec1, float m) {
        return new Vector2(vec1.x + m, vec1.y + m);
    }

    public static Vector2 subVec(Vector2 vec1, Vector2 vec2) {
        return new Vector2(vec1.x - vec2.x, vec1.y - vec2.y);
    }

    public static Vector2 subVec(Vector2 vec1, float m) {
        return new Vector2(vec1.x - m, vec1.y - m);
    }
}
