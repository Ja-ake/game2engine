package entities;

import components.FPSManagerComponent;
import components.PhysicsManagerComponent;
import components.RenderManagerComponent;
import edu.catlin.springerj.explore.collisions.CollisionManager;
import engine.AbstractEntity;
import systems.FPSManagerSystem;
import systems.RenderManagerSystem;

public class GameManager extends AbstractEntity {

    public GameManager() {
        RenderManagerComponent rmc = add(new RenderManagerComponent());
        add(new RenderManagerSystem(rmc));

        FPSManagerComponent fmc = add(new FPSManagerComponent());
        add(new FPSManagerSystem(fmc));
        
        add(new PhysicsManagerComponent());
        
        add(new CollisionManager());
    }

}
