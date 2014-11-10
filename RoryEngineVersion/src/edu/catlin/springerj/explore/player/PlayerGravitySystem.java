package edu.catlin.springerj.explore.player;

import components.PositionComponent;
import components.VelocityComponent;
import edu.catlin.springerj.explore.grapple.GrappleComponent;
import edu.catlin.springerj.explore.planets.Planet;
import edu.catlin.springerj.explore.planets.PlanetGravityComponent;
import edu.catlin.springerj.explore.planets.PlanetGravityManager;
import engine.Main;
import engine.AbstractEntity;
import engine.AbstractSystem;

public class PlayerGravitySystem extends AbstractSystem {

    private PositionComponent p;
    private VelocityComponent v;
    private PlanetGravityComponent pg;
    private GrappleComponent gc;

    @Override
    public void initialize(AbstractEntity e) {
        p = e.get(PositionComponent.class);
        v = e.get(VelocityComponent.class);
        pg = e.get(PlanetGravityComponent.class);
        gc = e.get(GrappleComponent.class);
    }

    @Override
    public void update() {
    	try {
        if (gc.planet == null) {
            Planet planet = Main.gameManager.getComponent(PlanetGravityManager.class).nearest(p.position);
            pg.planetPos = planet.get(PositionComponent.class);
            pg.planetVel = planet.get(VelocityComponent.class);
        } else {
            pg.planetPos = gc.planet.pc;
            pg.planetVel = gc.planet.vc;
        }
        v.velocity = v.velocity.add(pg.planetPos.position.subtract(p.position).setLength(50 * Main.stepSize));
        
    	}catch(Exception e) {
        	return;
        }
    }
}
