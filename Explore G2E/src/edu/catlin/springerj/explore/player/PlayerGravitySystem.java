package edu.catlin.springerj.explore.player;

import edu.catlin.springerj.explore.grapple.GrappleComponent;
import edu.catlin.springerj.explore.planets.Planet;
import edu.catlin.springerj.explore.planets.PlanetGravityComponent;
import edu.catlin.springerj.explore.planets.PlanetGravityManager;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.physics.PositionComponent;
import edu.catlin.springerj.g2e.physics.VelocityComponent;

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
            Planet planet = Core.getRootManager().getManager(PlanetGravityManager.class).nearest(p.position);
            pg.planetPos = planet.get(PositionComponent.class);
            pg.planetVel = planet.get(VelocityComponent.class);
        } else {
            pg.planetPos = gc.planet.pc;
            pg.planetVel = gc.planet.vc;
        }
        v.velocity = v.velocity.add(pg.planetPos.position.subtract(p.position).setLength(50 * Core.getDefaultTimer().getDeltaTime()));
        
    	}catch(Exception e) {
        	return;
        }
    }
}
