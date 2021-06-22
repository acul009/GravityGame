package com.acul.renderer;

import com.acul.gui.InitFailureException;
import com.acul.gui.OrthographicRenderer;
import com.acul.gui.Texture;
import com.acul.gui.Window;
import com.acul.simulation.Game;
import com.acul.simulation.MobileEntity;
import com.acul.simulation.Planet;
import com.acul.simulation.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class SolarSystemRenderer extends OrthographicRenderer {

    private Game game;
    private HashMap<String, Texture> textures = new HashMap<>();
    private float camSize = 10;

    public SolarSystemRenderer(Window win, Game game) {
        super(win);
        this.game = game;
        loadTextures();
    }

    @Override
    public void render() {
        centerCameraOnPlayer();
        super.render();

        renderPlanets();
        renderMobiles();
    }

    private void renderPlanets() {
        Vector<Planet> planets = game.getPlanets();
        for (Planet p : planets) {
            Texture tex = textures.get(p.getTextureName());
            float size = p.getSize();
            float posX = p.getPosX() - size/2;
            float posY = p.getPosY() - size/2;
            tex.draw(posX, posY, size);
        }
    }

    private void renderMobiles() {
        Vector<MobileEntity> mobiles = game.getMobiles();
        for (MobileEntity m : mobiles) {
            Texture tex = textures.get(m.getTextureName());
            float size = m.getSize();
            float posX = m.getPosX() - size/2;
            float posY = m.getPosY() - size/2;
            float rotation = m.getRotation();
            tex.draw(posX, posY, size, 0 , rotation);
        }
    }

    private void loadTextures() {
        String[] textureNames = game.getAllTextureNames();
        for (String texName : textureNames) {
            try {
                textures.put(texName, new Texture(texName));
            } catch (InitFailureException e) {
                e.printStackTrace();
            }
        }
    }

    private void centerCameraOnPlayer() {
        Player p = game.getPlayer();
        double posX = p.getPosX() - this.getCameraWidth() / 2;
        double posY = p.getPosY() - this.getCameraHeight() / 2;
        this.setCameraPos(posX,posY);
    }
}
