package com.mygdx.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entities.battle.BattlePlayer;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.particles.ParticleSystem;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.util.Vector2f;

public class MapBattle extends Map {

    private Texture bg_decor;
    private Vector2f backgroundPos;

    private BattlePlayer player;

    private float scrollRate;

    private SceneBattle scene;

    private ParticleSystem particles, particlesFG;

    public MapBattle(SceneBattle scene, String bgPath, SceneBattleGrid grid, BattlePlayer player) {
        super();

        this.scene = scene;

        setBackground(Image.getImage("backgrounds/" + bgPath));
        backgroundPos = new Vector2f(0, 0);

        //bg_decor = Image.getImage("backgrounds/bg_decor1");

        scrollRate = 1.2f;

        this.player = player;

        //particles = new ParticleSystem("snow", 100, -3, -5, 2, 2, 2, 4);
        //particlesFG = new ParticleSystem("snow", 50, -3, -5, 2, 2, 6, 4);

        //particles = new ParticleSystem("rain", 300, -3, -30, 2, 2, 8, 4);
        //particlesFG = new ParticleSystem("rain", 100, -3, -25, 2, 2, 10, 4);
    }

    @Override
    public void update() {
        //particles.update(scene);
        //particlesFG.update(scene);
    }

    @Override
    public void render(RenderSystem rs) {
        rs.draw(getBackground(), backgroundPos, Window.getSize());
        //rs.draw(bg_decor, backgroundPos, Window.getSize());
        //particles.render(rs);
    }

    @Override
    public void renderForeground(RenderSystem rs) {
        //particlesFG.render(rs);
    }
}
