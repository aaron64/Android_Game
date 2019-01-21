package com.mygdx.game.util;

import java.util.Random;

public class MapAreaGenerator {

    private long seed;
    private Random rand;

    public MapAreaGenerator(long seed) {
        this.seed = seed;
        rand = new Random(seed);
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }
}
