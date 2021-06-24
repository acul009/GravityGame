package com.acul.gui;

import org.lwjgl.glfw.GLFWScrollCallback;

public class Scrolldetector extends GLFWScrollCallback {

    private int scrollDiff = 0;

    @Override
    public String getSignature() {
        return super.getSignature();
    }

    @Override
    public void callback(long args) {
        super.callback(args);
    }

    @Override
    public void invoke(long l, double v, double v1) {
        scrollDiff += v1;
    }

    @Override
    public void close() {
        super.close();
    }

    public int getScrollDiff() {
        int ret = scrollDiff;
        scrollDiff = 0;
        return ret;
    }
}
