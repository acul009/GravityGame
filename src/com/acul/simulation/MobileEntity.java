package com.acul.simulation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public abstract class MobileEntity extends Entity {

    private float speedX, speedY, factorX, factorY, speedPredictionX, speedPredictionY;
    private int state = 0;
    private int predictionTicks = 0;
    private LinkedList<float[]> posPrediction = null;

    public MobileEntity(float posX, float posY, float size, String textureName) {
        super(posX, posY, size, textureName);
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public synchronized void accelerateBy(float x, float y) {
        this.setSpeedX(this.getSpeedX() + x);
        this.setSpeedY(this.getSpeedY() + y);
        if (posPrediction != null) {
            flushPrediction();
        }
    }

    public void setFactors(float factorX, float factorY) {
        this.factorX = factorX;
        this.factorY = factorY;
    }

    public float getRotation() {
        float rotation = -(float) Math.toDegrees(Math.atan(factorX / factorY));
        if (factorY >= 0) {
            rotation -= 180;
        }
        return rotation;
    }

    public void calculateNextPos(Vector<GravityEntity> planets) {
        if (posPrediction == null || posPrediction.size() == 0) {
            for (int i = 0; i < planets.size(); i++) {
                float[] acc = planets.get(i).calculateAccelerationForPos(this.getPosX(), this.getPosY());
                this.accelerateBy(acc[0], acc[1]);
            }
            moveBy(speedX, speedY);
        } else {
            float[] pos = posPrediction.remove();
            setPosX(pos[0]);
            setPosY(pos[1]);
            if(posPrediction.size() == 0) {

            }
        }
        calculatePrediction(planets);
    }

    protected void flushPrediction() {
        posPrediction = new LinkedList<>();
    }

    private void calculatePrediction(Vector<GravityEntity> planets) {
        if(posPrediction == null) {
            return;
        }
        float[] lastPos;
        if (posPrediction.size() == 0) {
            speedPredictionX = getSpeedX();
            speedPredictionY = getSpeedY();
            lastPos = new float[]{getPosX(), getPosY()};
        } else {
            lastPos = posPrediction.getLast();
        }
        if (posPrediction.size() < predictionTicks) {
            while (posPrediction.size() < predictionTicks) {
                for (GravityEntity p : planets) {
                    float[] acc = p.calculateAccelerationForPos(lastPos[0], lastPos[1]);
                    speedPredictionX += acc[0];
                    speedPredictionY += acc[1];
                }
                lastPos[0] += speedPredictionX;
                lastPos[1] += speedPredictionY;
                posPrediction.add(lastPos);
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
}
