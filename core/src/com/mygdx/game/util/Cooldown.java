package com.mygdx.game.util;

public class Cooldown {

    private boolean ready;
    private int time, count;
    public Cooldown(boolean ready, int time) {
        count = 0;
        this.time = time;
        this.ready = ready;
    }

    public void update() {
        count++;
        if(count > time) {
            ready = true;
        }
    }

    public boolean ready() {
        return ready;
    }

    public void reset() {
        ready = false;
        count = 0;
    }

    public int getDuration() {
        return time;
    }

    public void setDuration(int time) {
        this.time = time;
    }
}
