package com.acul.simulation;

public abstract class Entity {

    private Vektor2f pos;
    private float posX, posY, size;
    private String textureName;

    public Entity(Vektor2f pos, float size, String textureName) {
        this.pos = pos;
        this.size = size;
        this.textureName = textureName;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public synchronized void moveBy(Vektor2f diff) {
        this.setPos(this.getPos().add(diff));
    }

    public String getTextureName() {
        return textureName;
    }

    public Vektor2f getPos() {
        return pos;
    }

    public void setPos(Vektor2f pos) {
        this.pos = pos;
    }
}
