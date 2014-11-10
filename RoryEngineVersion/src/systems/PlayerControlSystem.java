package systems;

import components.PositionComponent;
import components.SpriteComponent;
import components.VelocityComponent;
import engine.AbstractSystem;
import engine.Keys;
import engine.MouseInput;
import engine.Vector2;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class PlayerControlSystem extends AbstractSystem {
    
    private PositionComponent position;
    private VelocityComponent velocity;
    private SpriteComponent sprite;
    
    public PlayerControlSystem(PositionComponent position, VelocityComponent velocity, SpriteComponent sprite) {
        this.position = position;
        this.velocity = velocity;
        this.sprite = sprite;
    }
    
    @Override
    public void update() {
//        boolean left = Keys.isDown(Keyboard.KEY_A);
//        boolean right = Keys.isDown(Keyboard.KEY_D);
//        boolean up = Keys.isDown(Keyboard.KEY_W);
//        boolean down = Keys.isDown(Keyboard.KEY_S);
//
//        if (left && !right) {
//            velocity.velocity = velocity.velocity.setX(-2);
//        } else if (right && !left) {
//            velocity.velocity = velocity.velocity.setX(2);
//        } else {
//            //velocity.velocity = velocity.velocity.setX(0);
//        }
//        if (down && !up) {
//            velocity.velocity = velocity.velocity.setY(-2);
//        } else if (up && !down) {
//            velocity.velocity = velocity.velocity.setY(2);
//        } else {
//            //velocity.velocity = velocity.velocity.setX(0);
//        }
        velocity.velocity = velocity.velocity.add(new Vector2(Mouse.getX(), Mouse.getY()).subtract(position.position).normalize().multiply(.2)).multiply(.9);
        
        if (MouseInput.isPressed(0)) {
            position.position = new Vector2(Mouse.getX(), Mouse.getY());
        }
        if (MouseInput.isDown(1)) {
            velocity.velocity = new Vector2();
        }
    }
    
}
