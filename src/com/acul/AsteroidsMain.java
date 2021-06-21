package com.acul;

import com.acul.gui.*;

import java.util.Timer;

public class AsteroidsMain {


    private static final int tps = 20;
    private final Application app;
    Timer gameTimer;

    public AsteroidsMain() {
        init();
        Initializer init = new Initializer();
        app = new Application(init);

        Thread t = new Thread(app);
        t.run();
    }

    public static void main(String[] args) {
        AsteroidsMain main = new AsteroidsMain();
    }

    private void init() {
        gameTimer = new Timer();
    }

    public void start() {
        gameTimer.scheduleAtFixedRate(null, 0, 1000/tps);
    }

}
