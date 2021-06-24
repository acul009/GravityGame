package com.acul.simulation;

public class Vektor2f {

    public float X;
    public float Y;

    public Vektor2f() {
        this(0,0);
    }

    public Vektor2f(float x, float y) {
        X = x;
        Y = y;
    }

    public Vektor2f add(Vektor2f toAdd) {
        return new Vektor2f(this.X + toAdd.X, this.Y + toAdd.Y);
    }

    public Vektor2f sub(Vektor2f sub) {
        return new Vektor2f(this.X-sub.X, this.Y-sub.Y);
    }

    public float getRotation() {
        float rotation = -(float) Math.toDegrees(Math.atan(X / Y));
        if (Y >= 0) {
            rotation -= 180;
        }
        return rotation;
    }

    public Vektor2f normalize() {
        float length = (float) Math.sqrt(X * X + Y * Y);
        return new Vektor2f(X/length, Y/length);
    }

    public Vektor2f scale(float scalar) {
        return new Vektor2f(X*scalar, Y*scalar);
    }

    public float getLength() {
        return (float) Math.sqrt(X*X + Y*Y);
    }
}
