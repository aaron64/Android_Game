package com.mygdx.game.animation;

import com.mygdx.game.Game;
import com.mygdx.game.animation.Animation;
import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.graphics.Window;

public class BattleEnemySpawnAnimation extends Animation {
    private BattleEnemy enemy;
    private float spawnY;

    private float dropSpeed;

    public BattleEnemySpawnAnimation(boolean lock, boolean simultaneous, BattleEnemy enemy) {
        super(lock, simultaneous, "ENEMY_SPAWN");
        this.enemy = enemy;

        dropSpeed = -10;

        spawnY = enemy.getPos().y;
        enemy.setY(Window.getHeight());
    }

    @Override
    public void update(Scene scene) {
        dropSpeed += Game.getGravity();

        enemy.moveY(dropSpeed);
        if(enemy.getPos().y < spawnY) {
            enemy.setY(spawnY);
            done = true;
        }
    }
}
