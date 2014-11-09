package edu.catlin.springerj.explore.enemy;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;

public class DeathSystem extends AbstractSystem {

    private AbstractEntity entity;
    private HealthComponent hc;

    @Override
    public void initialize(AbstractEntity e) {
        entity = e;
        hc = e.get(HealthComponent.class);
    }

    @Override
    public void update() {
        if (hc.currentHealth == 0) {
            Core.getRootManager().remove(entity);
        }
    }

}
