package com.acul.simulation;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CollisionRectangle implements CollisionBox {

    private Vektor2f size,center, topLeft, topRight, bottomRight, bottomLeft;
    private CollisionLine[] lineCache = null;



    public CollisionRectangle(Vektor2f center, Vektor2f size, float rotation) {
        this.center = center;
        this.size = size;
        Vektor2f offset = size.scale(0.5f);
        Vektor2f offsetTopRight = offset.rotate(rotation);
        Vektor2f offsetTopLeft = (new Vektor2f(-offset.X, offset.Y)).rotate(rotation);
        topLeft = offsetTopLeft.add(center);
        topRight = offsetTopRight.add(center);
        bottomRight = offsetTopLeft.scale(-1).add(center);
        bottomLeft = offsetTopRight.scale(-1).add(center);
    }

    public CollisionRectangle(Vektor2f topLeft, Vektor2f topRight, Vektor2f bottomRight, Vektor2f bottomLeft) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomRight = bottomRight;
        this.bottomLeft = bottomLeft;
    }

    public Vektor2f getTopLeft() {
        return topLeft;
    }

    public Vektor2f getTopRight() {
        return topRight;
    }

    public Vektor2f getBottomRight() {
        return bottomRight;
    }

    public Vektor2f getBottomLeft() {
        return bottomLeft;
    }

    public CollisionLine[] getCollisionLines() {
        if(lineCache != null) {
            return  lineCache;
        }
        CollisionLine[] lines = new CollisionLine[4];
        lines[0] = new CollisionLine(topLeft, topRight);
        lines[1] = new CollisionLine(topRight, bottomRight);
        lines[2] = new CollisionLine(bottomRight, bottomLeft);
        lines[3] = new CollisionLine(bottomLeft, topRight);
        lineCache = lines;
        return lines;
    }

    @Override
    public boolean collidesWith(CollisionSphere target) {
        return forwardCollision(target);
    }

    public boolean collidesWith(CollisionRectangle target) {
        return forwardCollision(target);
    }

    @Override
    public boolean collidesWith(CollisionLine target) {
        return forwardCollision(target);
    }

    private boolean forwardCollision(CollisionBox target) {
        CollisionLine[] myLines = this.getCollisionLines();
        for (CollisionLine myLine : myLines) {
            if (target.collidesWith(myLine)) {
                return true;
            }
        }
        return false;
    }

    public void move(Vektor2f diff) {
        center = center.add(diff);
        topLeft = topLeft.add(diff);
        topRight = topRight.add(diff);
        bottomRight = bottomRight.add(diff);
        bottomLeft = bottomLeft.add(diff);
    }

    public void rotate() {
        throw new NotImplementedException();
    }
}
