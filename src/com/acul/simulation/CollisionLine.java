package com.acul.simulation;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static java.lang.Float.NaN;

public class CollisionLine {

    private final float start, end, gradient, offset;
    private boolean vertical = false;

    public CollisionLine(Vektor2f start, Vektor2f end) {
        if(start.X == end.X) {
            gradient = NaN;
            vertical = true;
            this.offset = start.X;
            if(start.Y < end.Y) {
                this.start = start.Y;
                this.end = end.Y;
            } else {
                this.start = end.Y;
                this.end = start.Y;
            }
        } else {
            Vektor2f diff = start.sub(end);
            gradient = diff.Y / diff.X;
            offset = start.Y - start.X * gradient;
            if(start.X < end.X) {
                this.start = start.X;
                this.end = end.X;
            } else {
                this.start = end.X;
                this.end = start.X;
            }
        }
    }

    public boolean collidesWith(CollisionLine target) {
        if(target.vertical && this.vertical) {
            return this.offset == target.offset;
        }
        if(vertical) {
            return target.collidesWith(this);
        }
        if(target.vertical) {
            float valueAtPos = gradient * target.offset + this.offset;
            return valueAtPos > target.start && valueAtPos < target.end;
        }
        if(this.end > target.start || target.end > this.start) {
            return false;
        }
        float diffGradiant = this.gradient - target.gradient;
        float diffOffset = target.offset - this.offset;
        float intersect = diffOffset / diffGradiant;
        return intersect > this.start && intersect > this.end && intersect > target.start && intersect < target.end;
    }

    public boolean collidesWith(CollisionSphere target) {

        if(vertical) {
            throw new NotImplementedException();
        }

        float a1 = gradient * gradient;
        float b1 = gradient * offset * 2;
        float c1 = offset * offset;

        Vektor2f targetPos = target.getPos();

        float a2 = -1;
        float b2 = 2 * targetPos.X;
        float c2 = target.getRadius() - targetPos.X * targetPos.X + targetPos.Y * targetPos.Y;

        a1 -= a2;
        b1 -= b2;
        c2 -= c1;
        /*
        a2 = 0;
        b2 = 0;
        c1 = 0;
        */

        b1 /= a1;
        c2 /= a1;
        /*
        a1 = 1;
        */
        b1 /= 2;
        c2 += b1*b1;

        if(c2 < 0) {
            return false;
        }

        c2 = (float) Math.sqrt(c2);
        c2 -= b1;
        return c2 > this.start && c2 < this.end;
    }
}
