package com.acul.simulation;

public class Player extends MobileEntity {

    private static float acceleration = 0.003f;

    public Player(float posX, float posY) {
        super(posX, posY, 3, 1, "img.png");
    }

    public void pointTowards(float posX, float posY, boolean accelerate) {
        float totalDist = (float) Math.sqrt(posX * posX + posY * posY);
        float factorX = posX / totalDist;
        float factorY = posY / totalDist;
        this.setFactors(factorX,factorY);
        if(accelerate) {
            accelerateTowards(factorX, factorY);
        }
    }

    private void accelerateTowards(float factorX, float factorY) {

        this.accelerateBy(factorX * acceleration, factorY * acceleration);
    }

}
