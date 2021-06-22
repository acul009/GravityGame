package com.acul.gui;

import com.acul.gui.*;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Timer;
import java.util.Vector;

import static java.lang.Thread.sleep;
import static org.lwjgl.glfw.GLFW.*;

public class Application implements Runnable{

    private final AppInitializer init;
    private boolean shouldExit = false;
    ArrayList<Window> windows = new ArrayList<Window>();
    RenderLoop loop;
    public static final int fps = 144;

    public Application(AppInitializer init) {
        this.init = init;
    }

    public Window createWindow(String title) throws InitFailureException {
        Window win = new Window(title);
        windows.add(win);
        return win;
    }

    private void init() throws InitFailureException {
        if (!glfwInit())
            throw new InitFailureException("GLFW Failed to initialize!");
        loop = new RenderLoop(this);
        init.init(this);
    }

    private void start() {
        try {
            init();
        } catch (InitFailureException e) {
            e.printStackTrace();
        }
        while(!shouldExit()) {
            loop.run();
            glfwPollEvents();
            closeWindows();
            try {
                sleep(1000 / fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        exit();
    }

    private boolean shouldExit() {
        return shouldExit || windows.isEmpty();
    }

    public void addWindow(Window win) {
        windows.add(win);
    }

    private void closeWindows() {
        Window win;
        for (int i = windows.size() - 1; i >= 0; i--) {
            win = windows.get(i);
            if (win.shouldClose()) {
                win.close();
                windows.remove(i);
            }
        }
    }

    private void exit() {
        Window win;
        for (int i = windows.size() - 1; i >= 0; i--) {
            win = windows.get(i);
            if (win.shouldClose()) {
                win.close();
            }
        }
        glfwTerminate();
        System.exit(0);
    }

    public void shutdown() {
        shouldExit = true;
    }

    public ArrayList<Window> getWindows() {
        return windows;
    }

    @Override
    public void run() {
        this.start();
    }
}
