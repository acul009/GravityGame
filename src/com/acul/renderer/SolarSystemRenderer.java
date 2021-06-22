package com.acul.renderer;

import com.acul.gui.InitFailureException;
import com.acul.gui.OrthographicRenderer;
import com.acul.gui.Texture;
import com.acul.gui.Window;

public class SolarSystemRenderer extends OrthographicRenderer {

    private Texture tex;
    double posX;

    public SolarSystemRenderer(Window win) {
        super(win);
        try {
            tex = new Texture("Terran.png");
        } catch (InitFailureException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        super.render();
        tex.draw(10,10,30);
        this.setCameraPos(posX, 0);
        //posX -= 0.1;
    }
}
