package engine;

import components.PhysicsComponent;
import components.PositionComponent;
import entities.Box;
import entities.Player;
import entities.GameManager;
import java.util.ArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import physics.Edge;

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

    public static ArrayList<Updatable> updatables = new ArrayList();
    public static int speed = 60;
    public static double stepSize = 1. / 60;
    public static GameManager gameManager;

    public static void destroy() {
        Mouse.destroy();
        Keyboard.destroy();
        Display.destroy();
    }

    public static <E extends Updatable> E get(Class<E> c) {
        for (Updatable u : updatables) {
            if (c.isInstance(u)) {
                return (E) u;
            }
        }
        return null;
    }

    public static void init() throws LWJGLException {
        gameManager = new GameManager();
        Keyboard.create();
        Mouse.create();

        new Player();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                new Box(new Vector2(200 + 32 * i, 200 + 32 * j));
            }
        }
        //Room box
        new PhysicsComponent(new PositionComponent(new Vector2(640, 0)), new Edge(new Vector2(640, 0), new Vector2()));
        new PhysicsComponent(new PositionComponent(new Vector2()), new Edge(new Vector2(), new Vector2(0, 640)));
        new PhysicsComponent(new PositionComponent(new Vector2(640, 640)), new Edge(new Vector2(640, 640), new Vector2(640, 0)));
    }

    public static void run() {
        while (!Display.isCloseRequested() && !Keys.isPressed(Keyboard.KEY_ESCAPE)) {
            //Input
            Keys.update();
            MouseInput.update();
            //Logic
            for (int i = 0; i < updatables.size(); i++) {
                updatables.get(i).update();
            }
            //Graphics
            Display.update();
            Display.sync(speed);
        }
    }
}
