package player;

import movement.PositionComponent;
import bullets.BulletCooldownComponent;
import spells.Fireball;
import bullets.PlayerBullet;
import engine.AbstractSystem;
import engine.Keys;
import engine.MouseInput;
import engine.Vector2;
import org.lwjgl.input.Keyboard;

public class PlayerWeaponSystem extends AbstractSystem {

    private PositionComponent pos;
    private PlayerWeaponComponent wc;
    private BulletCooldownComponent bcc;

    public PlayerWeaponSystem(PositionComponent pos, PlayerWeaponComponent wc, BulletCooldownComponent bcc) {
        this.pos = pos;
        this.wc = wc;
        this.bcc = bcc;
    }

    public void fire1(int weapon) {
        Vector2 velocity = MouseInput.mousePos().subtract(pos.position).setLength(20);
        new PlayerBullet(pos.position, velocity, weapon);
    }

    public void fire2(int weapon) {
        switch (weapon) {
            case 0:
                Vector2 velocity = MouseInput.mousePos().subtract(pos.position).setLength(15);
                new Fireball(pos.position, velocity);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
    
    @Override
    public void update() {
        if (Keys.isPressed(Keyboard.KEY_Q)) {
            wc.next();
        }
        if (Keys.isPressed(Keyboard.KEY_M)) {
            wc.auto = !wc.auto;
        }
        if (wc.auto) {
            if (MouseInput.isDown(0)) {
                if (bcc.shoot()) {
                    fire1(wc.weapon);
                }
            }
        } else {
            if (MouseInput.isReleased(0)) {
                if (bcc.shoot()) {
                    double time = MouseInput.getTimeR(0);
                    if (time < 1) {
                        fire1(wc.weapon);
                    } else {
                        fire2(wc.weapon);
                    }
                }

            }
        }
    }

}
