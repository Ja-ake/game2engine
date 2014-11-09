package edu.catlin.springerj.explore.grapple.particle;

import java.util.ArrayList;
import java.util.List;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class ParticleComponent extends AbstractComponent {

	public List<Particle> particles;
	
	public ParticleComponent() {
		particles = new ArrayList<Particle>();
	}
	
	@Override
	public void initialize(AbstractEntity e) {
		
	}
	
}
