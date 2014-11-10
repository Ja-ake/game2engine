package edu.catlin.springerj.explore.player;

import components.PositionComponent;
import edu.catlin.springerj.explore.bullets.BulletCooldownComponent;
import edu.catlin.springerj.explore.spells.Fireball;
import edu.catlin.springerj.explore.bullets.PlayerBullet;
import engine.Main;
import engine.AbstractEntity;
import engine.AbstractSystem;
import engine.Keys;
import engine.MouseInput;
import engine.Vector2;
import java.awt.event.MouseEvent;
import org.lwjgl.input.Keyboard;

public class PlayerWeaponSystem extends AbstractSystem {

    private PositionComponent pos;
    private PlayerWeaponComponent wc;
    private BulletCooldownComponent bcc;

    public void fire1(int weapon) {
        Vector2 velocity = Main.gameManager.getComponent(MouseInput.class).mousePos.subtract(pos.position).setLength(300);
        Core.getRootManager().add(new PlayerBullet(pos.position, velocity, weapon));
    }

    public void fire2(int weapon) {
        switch (weapon) {
            case 0:
                Vector2 velocity = Main.gameManager.getComponent(MouseInput.class).mousePos.subtract(pos.position).setLength(200);
                Core.getRootManager().add(new Fireball(pos.position, velocity));
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
    public void initialize(AbstractEntity e) {
        pos = e.get(PositionComponent.class);
        wc = e.get(PlayerWeaponComponent.class);
        bcc = e.get(BulletCooldownComponent.class);
    }

    @Override
    public void update() {
        if (Main.gameManager.getComponent(Keys.class).isPressed(Keyboard.KEY_Q)) {
            wc.next();
        }
        if (Main.gameManager.getComponent(Keys.class).isPressed(Keyboard.KEY_M)) {
            wc.auto = !wc.auto;
        }
        if (wc.auto) {
            if (Main.gameManager.getComponent(MouseInput.class).isDown(MouseEvent.BUTTON_MB1)) {
            	
                if (bcc.shoot()) {
                    fire1(wc.weapon);
                }
            }
        } else {
            if (Main.gameManager.getComponent(MouseInput.class).isReleased(MouseEvent.BUTTON_MB1)) {
                if (bcc.shoot()) {
                    double time = Main.gameManager.getComponent(MouseInput.class).getTimeR(MouseEvent.BUTTON_MB1);
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
