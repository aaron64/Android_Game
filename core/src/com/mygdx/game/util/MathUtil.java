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

    public static Vector2f getUnitVector(Vector2f vector) {
        float x = vector.x;
        float y = vector.y;
        float mag = getDistance(vector);

        return new Vector2f(x/mag, y/mag);
    }
}
