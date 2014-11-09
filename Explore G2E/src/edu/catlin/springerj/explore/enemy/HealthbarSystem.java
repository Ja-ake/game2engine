package edu.catlin.springerj.explore.enemy;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.lwjgl.draw.Graphics;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class HealthbarSystem extends AbstractSystem {

    private PositionComponent pc;
    private HealthComponent hc;

    @Override
    public void initialize(AbstractEntity e) {
        pc = e.get(PositionComponent.class);
        hc = e.get(HealthComponent.class);
    }

    @Override
    public void update() {
        Graphics.fillRect(pc.position.x - 16, pc.position.y - 32, 32, 8, 0, 0, 0);
        Graphics.fillRect(pc.position.x - 14, pc.position.y - 30, 28, 4, 1, 0, 0);
        Graphics.fillRect(pc.position.x - 14, pc.position.y - 30, 28 * hc.currentHealth / hc.maxHealth, 4, 0, 1, 0);
    }

}
