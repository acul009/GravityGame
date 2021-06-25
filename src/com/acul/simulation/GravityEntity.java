package com.acul.simulation;

import java.security.cert.X509Certificate;

public abstract class GravityEntity extends Entity {

    public static final float gravConstant = 1;
    private float mass;

    public GravityEntity(Vektor2f pos, float size, float mass, String textureName) {
        super(pos, size, textureName);
        this.mass = mass;
    }

    public Vektor2f calculateAccelerationForPos(Vektor2f target) {
        Vektor2f dist = this.getPos().sub(target);
        float totalDist = (float) Math.sqrt(dist.X * dist.X + dist.Y * dist.Y);
        float totalAcceleration = (this.getMass() * gravConstant) / (totalDist * totalDist);
        Vektor2f factor = dist.normalize();
        float accelerationX = (totalAcceleration * factor.X);
        float accelerationY = (totalAcceleration * factor.Y);
        return new Vektor2f(accelerationX, accelerationY);
    }

    public float getMass() {
        return mass;
    }
}
