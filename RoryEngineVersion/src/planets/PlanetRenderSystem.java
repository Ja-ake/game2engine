package planets;

import movement.PositionComponent;
import collisions.CircleCollisionComponent;
import engine.AbstractSystem;
import engine.Color4d;
import graphics.Graphics;

public class PlanetRenderSystem extends AbstractSystem {

    private PositionComponent pos;
    private CircleCollisionComponent cir;
    private PlanetComponent pc;

    public PlanetRenderSystem(PositionComponent pos, CircleCollisionComponent cir, PlanetComponent pc) {
        this.pos = pos;
        this.cir = cir;
        this.pc = pc;
    }

    @Override
    public void update() {
        Graphics.drawCircle(pos.position.x + 8, pos.position.y - 8, cir.size, new Color4d(0, 0, 0, .2));
        Graphics.drawCircle(pos.position.x, pos.position.y, cir.size, pc.color2);
        Graphics.drawCircle(pos.position.x, pos.position.y, cir.size * .9, pc.color1);
    }

}
