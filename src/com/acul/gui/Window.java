package com.acul.gui;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    private long windowId;
    private Renderer renderer;


    public Window(String title) throws InitFailureException {
        windowId = glfwCreateWindow(1280,720, title, NULL, NULL);
        if (windowId == NULL) {
            throw new InitFailureException("Unable to create the Window \"" + title + "\"");
        }
    }


    public void setActive() {
        glfwMakeContextCurrent(windowId);
        GL.createCapabilities();

    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(windowId);
    }

    public void render() {
        setActive();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        renderer.render();
        glfwSwapBuffers(windowId);
    }

    public void close() {
        glfwFreeCallbacks(windowId);
        glfwDestroyWindow(windowId);
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
        this.setActive();
    }

    public double[] getMousePos() {
        DoubleBuffer bufferX = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer bufferY = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(windowId, bufferX, bufferY);

        return new double[]{bufferX.get(0), bufferY.get(0)};
    }
}
