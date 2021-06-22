package com.acul.simulation;

public abstract class MobileEntity extends Entity {

    private float speedX, speedY;

    public MobileEntity(float posX, float posY, float size, float mass, String textureName) {
        super(posX, posY, size, mass, textureName);
    }

    public void moveBySpeed() {
        this.moveBy(this.speedX, this.speedY);
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public synchronized void accelerateBy(float x, float y) {
        this.setSpeedX(this.getSpeedX() + x);
        this.setSpeedY(this.getSpeedY() + y);
    }
}
