package com.acul.simulation;

public abstract class Entity {

    private long posX, posY, sizeX, sizeY, mass;

    public long getSizeY() {
        return sizeY;
    }

    public void setSizeY(long sizeY) {
        this.sizeY = sizeY;
    }

    public long getSizeX() {
        return sizeX;
    }

    public void setSizeX(long sizeX) {
        this.sizeX = sizeX;
    }

    public long getPosX() {
        return posX;
    }

    public void setPosX(long posX) {
        this.posX = posX;
    }

    public long getPosY() {
        return posY;
    }

    public void setPosY(long posY) {
        this.posY = posY;
    }

    public long getMass() {
        return mass;
    }

    public void setMass(long mass) {
        this.mass = mass;
    }

    public synchronized void moveBy(long x, long y) {
        this.setPosX(this.getPosX() + x);
        this.setPosY(this.getPosY() + y);
    }
}
