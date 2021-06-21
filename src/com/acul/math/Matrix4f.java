package com.acul.math;

import com.acul.utils.BufferUtils;

import java.nio.FloatBuffer;
import java.util.Arrays;

public class Matrix4f extends Matrixf{

    private static final int size = 4;

    public Matrix4f() {
        super(4);
    }

    public static Matrix4f identity() {
        return (Matrix4f) Matrixf.identity(size);
    }

    public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far) {
        Matrix4f ortho = identity();
        ortho.setValue(0,0,2f / (right-left));
        ortho.setValue(1,1,2f / (top-bottom));
        ortho.setValue(2,2,2f / (near-far));

        ortho.setValue(3,0,(right+left) / (right-left));
        ortho.setValue(3,1,(top+bottom) / (top-bottom));
        ortho.setValue(3,2,(near+far) / (near-far));

        return ortho;
    }

    public static Matrix4f translate(Vector3f vector) {
        Matrix4f trans = identity();
        trans.setValue(3,0, vector.getX());
        trans.setValue(3,1, vector.getY());
        trans.setValue(3,2, vector.getZ());
        return trans;
    }

    public static Matrix4f rotate(float degrees) {
        Matrix4f rotated = identity();
        float radians = (float) Math.toRadians(degrees);
        float cos = (float) Math.cos(radians);
        float sin = (float) Math.sin(radians);

        rotated.setValue(0,0,cos);
        rotated.setValue(0,1,sin);
        rotated.setValue(1,0,-sin);
        rotated.setValue(1,1,cos);

        return rotated;
    }

    public Matrix4f multiply(Matrix4f matrix) {
        Matrix4f mult = new Matrix4f();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                float rowSum = 0;
                for(int k = 0; k < size; k++) {
                    rowSum += matrix.getValue(k,j) * this.getValue(i, k);
                }
                mult.setValue(j,i,rowSum);
            }
        }
        return mult;
    }

}
