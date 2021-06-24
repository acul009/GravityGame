package com.acul.gui;

import com.acul.simulation.Vektor2f;

import static org.lwjgl.opengl.GL11.*;

public class PredictionPointer {

    private static final float widthFactor = 1 / 1.25f / 2;

    public static void drawPointer(Vektor2f pos, float height, float rotation) {
        float offsetX = widthFactor * height;

        glLoadIdentity();
        glTranslatef(pos.X , pos.Y , 0);
        glRotatef(rotation, 0f, 0f, 1f);

        glColor3f(0,1,0);
        glBegin(GL_TRIANGLES);
        glVertex2f(0, height/2);
        glVertex2f(-offsetX, -height/2);
        glVertex2f(offsetX, -height/2);
        glEnd();
        glColor3f(1,1,1);
    }

}
