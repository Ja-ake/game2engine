package edu.catlin.springerj.explore.bullets;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class BulletCooldownComponent extends AbstractComponent {

    public double cooldown;
    public double currentCooldown;

    public BulletCooldownComponent(double cooldown) {
        this.cooldown = cooldown;
        currentCooldown = cooldown;
    }

    @Override
    public void initialize(AbstractEntity e) {
    }
    
    public boolean shoot() {
        if (currentCooldown <= 0) {
            currentCooldown = cooldown;
            return true;
        }
        return false;
    }
}
