package com.mygdx.game.factories;

import com.mygdx.game.entities.main_area.Enemy;
import com.mygdx.game.entities.main_area.EnemyFollower;
import com.mygdx.game.entities.main_area.EnemyStill;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.Vector2f;

public class EnemyFactory {

    private static int[] weights = {1,3};
    public static void newMainAreaEnemy(SceneMainArea scene, Vector2f pos) {
        Enemy enemy = null;

        int r = MathUtil.getWeightedRandom(weights);

        switch(r) {
            case 0:
                enemy = new EnemyStill(scene, pos, "enemy");
            break;
            case 1:
                enemy = new EnemyFollower(scene, pos, "enemy");
            break;
            default:
            break;
        }

        if(enemy != null)
            scene.addEntity(enemy);
    }
}
