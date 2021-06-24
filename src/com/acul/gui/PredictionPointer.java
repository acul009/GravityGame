package com.acul.gui;

import com.acul.simulation.Vektor2f;

import static org.lwjgl.opengl.GL11.*;

public class PredictionPointer {

    private static final float widthFactor = 1 / 1.25f / 2;

    public static void drawPointer(Vektor2f pos, float height, float rotation) {
        float offsetX = widthFactor * height;

        glLoadIdentity();
        System.out.println("X: " + pos.X + ", Y: " + pos.Y);
        //glRotatef(rotation, 0f, 0f, 1f);
        glTranslatef(pos.X , pos.Y , 0);

        glBegin(GL_TRIANGLES);
        glVertex2f(height/2, 0);
        glVertex2f(-offsetX, -height/2);
        glVertex2f(offsetX, -height/2);
        glEnd();
    }

}
