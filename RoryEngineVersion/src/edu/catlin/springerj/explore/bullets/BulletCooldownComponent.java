package edu.catlin.springerj.explore.bullets;

import engine.AbstractComponent;

public class BulletCooldownComponent extends AbstractComponent {

    public double cooldown;
    public double currentCooldown;

    public BulletCooldownComponent(double cooldown) {
        this.cooldown = cooldown;
        currentCooldown = cooldown;
    }
    
    public boolean shoot() {
        if (currentCooldown <= 0) {
            currentCooldown = cooldown;
            return true;
        }
        return false;
    }
}
