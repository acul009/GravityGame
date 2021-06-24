package com.acul.simulation;

import com.acul.gui.Window;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static org.lwjgl.glfw.GLFW.*;

public class Game {

    private final Window window;
    private final Vector<GravityEntity> planets = new Vector<>();
    private final Vector<MobileEntity> mobiles = new Vector<>();
    private Player player;

    public Game(Window window) {
        this.window = window;
        addPlanets();
        addPlayer();
    }

    public Player getPlayer() {
        return player;
    }

    private void addPlanets() {
        planets.add(new Planet(new Vektor2f(0, 70), 50, 7, "Terran.png"));
    }

    public Vector<GravityEntity> getPlanets() {
        return planets;
    }

    public Vector<MobileEntity> getMobiles() {
        return mobiles;
    }

    public Map<String, Integer> getAllTextureNames() {
        HashMap<String, Integer> textures = new HashMap<>();
        for (GravityEntity p : planets) {
            textures.put(p.getTextureName(), 1);
        }
        for (MobileEntity m : mobiles) {
            textures.put(m.getTextureName(), m.getStateAmount());
        }
        return textures;
    }

    private void addPlayer() {
        player = new Player(new Vektor2f(0, 0));
        mobiles.add(player);
    }

    public void runGameTick() {
        applyPlayerControl();
        for (MobileEntity m : mobiles) {
            m.calculateNextPos(planets);
        }
    }

    private void applyPlayerControl() {
        Vektor2f mousePos = window.getRelativeMousePos();
        player.pointTowards(
                mousePos,
                window.getMouseButtonState(GLFW_MOUSE_BUTTON_LEFT),
                window.getMouseButtonState(GLFW_MOUSE_BUTTON_RIGHT)
        );
    }


}
