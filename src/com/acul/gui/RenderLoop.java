package com.acul.gui;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Vector;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;


public class RenderLoop {

    private Application app;

    public RenderLoop(Application app) {
        this.app = app;
    }

    public void run() {
        ArrayList<Window> windows = app.getWindows();
        Window win;
        for (int i = windows.size() - 1; i >= 0; i--) {
            win = windows.get(i);
            win.render();
        }
    }
}
