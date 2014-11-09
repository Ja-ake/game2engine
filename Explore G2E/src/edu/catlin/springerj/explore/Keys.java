package edu.catlin.springerj.explore;

import edu.catlin.springerj.g2e.core.AbstractManager;
import edu.catlin.springerj.g2e.event.EventListener;
import edu.catlin.springerj.g2e.event.KeyboardEvent;
import java.util.ArrayList;

public class Keys extends AbstractManager implements EventListener<KeyboardEvent> {

    private ArrayList<Integer> down = new ArrayList();
    private ArrayList<Integer> pressed = new ArrayList();
    private ArrayList<Integer> released = new ArrayList();

    @Override
    public void initialize() {
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
        if (e.pressed) {
            down.add(e.key);
            pressed.add(e.key);
        } else {
            down.remove(e.key);
            released.add(e.key);
        }
    }

    @Override
    public void run() {
        pressed.clear();
        released.clear();
    }
}
