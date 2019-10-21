package com.mygdx.game.stats;

public class BattleStats {

    private int oMovementSpeed, oMovementLock;
    private int movementSpeed, movementLock;

    public BattleStats(int movementSpeed, int movementLock) {
        this.movementSpeed = movementSpeed;
        this.oMovementSpeed = movementSpeed;

        this.movementLock = movementLock;
        this.oMovementLock = movementLock;
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
