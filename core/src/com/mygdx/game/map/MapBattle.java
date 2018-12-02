package com.mygdx.game.map;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.battle.entities.BattlePlayer;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.RenderSystem;
import com.mygdx.game.util.Window;

public class MapBattle extends Map {

    private Vector2 pos;
    private Vector2 target_pos;

    private Vector2 backgroundSize;
    private Vector2 scrollAmount;
    private Vector2 halfOffset;

    private BattlePlayer player;

    private float scrollRate;

    public MapBattle(String bgPath, SceneBattleGrid grid, BattlePlayer player) {
        super(bgPath);
        scrollRate = 1.2f;

        Vector2
        gridSize = grid.getSize();
        backgroundSize = MathUtil.multiplyVec(Window.getSize(), scrollRate);

        scrollAmount = new Vector2(MathUtil.divideVec(MathUtil.subVec(backgroundSize, Window.getSize()), gridSize));

        halfOffset = new Vector2(MathUtil.divideVec(MathUtil.subVec(backgroundSize, Window.getSize()), 2));

        this.player = player;

        pos = MathUtil.subVec(MathUtil.multiplyVec(MathUtil.multiplyVec(player.getIndexPos(), -1), scrollAmount), halfOffset);
    }

    @Override
    public void update() {
        target_pos = MathUtil.subVec(MathUtil.multiplyVec(MathUtil.multiplyVec(player.getIndexPos(), -1), scrollAmount), halfOffset);
        pos.x += (target_pos.x - pos.x) * 0.2;
        pos.y += (target_pos.y - pos.y) * 0.2;
    }

    @Override
    public void render(RenderSystem rs) {
        rs.draw(getBackground(), pos, backgroundSize);
    }
}
