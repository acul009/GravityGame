package com.acul.simulation;

public abstract class MobileEntity extends Entity {

    private long speedX, speedY;

    void moveBySpeed() {
        this.moveBy(this.speedX, this.speedY);
    }

    public long getSpeedX() {
        return speedX;
    }

    public void setSpeedX(long speedX) {
        this.speedX = speedX;
    }

    public long getSpeedY() {
        return speedY;
    }

    public void setSpeedY(long speedY) {
        this.speedY = speedY;
    }

    public synchronized void accelerateBy(long x, long y) {
        this.setSpeedX(this.getSpeedX() + x);
        this.setSpeedY(this.getSpeedX() + y);
    }
}
