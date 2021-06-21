package com.acul.utils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class BufferUtils {

    private BufferUtils() {

    }

    public static ByteBuffer createByteBuffer(byte[] array) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
        buffer.put(array).flip();
        return buffer;
    }

    public static FloatBuffer createFloatBuffer(float[] array) {
        FloatBuffer buffer = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        buffer.put(array).flip();
        return buffer;
    }

    public static IntBuffer createIntBuffer(int[] array) {
        IntBuffer buffer = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
        buffer.put(array).flip();
        return buffer;
    }
}
