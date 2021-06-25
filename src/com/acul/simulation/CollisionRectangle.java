package com.acul.simulation;

public class CollisionRectangle {

    private Vektor2f topLeft, topRight, bottomRight, bottomLeft;
    private CollisionLine[] lineCache = null;



    public CollisionRectangle(Vektor2f center, Vektor2f size, float rotation) {
        Vektor2f offset = size.scale(0.5f);
        Vektor2f offsetTopRight = offset.rotate(rotation);
        Vektor2f offsetTopLeft = (new Vektor2f(-offset.X, offset.Y)).rotate(rotation);
        topLeft = offsetTopLeft.add(center);
        topRight = offsetTopRight.add(center);
        bottomRight = offsetTopLeft.scale(-1).add(center);
        bottomLeft = offsetTopRight.scale(-1).add(center);
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

    public boolean collidesWith(CollisionRectangle target) {
        CollisionLine[] myLines = this.getCollisionLines();
        CollisionLine[] targetLines = target.getCollisionLines();
        for(int i = 0; i < myLines.length; i++) {
            for (int j = 0; j < targetLines.length; j++) {
                if(myLines[i].collidesWith(targetLines[j])) {
                    return true;
                }
            }
        }
        return false;
    }
}
