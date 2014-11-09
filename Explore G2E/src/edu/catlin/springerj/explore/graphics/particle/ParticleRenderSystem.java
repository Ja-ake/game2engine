package edu.catlin.springerj.explore.graphics.particle;

import static org.lwjgl.opengl.GL11.*;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import org.lwjgl.opengl.GL11;

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
                    glBlendFunc(GL_ONE, GL_ONE);
        glBegin(GL_POINTS);
        {
            for (Particle p : pc.particles) {
                if (p.blend) {
                }
                glColor4d(p.red, p.green, p.blue, p.alpha);
                glVertex3d(p.position.x, p.position.y, 0.0d);
                
                if (p.blend) {
                }
            }
            glEnd();
        }
                    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glPointSize(1.0f);
        glPopMatrix();
    }

}
