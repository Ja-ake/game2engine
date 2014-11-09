package edu.catlin.springerj.explore.rory;

import edu.catlin.springerj.g2e.core.AbstractManager;
import edu.catlin.springerj.g2e.event.EventListener;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.event.KeyboardEvent;
import java.util.ArrayList;

public class Keys extends AbstractManager implements EventListener<KeyboardEvent> {

    private ArrayList<Integer> down = new ArrayList();
    private ArrayList<Integer> pressed = new ArrayList();
    private ArrayList<Integer> released = new ArrayList();

    @Override
    public void initialize() {
        getManager().getManager(EventManager.class).register(this);
    }

    public boolean isDown(int key) {
        return down.contains(key);
    }

    public boolean isPressed(int key) {
        return pressed.contains(key);
    }

    public boolean isReleased(int key) {
        return released.contains(key);
    }

    @Override
    public void onEvent(KeyboardEvent e) {
        Integer key = e.key;
        if (e.pressed) {
            down.add(key);
            pressed.add(key);
        } else {
            down.remove(key);
            released.add(key);
        }
    }

    @Override
    public void run() {
        pressed.clear();
        released.clear();
    }
}
