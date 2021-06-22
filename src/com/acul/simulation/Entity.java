package com.acul.simulation;

public abstract class Entity {

    private float posX, posY, size, mass;
    private String textureName;

    public Entity(float posX, float posY, float size, float mass, String textureName) {
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.mass = mass;
        this.textureName = textureName;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public synchronized void moveBy(float x, float y) {
        this.setPosX(this.getPosX() + x);
        this.setPosY(this.getPosY() + y);
    }

    public String getTextureName() {
        return textureName;
    }
}
