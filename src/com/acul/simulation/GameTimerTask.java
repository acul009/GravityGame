package com.acul.simulation;

import java.util.TimerTask;

public class GameTimerTask extends TimerTask {

    private final Game game;

    public GameTimerTask(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        game.runGameTick();
    }
}
