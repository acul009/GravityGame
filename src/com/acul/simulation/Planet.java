package com.acul.simulation;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Planet extends GravityEntity{

    public Planet(Vektor2f pos, float size, float mass, String textureName) {
        super(pos, size, mass, textureName);
    }
}
