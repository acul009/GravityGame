package com.acul.gui;

import static org.lwjgl.opengl.GL11.*;

public class TestRenderer extends Renderer{

    Window win;

    public TestRenderer(Window win) {
        super(win);
        try {
            Texture tex = new Texture("img.png");
        } catch (InitFailureException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        super.render();
        glClearColor(1f,0,0,1f);
    }
}
