package edu.catlin.springerj.explore.graphics.particle;

import engine.Main;
import engine.AbstractEntity;
import engine.AbstractSystem;

public class ParticleUpdateSystem extends AbstractSystem {

    private ParticleComponent pac;
    private AbstractEntity entity;

    @Override
    public void update() {
        for (int i = 0; i < pac.particles.size(); i++) {
            Particle p = pac.particles.get(i);
            p.life -= Main.stepSize;
            if (p.life < 0) {
                pac.particles.remove(i);
                i--;
            }
            p.color = p.color.setA(p.life / p.maxlife);
            p.position = p.position.add(p.velocity.multiply(p.life / p.maxlife).multiply(Main.stepSize));
        }

        if (pac.particles.size() == 0) {
            entity.destroySelf();
        }
    }

}
