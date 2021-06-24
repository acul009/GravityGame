package com.acul.simulation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public abstract class MobileEntity extends Entity {

    private Vektor2f speed = new Vektor2f(), factor = new Vektor2f();
    private int state = 0;
    private int predictionTicks = 0;
    private LinkedList<Vektor2f> posPrediction = null;
    private LinkedList<Vektor2f> speedPrediction = null;

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
        if (posPrediction == null || posPrediction.size() == 0) {
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
        posPrediction = new LinkedList<>();
        speedPrediction = new LinkedList<>();
    }

    private void calculatePrediction(Vector<GravityEntity> planets) {
        if (predictionTicks < 1) return;
        if (posPrediction == null) {
            flushPrediction();
        }
        if (posPrediction.size() < predictionTicks) {
            Vektor2f pos, speed;
            if(posPrediction.size() == 0) {
                pos = getPos();
                speed = getSpeed();
            } else {
                pos = posPrediction.getLast();
                speed = speedPrediction.getLast();
            }
            while (posPrediction.size() < predictionTicks) {
                Vektor2f acc = new Vektor2f();
                for (GravityEntity p: planets) {
                    speed = speed.add(p.calculateAccelerationForPos(pos));
                }
                pos = pos.add(speed);
                posPrediction.add(pos);
                speedPrediction.add(speed);
            }
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

    public int getPredictionTicks() {
        return predictionTicks;
    }

    public void setPredictionTicks(int predictionTicks) {
        if (predictionTicks > 0 && posPrediction == null) {
            flushPrediction();
        }
        this.predictionTicks = predictionTicks;
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

    public LinkedList<Vektor2f> getPosPrediction() {
        return posPrediction;
    }

    public LinkedList<Vektor2f> getSpeedPrediction() {
        return speedPrediction;
    }
}
