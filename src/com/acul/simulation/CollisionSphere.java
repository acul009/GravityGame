package com.acul.simulation;

public class CollisionSphere {

    private Vektor2f pos;
    private float radius;

    public CollisionSphere(Vektor2f pos, float radius) {
        this.pos = pos;
        this.radius = radius;
    }

    public boolean collidesWith(CollisionSphere target) {
        float dist = target.getPos().sub(pos).getLength();
        return radius + target.getRadius() <= dist;
    }

    public Vektor2f getPos() {
        return pos;
    }

    public void setPos(Vektor2f pos) {
        this.pos = pos;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
