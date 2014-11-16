package enemies;

import engine.AbstractComponent;

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
}
