package engine;

import java.util.ArrayList;
import org.lwjgl.input.Mouse;

public abstract class MouseInput {

    private static ArrayList<Integer> down = new ArrayList();
    private static ArrayList<Integer> pressed = new ArrayList();
    private static ArrayList<Integer> released = new ArrayList();

    public static boolean isDown(int button) {
        return down.contains(button);
    }

    public static boolean isPressed(int button) {
        return pressed.contains(button);
    }

    public static boolean isReleased(int button) {
        return released.contains(button);
    }

    public static void update() {
        pressed.clear();
        released.clear();
        while (Mouse.next()) {
            Integer button = Mouse.getEventButton();
            if (Mouse.getEventButtonState()) {
                down.add(button);
                pressed.add(button);
            } else {
                down.remove(button);
                released.add(button);
            }
        }
    }
}
