package edu.catlin.springerj.explore.player;

import engine.AbstractComponent;
import engine.AbstractEntity;

public class PlayerWeaponComponent extends AbstractComponent {

    public int weapon = 0;
    public boolean auto = false;

    @Override
    public void initialize(AbstractEntity e) {
    }

    public int next() {
        weapon = (weapon + 1) % 4;
        return weapon;
    }

}
