package player;

import engine.AbstractComponent;

public class PlayerWeaponComponent extends AbstractComponent {

    public int weapon;
    public boolean auto;

    public PlayerWeaponComponent() {
        weapon = 0;
        auto = false;
    }

    public int next() {
        weapon = (weapon + 1) % 4;
        return weapon;
    }
}
