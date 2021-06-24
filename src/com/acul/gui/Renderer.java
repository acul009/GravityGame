package com.acul.gui;

public abstract class Renderer {

    private Window win;

    public Renderer(Window win) {
        this.win = win;
        win.setRenderer(this);
    }

    public abstract void render();

    public Window getWindow() {
        return win;
    }
}
