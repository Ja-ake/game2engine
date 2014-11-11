package particles;

import engine.Main;
import engine.AbstractEntity;
import engine.AbstractSystem;

public class ParticleUpdateSystem extends AbstractSystem {

    private ParticleComponent pac;
    private AbstractEntity entity;

    public ParticleUpdateSystem(ParticleComponent pac, AbstractEntity entity) {
        this.pac = pac;
        this.entity = entity;
    }

    @Override
    public void update() {
        for (int i = 0; i < pac.particles.size(); i++) {
            Particle p = pac.particles.get(i);
            p.life--;
            if (p.life < 0) {
                pac.particles.remove(i);
                i--;
            }
            p.color = p.color.setA((double) p.life / p.maxLife);
            p.position = p.position.add(p.velocity.multiply((double) p.life / p.maxLife));
        }
        if (pac.particles.isEmpty()) {
            entity.destroySelf();
        }
    }
}
