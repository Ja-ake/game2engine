package edu.catlin.springerj.explore.planets;

import components.PositionComponent;
import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import engine.AbstractEntity;
import engine.AbstractSystem;
import engine.Color4d;
import graphics.Graphics;

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
        Graphics.drawCircle(pos.position.x + 8, pos.position.y - 8, cir.size, new Color4d(0, 0, 0, .2));
        Graphics.drawCircle(pos.position.x, pos.position.y, cir.size, pc.color2);
        Graphics.drawCircle(pos.position.x, pos.position.y, cir.size * .9, pc.color1);
    }

}
