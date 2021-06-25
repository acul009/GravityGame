package com.acul.simulation;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    public boolean collidesWith(CollisionLine target) {
        return target.collidesWith(this);
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

    public float getOffsetAt(float posX) {
        float centerX = pos.X - this.pos.X;
        float rootMe = radius - centerX * centerX;
        if(rootMe < 0) {
            return Float.NaN;
        }
        return (float) Math.sqrt(rootMe);
    }

    public boolean isInSphere(Vektor2f pos) {
        float offset = getOffsetAt(pos.X);
        return pos.Y < this.pos.Y + offset && pos.Y > this.pos.Y - offset;
    }
}
