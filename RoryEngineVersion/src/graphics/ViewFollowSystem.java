package graphics;

import movement.PositionComponent;
import engine.AbstractSystem;
import engine.Main;

public class ViewFollowSystem extends AbstractSystem {

    private PositionComponent pc;
    private RenderManagerComponent rmc;

    public ViewFollowSystem(PositionComponent pc) {
        this.pc = pc;
        rmc = Main.gameManager.get(RenderManagerComponent.class);
    }

    @Override
    public void update() {
        rmc.viewX += (pc.position.x - rmc.viewX - rmc.viewWidth / 2) * .1;
        rmc.viewY += (pc.position.y - rmc.viewY - rmc.viewHeight / 2) * .1;
    }
}
