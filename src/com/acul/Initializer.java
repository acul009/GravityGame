package com.acul;

import com.acul.gui.*;
import com.acul.renderer.SolarSystemRenderer;
import com.acul.simulation.Game;
import com.acul.simulation.GameTimerTask;

import java.util.Timer;
import java.util.TimerTask;

public class Initializer implements AppInitializer {

    public Initializer() {
    }

    @Override
    public void init(Application app) {
        try {
            Window mainWindow = app.createWindow("Main");
            Game game = new Game(mainWindow);
            Renderer test = new SolarSystemRenderer(mainWindow, game);
            Timer gameTimer = new Timer();
            TimerTask task = new GameTimerTask(game);
            gameTimer.scheduleAtFixedRate(task, 0, 10);
        } catch (InitFailureException e) {
            e.printStackTrace();
        }
    }
}
