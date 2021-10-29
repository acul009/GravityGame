package com.acul.simulation;

public interface CollisionBox {

    boolean collidesWith(CollisionSphere target);

    boolean collidesWith(CollisionRectangle target);

    boolean collidesWith(CollisionLine target);

}
