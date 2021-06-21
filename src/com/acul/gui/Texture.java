package com.acul.gui;

import com.acul.utils.AssetUtils;
import com.acul.utils.BufferUtils;

import java.awt.image.BufferedImage;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

    private int height, width;
    private int textureID;

    private static final String shaderLocation = "/textures/";

    public Texture(String name) throws InitFailureException {
        BufferedImage img = AssetUtils.loadImage(shaderLocation + name);
        if(img == null) {
            throw new InitFailureException("Texture could not be loaded!");
        }
        height = img.getHeight();
        width = img.getWidth();
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
            a = (pixel & 0b11111111);
            r = ((pixel >> 8) & 0b11111111);
            g = ((pixel >> 16) & 0b11111111);
            b = ((pixel >> 24) & 0b11111111);
            converted[i] = (b) + (g << 8) + (r << 16) + (a << 24);
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
}
