package com.acul;

import com.acul.gui.*;
import com.acul.renderer.SolarSystemRenderer;

public class Initializer implements AppInitializer {
    @Override
    public void init(Application app) {
        Window mainWindow = null;
        try {
            mainWindow = app.createWindow("Main");
            Renderer test = new SolarSystemRenderer(mainWindow);
        } catch (InitFailureException e) {
            e.printStackTrace();
        }
    }
}
