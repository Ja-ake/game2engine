package engine;

import java.util.ArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import managers.RoomComponent;

public abstract class Main {

    public static void main(String[] args) {
        try {
            init();
            run();
        } catch (LWJGLException ex) {
            ex.printStackTrace();
        } finally {
            destroy();
        }
    }

    public static ArrayList<ArrayList<AbstractSystem>> systems;
    private static int layers = 3;
    private static int speed = 60;
    public static double stepSize = 1. / 60;
    public static GameManager gameManager;

    public static void destroy() {
        Mouse.destroy();
        Keyboard.destroy();
        Display.destroy();
    }

    public static void init() throws LWJGLException {
        systems = new ArrayList();
        for (int i = 0; i < layers; i++) {
            systems.add(new ArrayList());
        }
        gameManager = new GameManager();
        Keyboard.create();
        Mouse.create();

        //Load room 1
        gameManager.get(RoomComponent.class).load("tutorial-00");
    }

    public static void run() {
        while (!Display.isCloseRequested() && !Keys.isPressed(Keyboard.KEY_ESCAPE)) {
            //Input
            Keys.update();
            MouseInput.update();
            //Logic
            for (ArrayList<AbstractSystem> list : systems) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).update();
                }
            }
            //Graphics
            Display.update();
            Display.sync(speed);
        }
    }
}
