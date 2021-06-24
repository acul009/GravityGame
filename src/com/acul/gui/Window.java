package com.acul.gui;

import com.acul.simulation.Vektor2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

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

    public Vektor2f getMousePos() {
        DoubleBuffer bufferX = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer bufferY = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(windowId, bufferX, bufferY);

        return new Vektor2f((float) bufferX.get(0), (float) bufferY.get(0));
    }

    public Vektor2f getRelativeMousePos() {
        Vektor2f mousePos = getMousePos();
        int[] windowSize = getWindowSize();
        return new Vektor2f(mousePos.X - (float)windowSize[0]/2, mousePos.Y - (float)windowSize[1]/2);
    }

    public int[] getWindowSize() {
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(windowId, width, height);
        return new int[]{width.get(0), height.get(0)};
    }

    public boolean getMouseButtonState(int buttonId) {
        return glfwGetMouseButton(windowId, buttonId) == 1;
    }
}
