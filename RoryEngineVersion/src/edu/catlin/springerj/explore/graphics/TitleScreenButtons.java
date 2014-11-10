package edu.catlin.springerj.explore.graphics;

import com.sun.jmx.snmp.tasks.Task;
import components.PositionComponent;
import components.SpriteComponent;
import engine.Main;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.lwjgl.draw.StaticImageRenderSystem;
import engine.AbstractEntity;
import engine.Vector2;
import java.awt.event.MouseEvent;

public class TitleScreenButtons extends AbstractEntity {

    private Runnable run;

    public TitleScreenButtons(Runnable r) {
        run = r;
    }

    @Override
    public void initialize() {
        add(new PositionComponent(new Vector2(-480.0d, -12.5d)));
        add(new SpriteComponent("arrow", 4, 1.5d));

        add(new StaticImageRenderSystem());

        Main.gameManager.getComponent(EventManager.class).register(this);
    }

    @Override
    public void update() {
    }

    @Override
    public void onEvent(MouseEvent event) {
        PositionComponent pc;
        try {
            pc = get(PositionComponent.class);
        } catch (Exception e) {
            return;
        }
        if (event.action == MouseEvent.ACTION_RELEASE) {
            if (pc.position.y == -12.5d) {
                for (int i = 0; i < Core.getRootManager().getEntities().size(); i++) {
                    Core.getRootManager().remove(Core.getRootManager().getEntities().get(i));
                }
                final TitleScreenButtons thus = this;
                Core.task(new Task() {

                    @Override
                    public void run() {
                        thus.run.run();
                    }

                });
            } else {
                System.exit(0);
            }
        } else if (event.action == MouseEvent.ACTION_OTHER) {
            if (pc.position.y == -12.5d) {
                pc.position = new Vector2(pc.position.x, -107.0d);
            } else {
                pc.position = new Vector2(pc.position.x, -12.5d);
            }
        }
    }
}
