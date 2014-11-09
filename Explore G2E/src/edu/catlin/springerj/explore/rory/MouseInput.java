package edu.catlin.springerj.explore.rory;

import edu.catlin.springerj.g2e.core.AbstractManager;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.event.EventListener;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.event.MouseEvent;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.lwjgl.WindowComponent;
import edu.catlin.springerj.g2e.math.Vector2;
import java.util.ArrayList;

public class MouseInput extends AbstractManager implements EventListener<MouseEvent> {

    private ArrayList<Integer> down = new ArrayList();
    private ArrayList<Integer> pressed = new ArrayList();
    private ArrayList<Integer> released = new ArrayList();
    public Vector2 mousePos;

    @Override
    public void initialize() {
        getManager().getManager(EventManager.class).register(this);
    }

    public boolean isDown(int button) {
        return down.contains(button);
    }

    public boolean isPressed(int button) {
        return pressed.contains(button);
    }

    public boolean isReleased(int button) {
        return released.contains(button);
    }

    @Override
    public void onEvent(MouseEvent e) {
        Integer button = e.button;
        if (e.action == MouseEvent.ACTION_PRESS) {
            down.add(button);
            pressed.add(button);
        } else if (e.action == MouseEvent.ACTION_RELEASE) {
            down.remove(button);
            released.add(button);
        } else if (e.action == MouseEvent.ACTION_MOVE) {
            WindowComponent wc = ((LWJGLManager) Core.getRootManager()).getWindow().getComponent(WindowComponent.class);
            mousePos = new Vector2((wc.centerx - (wc.width / 2)) + e.x, (wc.centery - (wc.height / 2)) + e.y);
        }
    }

    @Override
    public void run() {
        pressed.clear();
        released.clear();
    }
}
