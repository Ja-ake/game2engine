package edu.catlin.springerj.explore.graphics.particle;

import static org.lwjgl.opengl.GL11.*;

import engine.AbstractSystem;

public class ParticleRenderSystem extends AbstractSystem {

    private ParticleComponent pc;

    public ParticleRenderSystem(ParticleComponent pc) {
        this.pc = pc;
    }

    @Override
    public void update() {
        glPushMatrix();
        glPointSize(2.0f);
        if (pc.blendAdd) {
            glBlendFunc(GL_ONE, GL_ONE);
        }
        glBegin(GL_POINTS);
        for (Particle p : pc.particles) {
            p.color.glColor();
            glVertex2d(p.position.x, p.position.y);
        }
        glEnd();
        if (pc.blendAdd) {
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        }
        glPointSize(1.0f);
        glPopMatrix();
    }
}
