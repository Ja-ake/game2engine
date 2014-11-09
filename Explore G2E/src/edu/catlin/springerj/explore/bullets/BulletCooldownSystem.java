package edu.catlin.springerj.explore.bullets;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;

public class BulletCooldownSystem extends AbstractSystem {

    private BulletCooldownComponent bcc;

    @Override
    public void initialize(AbstractEntity e) {
        bcc = e.get(BulletCooldownComponent.class);
    }

    @Override
    public void update() {
        bcc.currentCooldown -= Core.getDefaultTimer().getDeltaTime();
        if (bcc.currentCooldown < 0) {
            bcc.currentCooldown = 0;
        }
    }

}
