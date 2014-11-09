package edu.catlin.springerj.explore.grapple.particle;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class ParticleEmitter extends AbstractEntity {
	
	public ParticleEmitter(Vector2 position, Vector2 velocity, int particlenumber) {
		ParticleComponent pac;
		PositionComponent pc;
		
		add(pc = new PositionComponent(position));
		add(pac = new ParticleComponent());
		
		for (int i=0; i<particlenumber; i++) {
			pac.particles.add(new Particle(position.add(new Vector2(Math.random()*10.0d, Math.random()*10.0d)),
											velocity.add(new Vector2(Math.random()*Math.max(velocity.length(), 10.0d), Math.random()*Math.max(velocity.length(), 10.0d))),
											Math.random()*5.0d, 0.0d, 0.0d, 0.0d));
		}
	}
	
	@Override
	public void initialize() {
		add(new ParticleRenderSystem());
		add(new ParticleUpdateSystem());
	}

	@Override
	public void update() {

	}

}
