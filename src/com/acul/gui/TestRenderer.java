package com.acul.gui;

import static org.lwjgl.opengl.GL11.*;

public class TestRenderer extends Renderer {

    Window win;
    Texture tex;
    double width,height;

    public TestRenderer(Window win) {
        super(win);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        width = 100d;
        height = width / 16 * 9;
        glOrtho(0, width, height, 0, 10, -10);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_ALWAYS);

        try {
            tex = new Texture("img.png");
        } catch (InitFailureException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void render() {
        super.render();
        glClearColor(0, 0, 0, 1f);
        //tex.draw(10, 10, 20, 0, 0);
        tex.draw(10, 10, 20, 0, 90);
        /*
        tex.activate();
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glBegin(GL_QUADS);
        float startX = 0;
        float endX = startX + tex.getWidth();
        float startY = 0;
        float endY = startY + tex.getHeight();
        float textureX = startX / tex.getWidth();
        float textureY = startY / tex.getHeight();
        float textureXEnd = endX / tex.getWidth();
        float textureYEnd = endY / tex.getHeight();
        float quadX = 50;
        float quadY = 50;
        float quadWidth = 50;
        float quadHeight = 50;
        glTexCoord2f(textureX, textureY);
        glVertex2f(quadX, quadY);
        glTexCoord2f(textureX, textureYEnd);
        glVertex2f(quadX, quadY + quadHeight);
        glTexCoord2f(textureXEnd, textureYEnd);
        glVertex2f(quadX + quadWidth, quadY + quadHeight);
        glTexCoord2f(textureXEnd, textureY);
        glVertex2f(quadX + quadWidth, quadY );
        glEnd();
        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
        tex.deactivate();
        */
    }
}
