package com.mygdx.game.animation;

import com.mygdx.game.entities.main_area.Player;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Vec2f;

/**
 * PlayerDashAnimation
 *
 * Animation that quickly moves the player
 * in a specified direction
 *
 * @author  Aaron Chambers
 */
public class PlayerDashAnimation extends Animation {

    private Player player;
    private Vec2f direction;
    private int time, count;
    private float velocity;


    /**
     * PlayerDashAnimation constructor
     * @param lock Whether the game should wait for the animation to finish
     * @param simultaneous Whether the animation should run along other animations queued
     * @param player Player dashing
     * @param dir Direction of the dash
     * @param time Total time to dash
     * @param dist Total distance of dash
     */
    public PlayerDashAnimation(boolean lock, boolean simultaneous, Player player, Vec2f dir, int time, float dist) {
        super(lock, simultaneous, "DASH");

        this.player = player;
        this.direction = dir;

        this.time = time;
        this.count = 0;

        this.velocity = dist/time;

        this.direction.multiply(this.velocity);
    }

    /**
     * update
     * moves the player towards dir
     * @param scene The current scene
     */
    @Override
    public void update(Scene scene) {
        count++;
        if(count >= time) {
            done = true;
        }

        player.move(direction);
    }
}
