package com.acul.simulation;

public abstract class GravityEntity extends Entity {

    private static final float gravConstant = 1;

    public GravityEntity(float posX, float posY, float size, float mass, String textureName) {
        super(posX, posY, size, mass, textureName);
    }

    public void affect(MobileEntity ent) {
        float distX =  this.getPosX() - ent.getPosX();
        float distY = this.getPosY() - ent.getPosY();
        float totalDist = (float) Math.sqrt(distX * distX + distY * distY);
        float totalAcceleration = (this.getMass() * gravConstant) / (totalDist * totalDist);
        float factorX = distX /  totalDist;
        float factorY =  distY /  totalDist;
        float accelerationX =  (totalAcceleration * factorX);
        float accelerationY =  (totalAcceleration * factorY);
        ent.accelerateBy(accelerationX, accelerationY);
    }

}
