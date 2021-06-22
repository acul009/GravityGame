package com.acul.simulation;

public abstract class MobileEntity extends Entity {

    private float speedX, speedY, factorX, factorY;

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

    public void setFactors(float factorX, float factorY) {
        this.factorX = factorX;
        this.factorY = factorY;
    }

    public float getRotation() {
        float rotation = -(float) Math.toDegrees(Math.atan(factorX / factorY));
        if(factorY < 0) {
            rotation += 180;
        }
        return rotation;
    }
}
