package com.mygdx.game.util;

public class Cooldown {

    private CooldownInterface inter;

    private String name;

    private boolean ready;
    private int time, duration;
    public Cooldown(CooldownInterface inter, String name, boolean ready, int time) {
        this.name = name;

        duration = 0;
        this.time = time;
        this.ready = ready;

        this.inter = inter;
    }

    public void update() {
        duration++;
        if(duration > time) {
            ready = true;
            inter.trigger(name);
        }
    }

    public boolean ready() {
        return ready;
    }

    public void reset() {
        ready = false;
        duration = 0;
    }

    public int getDuration() {
        return time;
    }

    public void setDuration(int time) {
        this.time = time;
    }
}
