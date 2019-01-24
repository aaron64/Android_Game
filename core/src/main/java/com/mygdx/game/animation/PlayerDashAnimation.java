package com.mygdx.game.animation;

import com.mygdx.game.entities.main_area.Player;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Vector2f;

public class PlayerDashAnimation extends Animation {

    private Player player;
    private Vector2f direction;
    private int time, count;
    private float velocity;

    public PlayerDashAnimation(boolean lock, boolean simultaneous, Player player, Vector2f dir, int time, float dist) {
        super(lock, simultaneous, "DASH");

        this.player = player;
        this.direction = dir;

        this.time = time;
        this.count = 0;

        this.velocity = dist/time;

        this.direction.multiply(this.velocity);
    }

    @Override
    public void update(Scene scene) {
        count++;
        if(count >= time) {
            done = true;
        }

        player.move(direction);
    }
}
