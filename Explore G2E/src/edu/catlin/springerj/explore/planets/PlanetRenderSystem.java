package edu.catlin.springerj.explore.planets;

import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.lwjgl.draw.Graphics;
import edu.catlin.springerj.g2e.math.Color4;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class PlanetRenderSystem extends AbstractSystem {

    private PositionComponent pos;
    private CircleCollisionComponent cir;
    private PlanetComponent pc;

    @Override
    public void initialize(AbstractEntity e) {
        pos = e.get(PositionComponent.class);
        cir = e.get(CircleCollisionComponent.class);
        pc = e.get(PlanetComponent.class);
    }

    @Override
    public void update() {
        Graphics.drawCircle(pos.position.x + 8, pos.position.y - 8, cir.size, new Color4(0, 0, 0, .2));
        Graphics.drawCircle(pos.position.x, pos.position.y, cir.size, pc.color2);
        Graphics.drawCircle(pos.position.x, pos.position.y, cir.size * .9, pc.color1);
    }

}
