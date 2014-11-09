package edu.catlin.springerj.explore.grapple.particle;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class ParticleUpdateSystem extends AbstractSystem {

	private PositionComponent pc;
	private ParticleComponent pac;
	private AbstractEntity entity;
	
	@Override
	public void initialize(AbstractEntity e) {
		pc = e.get(PositionComponent.class);
		pac = e.get(ParticleComponent.class);
		entity = e;
	}

	@Override
	public void update() {
		for (Particle p : pac.particles) {
			p.life -= Core.getDefaultTimer().getDeltaTime();
			p.alpha = p.life/p.maxlife;
			p.position = p.position.add(p.velocity.multiply(p.life/p.maxlife).multiply(Core.getDefaultTimer().getDeltaTime()));
		}
		
		if (pac.particles.size() == 0) {
			Core.getRootManager().remove(entity);
		}
	}

}
