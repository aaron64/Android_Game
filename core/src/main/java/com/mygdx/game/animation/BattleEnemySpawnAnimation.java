package com.mygdx.game.animation;

import com.mygdx.game.Game;
import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattleTile;

/**
 * BattleEnemySpawnAnimation
 *
 * Animation for an enemy falling from the sky to the grid
 *
 * @author  Aaron Chambers
 */
public class BattleEnemySpawnAnimation extends Animation {

    private BattleEnemy enemy;
    private float spawnY;

    private float dropSpeed;

    private SceneBattleTile tile;

    /**
     * BattleEnemySpawnAnimation constructor
     * @param lock Whether the game should wait for the animation to finish
     * @param simultaneous Whether the animation should run along other animations queued
     * @param enemy Enemy falling
     * @param tile Tile the enemy will drop on
     */
    public BattleEnemySpawnAnimation(boolean lock, boolean simultaneous, BattleEnemy enemy, SceneBattleTile tile) {
        super(lock, simultaneous, "ENEMY_SPAWN");
        this.enemy = enemy;

        dropSpeed = -10;

        spawnY = enemy.getPos().y;
        enemy.setY(Window.getHeight());

        this.tile = tile;
    }

    /**
     * update
     * updates the drop speed, sets enemy's y cordnate accordingly
     * @param scene The current scene
     */
    @Override
    public void update(Scene scene) {
        dropSpeed += Game.getGravity();

        enemy.moveY(dropSpeed);
        if(enemy.getPos().y < spawnY) {
            enemy.setY(spawnY);

            //scene.addAnimation(new BattleTileBounceAnimation(tile,30, 15));
            done = true;
        }
    }
}
