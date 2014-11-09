package edu.catlin.springerj.g2e.event;

import edu.catlin.springerj.g2e.core.AbstractManager;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.event.EventListener;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.event.MouseEvent;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.lwjgl.WindowComponent;
import edu.catlin.springerj.g2e.math.Vector2;
import java.util.ArrayList;
import java.util.HashMap;

public class MouseInput extends AbstractManager implements EventListener<MouseEvent> {

    private static HashMap<Integer, Double> down = new HashMap();
    private static ArrayList<Integer> pressed = new ArrayList();
    private static HashMap<Integer, Double> released = new HashMap();
    public static Vector2 mousePos;

    public double getTimeD(Integer button) {
        return down.get(button);
    }

    public double getTimeR(Integer button) {
        return released.get(button);
    }

    @Override
    public void initialize() {
        getManager().getManager(EventManager.class).register(this);
    }

    public boolean isDown(int button) {
        return down.containsKey(button);
    }

    public boolean isPressed(int button) {
        return pressed.contains(button);
    }

    public boolean isReleased(int button) {
        return released.containsKey(button);
    }

    @Override
    public void onEvent(MouseEvent e) {
        Integer button = e.button;
        if (e.action == MouseEvent.ACTION_PRESS) {
            pressed.add(button);
            down.put(button, 0.);
        } else if (e.action == MouseEvent.ACTION_RELEASE) {
            released.put(button, down.get(button));
            down.remove(button);
        } else if (e.action == MouseEvent.ACTION_MOVE) {
            WindowComponent wc = ((LWJGLManager) Core.getRootManager()).getWindow().getComponent(WindowComponent.class);
            mousePos = new Vector2((wc.centerx - (wc.width / 2)) + e.x, (wc.centery - (wc.height / 2)) + e.y);
        }
    }

    @Override
    public void run() {
    }
    
    public static void update() {
        pressed.clear();
        released.clear();
        for (Integer button : down.keySet()) {
            down.put(button, down.get(button) + Core.getDefaultTimer().getDeltaTime());
        }
    }
}
