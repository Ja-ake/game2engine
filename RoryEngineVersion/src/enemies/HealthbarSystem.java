package enemies;

import movement.PositionComponent;
import engine.AbstractSystem;
import engine.Color4d;
import graphics.Graphics;

public class HealthbarSystem extends AbstractSystem {

    private PositionComponent pc;
    private HealthComponent hc;

    public HealthbarSystem(PositionComponent pc, HealthComponent hc) {
        this.pc = pc;
        this.hc = hc;
    }

    @Override
    public void update() {
        Graphics.fillRect(pc.position.x - 16, pc.position.y - 32, 32, 8, Color4d.BLACK);
        Graphics.fillRect(pc.position.x - 14, pc.position.y - 30, 28, 4, Color4d.RED);
        Graphics.fillRect(pc.position.x - 14, pc.position.y - 30, 28 * hc.currentHealth / hc.maxHealth, 4, Color4d.GREEN);
    }

}
