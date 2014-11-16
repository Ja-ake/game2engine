package enemies;

import engine.AbstractEntity;
import engine.AbstractSystem;

public class DeathSystem extends AbstractSystem {

    private AbstractEntity entity;
    private HealthComponent hc;

    public DeathSystem(AbstractEntity entity, HealthComponent hc) {
        this.entity = entity;
        this.hc = hc;
    }

    @Override
    public void update() {
        if (hc.currentHealth == 0) {
            entity.destroySelf();
        }
    }

}
