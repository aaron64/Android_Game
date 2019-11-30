package com.mygdx.game.stats;

public class BattleStats {

    public static BattleStats BASE_STATS_PLAYER = new BattleStats(6, 10);
    public static BattleStats BASE_STATS_ARROW_TURRET = new BattleStats(6, 10);
    public static BattleStats BASE_STATS_MAGE = new BattleStats(6, 80);
    public static BattleStats BASE_STATS_SWORDSMAN = new BattleStats(6, 60);

    private int oMovementSpeed, oMovementLock;
    private int movementSpeed, movementLock;

    public BattleStats(int movementSpeed, int movementLock) {
        this.movementSpeed = movementSpeed;
        this.oMovementSpeed = movementSpeed;

        this.movementLock = movementLock;
        this.oMovementLock = movementLock;
    }

    public BattleStats(BattleStats battleStats) {
        this(battleStats.getMovementSpeed(), battleStats.getMovementLock());
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public int getMovementLock() {
        return movementLock;
    }

    public void affectMovementSpeed(float f) {
        this.movementSpeed *= f;
    }

    public void affectMovementLock(float f) {
        this.movementLock *= f;
    }

    public void resetMovementSpeed() {
        movementSpeed = oMovementSpeed;
    }

    public void resetMovementLock() {
        movementLock = oMovementLock;
    }
}
