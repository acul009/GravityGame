package com.acul.gui;

import static org.lwjgl.opengl.GL11.*;

public abstract class OrthographicRenderer extends Renderer{

    private double posX,posY,width,height;

    public OrthographicRenderer(Window win) {
        super(win);
        width = 100d;
        height = width / 16 * 9;
        this.setCameraPos(0,0);
    }

    protected void setCameraPos(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void render() {

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glOrtho(posX, posX + width, posY + height, posY, 10, -10);

        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_ALWAYS);

        glClearColor(0, 0, 0, 1f);
    }
}
