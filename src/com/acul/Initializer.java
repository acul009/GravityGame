package com.acul;

import com.acul.gui.*;

public class Initializer implements AppInitializer {
    @Override
    public void init(Application app) {
        Window mainWindow = null;
        Window secondWindow = null;
        try {
            mainWindow = app.createWindow("Main");
            Renderer test = new TestRenderer(mainWindow);
        } catch (InitFailureException e) {
            e.printStackTrace();
        }
    }
}
