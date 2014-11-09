package edu.catlin.springerj.explore.enemy;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class HealthComponent extends AbstractComponent {

    public double currentHealth;
    public double maxHealth;

    public HealthComponent(double maxHealth) {
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
    }

    public void damage(double amount) {
        currentHealth -= amount;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    @Override
    public void initialize(AbstractEntity e) {
    }

}
