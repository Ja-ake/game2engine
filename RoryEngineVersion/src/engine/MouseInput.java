package engine;

import graphics.RenderManagerComponent;
import java.util.ArrayList;
import java.util.HashMap;
import org.lwjgl.input.Mouse;

public abstract class MouseInput {

    private static HashMap<Integer, Double> down = new HashMap();
    private static ArrayList<Integer> pressed = new ArrayList();
    private static HashMap<Integer, Double> released = new HashMap();

    public static double getTimeD(int button) {
        return down.get(button);
    }

    public static double getTimeR(int button) {
        return released.get(button);
    }

    public static boolean isDown(int button) {
        return down.containsKey(button);
    }

    public static boolean isPressed(int button) {
        return pressed.contains(button);
    }

    public static boolean isReleased(int button) {
        return released.containsKey(button);
    }

    public static Vector2 mousePos() {
        RenderManagerComponent rmc = Main.gameManager.get(RenderManagerComponent.class);
        return new Vector2(Mouse.getX()+rmc.viewX, Mouse.getY()+rmc.viewY);
    }

    public static void update() {
        pressed.clear();
        released.clear();
        for (Integer button : down.keySet()) {
            down.put(button, down.get(button) + Main.stepSize);
        }
        while (Mouse.next()) {
            Integer button = Mouse.getEventButton();
            if (Mouse.getEventButtonState()) {
                pressed.add(button);
                down.put(button, 0.);
            } else {
                released.put(button, down.get(button));
                down.remove(button);
            }
        }
    }
}
