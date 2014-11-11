package graphics;

import engine.AbstractComponent;

public class RenderManagerComponent extends AbstractComponent {

    public int displayWidth;
    public int displayHeight;
    public boolean startFullscreen;
    public int viewX;
    public int viewY;
    public int viewWidth;
    public int viewHeight;

    public RenderManagerComponent() {
        displayWidth = 1280;
        displayHeight = 960;
        startFullscreen = false;
        viewX = 0;
        viewY = 0;
        viewWidth = 1280;
        viewHeight = 960;
    }
}
