package com.acul.math;

public class Vector3f extends Vectorf{

    private float x,y,z;

    public Vector3f() {
        super(3);
    }

    public Vector3f(float x, float y, float z) {
        super(new float[]{x, y, z});
    }

    public float getX() {
        return super.getComponent(0);
    }

    public float getY() {
        return super.getComponent(1);
    }

    public float getZ() {
        return super.getComponent(2);
    }
}
