package com.acul.simulation;

public abstract class GravityEntity extends Entity {

    private static final float gravConstant = 1;
    private float mass;

    public GravityEntity(float posX, float posY, float size, float mass, String textureName) {
        super(posX, posY, size, textureName);
        this.mass = mass;
    }

    public float[] calculateAccelerationForPos(float posX, float posY) {
        float distX = this.getPosX() - posX;
        float distY = this.getPosY() - posY;
        float totalDist = (float) Math.sqrt(distX * distX + distY * distY);
        float totalAcceleration = (this.getMass() * gravConstant) / (totalDist * totalDist);
        float factorX = distX / totalDist;
        float factorY = distY / totalDist;
        float accelerationX = (totalAcceleration * factorX);
        float accelerationY = (totalAcceleration * factorY);
        return new float[]{accelerationX, accelerationY};
    }

    public float getMass() {
        return mass;
    }
}
