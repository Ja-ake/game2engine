package engine;

import managers.FPSManagerComponent;
import graphics.RenderManagerComponent;
import collisions.CollisionManager;
import planets.PlanetGravityManager;
import engine.AbstractEntity;
import managers.RoomComponent;
import managers.FPSManagerSystem;
import graphics.RenderManagerSystem;

public class GameManager extends AbstractEntity {

    public GameManager() {
        RenderManagerComponent rmc = add(new RenderManagerComponent());
        add(new RenderManagerSystem(rmc));

        FPSManagerComponent fmc = add(new FPSManagerComponent());
        add(new FPSManagerSystem(fmc));
        
        add(new CollisionManager());
        
        add(new RoomComponent());
        
        add(new PlanetGravityManager());
    }

}
