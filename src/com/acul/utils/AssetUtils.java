package com.acul.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class AssetUtils {

    private static final String assetLocation = "/assets";

    private static String getAssetPath(String filepath) {
        return assetLocation + filepath;
    }

    public static byte[] load(String filepath) {
        try {
            filepath = getAssetPath(filepath);
            byte[] data;
            InputStream stream = AssetUtils.class.getResourceAsStream(filepath);
            if (stream == null) {
                return null;
            }
            data = new byte[stream.available()];
            stream.read(data);
            return data;
        } catch (IOException e) {
            return null;
        }
    }

    public static BufferedImage loadImage(String filepath) {
        try {
            filepath = getAssetPath(filepath);
            InputStream stream = AssetUtils.class.getResourceAsStream(filepath);
            if (stream == null) {
                return null;
            }
            BufferedImage img = ImageIO.read(stream);
            return img;
        } catch (IOException e) {
            return null;
        }
    }

}
