package edu.catlin.springerj.explore.graphics.particle;

import static org.lwjgl.opengl.GL11.*;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;

public class ParticleRenderSystem extends AbstractSystem {

	private ParticleComponent pc;
	
	@Override
	public void initialize(AbstractEntity e) {
		pc = e.get(ParticleComponent.class);
	}

	@Override
	public void update() {
		glPushMatrix();
		glPointSize(2.0f);
		glBegin(GL_POINTS);
		{
			for (Particle p : pc.particles) {
				glColor4d(p.red, p.green, p.blue, p.alpha);
				glVertex3d(p.position.x, p.position.y, 0.0d);
			}
			glEnd();
		}
		glPointSize(1.0f);
		glPopMatrix();
	}

}
