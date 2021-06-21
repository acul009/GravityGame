package com.acul.math;

public class Vectorf {

    private float[] components;

    public Vectorf(int size) {
        components = new float[size];
    }

    public Vectorf(float[] values) {
        components = values;
    }

    public float getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, float value) {
        components[index] = value;
    }
}
