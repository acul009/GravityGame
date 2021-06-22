package com.acul.gui;

import com.acul.utils.AssetUtils;
import com.acul.utils.BufferUtils;

import java.awt.image.BufferedImage;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

    private int height, width;
    private int textureID;
    private int frameWidth;

    private static final String shaderLocation = "/textures/";

    public Texture(String name) throws InitFailureException {
        this(name, 1);
    }

    public Texture(String name, int frames) throws InitFailureException {
        BufferedImage img = AssetUtils.loadImage(shaderLocation + name);
        if (img == null) {
            throw new InitFailureException("Texture could not be loaded!");
        }
        height = img.getHeight();
        width = img.getWidth();
        frameWidth = width / frames;
        int[] imageData = new int[height * width];
        img.getRGB(0, 0, width, height, imageData, 0, width);
        IntBuffer buffer = BufferUtils.createIntBuffer(mirrorBytes(imageData));

        textureID = glGenTextures();
        activate();
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

    }

    private int[] mirrorBytes(int[] original) {
        int a, r, g, b;
        int[] converted = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            int pixel = original[i];
            b = (pixel & 0b11111111);
            g = ((pixel >> 8) & 0b11111111);
            r = ((pixel >> 16) & 0b11111111);
            a = ((pixel >> 24) & 0b11111111);
            converted[i] = (r) + (g << 8) + (b << 16) + (a << 24);
        }
        return converted;
    }

    public void activate() {
        glBindTexture(GL_TEXTURE_2D, textureID);
    }

    public void deactivate() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void draw(float posX, float posY, float height) {
        this.draw(posX, posY, height, 0, 0);
    }

    public void draw(float posX, float posY, float height, int frame, float rotation) {
        float texStartX = frame * frameWidth / (float) this.getWidth();
        float texEndX = texStartX + frameWidth / (float) this.getWidth();
        float texStartY = 0;
        float texEndY = 1;

        float width = (height / this.getHeight() * this.getWidth());

        float posX1 = 0;
        float posX2 = posX1;
        float posX3 = posX1 + height;
        float posX4 = posX3;
        float posY1 = 0;
        float posY2 = posY1 + width;
        float posY3 = posY2;
        float posY4 = posY1;

        this.activate();
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glLoadIdentity();
        glTranslatef(posX + width/2, posY +  height/2, 0);
        glRotatef(rotation, 0f, 0f, 1f);
        glTranslatef(-width/2, -height/2, 0);

        glBegin(GL_QUADS);

        glTexCoord2f(texStartX, texStartY);
        glVertex2f(posX1, posY1);
        glTexCoord2f(texStartX, texEndY);
        glVertex2f(posX2, posY2);
        glTexCoord2f(texEndX, texEndY);
        glVertex2f(posX3, posY3);
        glTexCoord2f(texEndX, texStartY);
        glVertex2f(posX4, posY4);

        glEnd();

        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
        this.deactivate();
    }
}
