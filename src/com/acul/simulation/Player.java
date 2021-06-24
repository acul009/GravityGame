package com.acul.simulation;

public class Player extends MobileEntity {

    private static float acceleration = 0.001f;

    public Player(Vektor2f pos) {
        super(pos, 5, "spaceship.png");
        setMaxPredictionTicks(5000);
        setMinPredictionTicks(300);
    }

    public void pointTowards(Vektor2f pos, boolean accelerate, boolean fire) {
        Vektor2f factor = pos.normalize();
        setFactor(factor);
        if(accelerate) {
            this.setState(1);
            accelerateTowards(factor.scale(acceleration));
        } else {
            setState(0);
        }
    }

    private void accelerateTowards(Vektor2f factor) {
        this.accelerateBy(factor);
    }

    @Override
    public int getStateAmount() {
        return 2;
    }
}
