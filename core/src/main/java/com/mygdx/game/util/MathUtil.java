package com.mygdx.game.util;

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

    public static float getDistance(Vector2f v) {
        return (float) Math.sqrt(v.x * v.x + v.y * v.y);
    }

    public static float getDistance(Vector2f v1, Vector2f v2) {
        float dx = v1.x - v2.x;
        float dy = v1.y - v2.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public static Vector2f getUnitVector(Vector2f vector) {
        float x = vector.x;
        float y = vector.y;
        float mag = getDistance(vector);

        return new Vector2f(x/mag, y/mag);
    }

    public static boolean flipCoin(float f) {
        return Math.random() <= f;
    }
}
