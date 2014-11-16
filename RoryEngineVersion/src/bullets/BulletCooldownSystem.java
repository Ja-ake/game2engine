package bullets;

import engine.AbstractSystem;
import engine.Main;

public class BulletCooldownSystem extends AbstractSystem {

    private BulletCooldownComponent bcc;

    public BulletCooldownSystem(BulletCooldownComponent bcc) {
        this.bcc = bcc;
    }

    @Override
    public void update() {
        bcc.currentCooldown -= Main.stepSize;
        if (bcc.currentCooldown < 0) {
            bcc.currentCooldown = 0;
        }
    }

}
