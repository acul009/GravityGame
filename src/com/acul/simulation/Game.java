package com.acul.simulation;

import com.acul.gui.Window;

import java.util.HashSet;
import java.util.Vector;

public class Game {

    private final Window window;
    private final Vector<Planet> planets = new Vector<>();
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
        planets.add(new Planet(0,70,50,7,"Terran.png"));
    }

    public Vector<Planet> getPlanets() {
        return planets;
    }

    public Vector<MobileEntity> getMobiles() {
        return mobiles;
    }

    public String[] getAllTextureNames() {
        HashSet<String> textures = new HashSet<>();
        for (Planet p: planets) {
            textures.add(p.getTextureName());
        }
        for (MobileEntity m: mobiles) {
            textures.add(m.getTextureName());
        }
        String[] texArray = new String[textures.size()];
        textures.toArray(texArray);
        return texArray;
    }

    private void addPlayer() {
        player = new Player(0,0);
        mobiles.add(player);
    }

    public void runGameTick() {
        System.out.println("Running Tick");
        applyPlayerControl();
        for (MobileEntity m:mobiles) {
            for (Planet p:planets) {
                p.affect(m);
            }
            m.moveBySpeed();
        }
    }

    private void applyPlayerControl() {
        double[] mousePos = window.getMousePos();
        System.out.println("MouseX: " + mousePos[0] + ", MouseY: "+ mousePos[1]);
    }


}
