package com.acul.simulation;

public abstract class GravityEntity extends Entity {

    private static final long gravConstant = 1;

    public void affect(MobileEntity ent) {
        long distX = ent.getPosX() - this.getPosX();
        long distY = ent.getPosY() - this.getPosY();
        long totalDist = (long) Math.sqrt(distX * distX + distY * distY);
        long totalAcceleration = (this.getMass() * gravConstant) / (totalDist * totalDist);
        float factorX = (float) distX / (float) totalDist;
        float factorY = (float) distY / (float) totalDist;
        long accelerationX = (long) (totalAcceleration * factorX);
        long accelerationY = (long) (totalAcceleration * factorY);
        ent.accelerateBy(accelerationX, accelerationY);
    }

}
