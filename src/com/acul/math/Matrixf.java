package com.acul.math;

import com.acul.utils.BufferUtils;

import java.nio.FloatBuffer;
import java.util.Arrays;

public class Matrixf {

    private float[] matrix;
    private int size;

    public Matrixf(int size) {
        this.initMatrix(size);
    }

    private void initMatrix(int size) {
        matrix = new float[size*size];
        Arrays.fill(matrix, 0);
    }

    public int getSize() {
        return size;
    }

    public float getValue(int row, int col) {
        return matrix[size*row + col];
    }

    public void setValue(int row, int col, float value) {
        matrix[size*row + col] = value;
    }

    public static Matrixf identity(int size) {
        Matrixf identity = new Matrixf(size);
        for(int i = 0; i < size; i++) {
            identity.setValue(i,i,1f);
        }
        return identity;
    }

    public FloatBuffer toBuffer() {
        return BufferUtils.createFloatBuffer(this.matrix);
    }

}
