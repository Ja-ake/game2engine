package player;

import movement.PositionComponent;
import movement.VelocityComponent;
import planets.Planet;
import planets.PlanetGravityManager;
import engine.AbstractComponent;
import engine.Main;
import engine.Vector2;

public class PlayerGravityComponent extends AbstractComponent {

    public Planet planet;

    public PlayerGravityComponent(Vector2 pos) {
        planet = Main.gameManager.get(PlanetGravityManager.class).nearest(pos);
    }
    
    public Vector2 pos() {
        return planet.get(PositionComponent.class).position;
    }
    
    public Vector2 vel() {
        return planet.get(VelocityComponent.class).velocity;
    }

}
