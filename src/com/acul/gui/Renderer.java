package com.acul.gui;

public abstract class Renderer {

    Window win;

    public Renderer(Window win) {
        this.win = win;
        win.setRenderer(this);
    }

    public void render() {

    }
}
