package com.acul.gui;

import static org.lwjgl.opengl.GL11.*;

public abstract class OrthographicRenderer extends Renderer{

    private double posX,posY,width,height, ratio;

    public OrthographicRenderer(Window win) {
        super(win);
        setAspectRation(16d/9d);
        setCameraHeight(100);
        this.setCameraPos(0,0);
    }

    protected void setCameraPos(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    protected void setCameraHeight(double height) {
        this.height = height;
        this.width = height*ratio;
    }

    protected double getCameraHeight() {
        return height;
    }

    protected double getCameraWidth() {
        return width;
    }

    public void setAspectRation(double ratio) {
        this.ratio = ratio;
        this.setCameraHeight(height);
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
