package com.acul.simulation;

public class Player extends MobileEntity {

    private static float acceleration = 0.003f;

    public Player(float posX, float posY) {
        super(posX, posY, 5, "spaceship.png");
        //setPredictionTicks(20);
    }

    public void pointTowards(float posX, float posY, boolean accelerate, boolean fire) {
        float totalDist = (float) Math.sqrt(posX * posX + posY * posY);
        float factorX = posX / totalDist;
        float factorY = posY / totalDist;
        this.setFactors(factorX,factorY);
        if(accelerate) {
            this.setState(1);
            accelerateTowards(factorX, factorY);
        } else {
            setState(0);
        }
    }

    private void accelerateTowards(float factorX, float factorY) {

        this.accelerateBy(factorX * acceleration, factorY * acceleration);
    }

    @Override
    public int getStateAmount() {
        return 2;
    }
}
