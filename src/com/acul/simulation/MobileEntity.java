package com.acul.simulation;

import java.util.LinkedList;
import java.util.Vector;

public abstract class MobileEntity extends Entity {

    private Vektor2f speed = new Vektor2f(), factor = new Vektor2f();
    private int state = 0;
    private int maxPredictionTicks = 0;
    private int minPredictionTicks = 100;
    private LinkedList<Vektor2f> posPrediction = null;
    private LinkedList<Vektor2f> speedPrediction = null;
    private boolean flushPrediction = false;
    private Vektor2f[] posPredictionArray = new Vektor2f[0], speedPredictionArray = new Vektor2f[0];

    public MobileEntity(Vektor2f pos, float size, String textureName) {
        super(pos, size, textureName);
    }

    public synchronized void accelerateBy(Vektor2f acc) {
        this.setSpeed(this.getSpeed().add(acc));
        if (posPrediction != null) {
            flushPrediction();
        }
    }

    public float getRotation() {
        return factor.getRotation();
    }

    public void calculateNextPos(Vector<GravityEntity> planets) {
        if (posPrediction == null || posPrediction.size() == 0 || flushPrediction) {
            for (int i = 0; i < planets.size(); i++) {
                Vektor2f acc = planets.get(i).calculateAccelerationForPos(this.getPos());
                this.accelerateBy(acc);
            }
            this.moveBy(this.getSpeed());
        } else {
            setPos(posPrediction.remove());
            setSpeed(speedPrediction.remove());
        }
        calculatePrediction(planets);
    }

    protected void flushPrediction() {
        flushPrediction = true;
    }

    private void calculatePrediction(Vector<GravityEntity> planets) {
        if (maxPredictionTicks < 1) return;
        LinkedList<Vektor2f> posPrediction, speedPrediction;
        if (this.posPrediction == null || flushPrediction) {
            flushPrediction = false;
            posPrediction = new LinkedList<>();
            speedPrediction = new LinkedList<>();
        } else {
            posPrediction = this.posPrediction;
            speedPrediction = this.speedPrediction;
        }
        if (posPrediction.size() < maxPredictionTicks) {
            Vektor2f pos, speed;
            if (posPrediction.size() == 0) {
                pos = getPos();
                speed = getSpeed();
            } else {
                pos = posPrediction.getLast();
                speed = speedPrediction.getLast();
            }
            int i = 0;
            while (posPrediction.size() < maxPredictionTicks && (posPrediction.size() < minPredictionTicks || i++ < 5)) {
                Vektor2f acc = new Vektor2f();
                for (GravityEntity p : planets) {
                    speed = speed.add(p.calculateAccelerationForPos(pos));
                }
                pos = pos.add(speed);
                posPrediction.add(pos);
                speedPrediction.add(speed);
            }
            this.posPrediction = posPrediction;
            this.speedPrediction = speedPrediction;
            posPredictionArray = new Vektor2f[posPrediction.size()];
            posPredictionArray = posPrediction.toArray(posPredictionArray);
            speedPredictionArray = new Vektor2f[posPrediction.size()];
            speedPredictionArray = posPrediction.toArray(speedPredictionArray);
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStateAmount() {
        return 1;
    }

    public int getMaxPredictionTicks() {
        return maxPredictionTicks;
    }

    public void setMaxPredictionTicks(int predictionTicks) {
        if (predictionTicks > 0 && posPrediction == null) {
            flushPrediction();
        }
        this.maxPredictionTicks = predictionTicks;
    }

    public Vektor2f getSpeed() {
        return speed;
    }

    public void setSpeed(Vektor2f speed) {
        this.speed = speed;
    }

    public Vektor2f getFactor() {
        return factor;
    }

    public void setFactor(Vektor2f factor) {
        this.factor = factor;
    }

    public Vektor2f[] getPosPrediction() {
        return posPredictionArray;
    }

    public Vektor2f[] getSpeedPrediction() {
        return speedPredictionArray;
    }

    public int getMinPredictionTicks() {
        return minPredictionTicks;
    }

    public void setMinPredictionTicks(int minPredictionTicks) {
        this.minPredictionTicks = minPredictionTicks;
    }
}
