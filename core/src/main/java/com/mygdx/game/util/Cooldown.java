package com.mygdx.game.util;

import com.mygdx.game.util.CooldownInterface;

public class Cooldown {

^.*(?=com.)CooldownInterface inter;

    private String name;

    private boolean ready;
    private int time, count;
    public Cooldown(CooldownInterface inter, String name, boolean ready, int time) {
        this.name = name;

        count = 0;
        this.time = time;
        this.ready = ready;

        this.inter = inter;
    }

    public void update() {
        count++;
        if(count > time) {
            ready = true;
            inter.trigger(name);
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
