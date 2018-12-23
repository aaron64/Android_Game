package com.mygdx.game.map;

import com.badlogic.gdx.graphics.Texture;

import com.mygdx.game.entities.battle.BattlePlayer;
import com.mygdx.game.particles.ParticleSystem;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.util.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.util.Window;

public class MapBattle extends Map {

    private Vector2f backgroundPos;
    private Vector2f target_pos;

    private Vector2i backgroundSize;
    private Vector2f scrollAmount;
    private Vector2f halfOffset;

    private BattlePlayer player;

    private float scrollRate;

    private SceneBattle scene;

    private ParticleSystem particles;

    public MapBattle(SceneBattle scene, String bgPath, SceneBattleGrid grid, BattlePlayer player) {
        super();

        this.scene = scene;

        setBackground(new Texture("backgrounds/" + bgPath + ".png"));

        scrollRate = 1.2f;

        Vector2i gridSize = grid.getSize();
        backgroundSize = Vector2i.multiplyVector(Window.getSize(), scrollRate);

        scrollAmount = new Vector2f(Vector2f.divideVectors(Vector2f.subtractVectors(backgroundSize, Window.getSize()), gridSize));

        halfOffset = new Vector2f(Vector2f.divideVector(Vector2f.subtractVectors(backgroundSize, Window.getSize()), 2));

        this.player = player;

        backgroundPos = Vector2f.subtractVectors(Vector2f.multiplyVectors(Vector2f.multiplyVector(player.getIndexPos(), -1), scrollAmount), halfOffset);

        particles = new ParticleSystem("snow", 100, -3, -5, 2, 2, 2, 4);
    }

    @Override
    public void update() {
        target_pos = Vector2f.subtractVectors(Vector2f.multiplyVectors(Vector2f.multiplyVector(player.getIndexPos(), -1), scrollAmount), halfOffset);
        backgroundPos.x += (target_pos.x - backgroundPos.x) * 0.2;
        backgroundPos.y += (target_pos.y - backgroundPos.y) * 0.2;
        particles.update(scene);
    }

    @Override
    public void render(RenderSystem rs) {
        rs.draw(getBackground(), backgroundPos, backgroundSize);
        particles.render(rs);
    }
}
