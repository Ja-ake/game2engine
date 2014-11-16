package particles;

import engine.AbstractEntity;
import engine.Color4d;
import engine.Vector2;

public class ParticleEmitter extends AbstractEntity {

    public ParticleEmitter(Vector2 position, Vector2 velocity, int particlenumber, int life, Color4d color, boolean blend) {
        //Components
        ParticleComponent pac = add(new ParticleComponent(blend));

        for (int i = 0; i < particlenumber; i++) {
            pac.particles.add(new Particle(position.add(new Vector2(Math.random() - .5, Math.random() - .5)),
                    velocity.add(new Vector2(Math.random() - .5, Math.random() - .5)),
                    (int) (Math.random() * life), color));
        }
        //Systems
        add(new ParticleUpdateSystem(pac, this));
        add(new ParticleRenderSystem(pac));
    }
}
