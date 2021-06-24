package com.acul.renderer;

import com.acul.gui.*;
import com.acul.simulation.*;

import java.util.*;

public class SolarSystemRenderer extends OrthographicRenderer {

    private Game game;
    private HashMap<String, Texture> textures = new HashMap<>();
    private static final float zoomOutFactor = 1.2f;
    private static final float zoomInFactor = 1 / zoomOutFactor;


    public SolarSystemRenderer(Window win, Game game) {
        super(win);
        this.game = game;
        loadTextures();
    }

    @Override
    public void render() {
        handleZoom();
        centerCameraOnPlayer();
        super.render();

        renderPlayerPredictions();
        renderPlanets();
        renderMobiles();
    }

    private void renderPlanets() {
        Vector<GravityEntity> planets = game.getPlanets();
        for (GravityEntity p : planets) {
            Texture tex = textures.get(p.getTextureName());
            float size = p.getSize();
            Vektor2f pos = p.getPos().sub(new Vektor2f(size / 2, size / 2));
            tex.draw(pos.X, pos.Y, size);
        }
    }

    private void renderMobiles() {
        Vector<MobileEntity> mobiles = game.getMobiles();
        for (MobileEntity m : mobiles) {
            Texture tex = textures.get(m.getTextureName());
            float size = m.getSize();
            Vektor2f pos = m.getPos().sub(new Vektor2f(size / 2, size / 2));
            float rotation = m.getRotation();
            tex.draw(pos.X, pos.Y, size, m.getState(), rotation);
        }
    }

    private void renderPlayerPredictions() {
        Player p = game.getPlayer();
        Vektor2f[] posPredictions =  p.getPosPrediction();
        Vektor2f[] speedPredictions = p.getSpeedPrediction();
        for (int i = 0; i < posPredictions.length - 1; i += 20) {
            PredictionPointer.drawPointer(posPredictions[i], 2, posPredictions[i].sub(posPredictions[i+1]).getRotation());
        }
    }

    private void loadTextures() {
        Map<String, Integer> textureNames = game.getAllTextureNames();
        for (String texName : textureNames.keySet()) {
            try {
                textures.put(texName, new Texture(texName, textureNames.get(texName)));
            } catch (InitFailureException e) {
                e.printStackTrace();
            }
        }
    }

    private void centerCameraOnPlayer() {
        Player p = game.getPlayer();
        Vektor2f cameraOffset = new Vektor2f((float) this.getCameraWidth() / 2, (float) this.getCameraHeight() / 2);
        Vektor2f pos = p.getPos().sub(cameraOffset);
        this.setCameraPos(pos.X, pos.Y);
    }

    private void handleZoom() {
        int zoomChange = getWindow().getScrollDiff();
        changeZoom(zoomChange);
    }

    public void changeZoom(int amount) {
        if(amount > 0) {
            increaseZoom(amount);
        } else if(amount < 0) {
            decreaseZoom(-amount);
        }
    }

    private void increaseZoom(int amount) {
        setCameraHeight(getCameraHeight() * Math.pow(zoomInFactor, amount));
    }

    private void decreaseZoom(int amount) {
        setCameraHeight(getCameraHeight() * Math.pow(zoomOutFactor, amount));
    }
}
