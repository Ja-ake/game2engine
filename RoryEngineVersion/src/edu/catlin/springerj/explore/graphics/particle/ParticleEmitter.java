package edu.catlin.springerj.explore.graphics.particle;

import components.PositionComponent;
import engine.AbstractEntity;
import engine.Color4d;
import engine.Vector2;

public class ParticleEmitter extends AbstractEntity {
    
    public ParticleEmitter(Vector2 position, Vector2 velocity, int particlenumber, double life, Color4d color, boolean blend) {
        ParticleComponent pac;
        PositionComponent pc;
        
        add(pc = new PositionComponent(position));
        add(pac = new ParticleComponent());
        
        for (int i = 0; i < particlenumber; i++) {
            pac.particles.add(new Particle(position.add(new Vector2(Math.random() * 1.0d, Math.random() * 1.0d)),
                    velocity.add(new Vector2(Math.random() * Math.max(velocity.length() / 1.25, 1.0d), Math.random() * Math.max(velocity.length() / 1.25, 1.0d))),
                    Math.random() * life, color.copy(), blend)); //double colorrand = Math.random() - 0.25d;
                    //pac.particles.add(new Particle(position.add(new Vector2(Math.random() * 1.0d, Math.random() * 1.0d)),
                    //        velocity.add(new Vector2(Math.random() * Math.max(velocity.length() / 1.25, 1.0d), Math.random() * Math.max(velocity.length() / 1.25, 1.0d))),
                    //        Math.random() * life, r - colorrand, g - colorrand, b - colorrand, blend));
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
