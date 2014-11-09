package edu.catlin.springerj.explore.enemy;

import edu.catlin.springerj.explore.bullets.BulletCooldownComponent;
import edu.catlin.springerj.explore.bullets.BulletCooldownSystem;
import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.RotationComponent;
import edu.catlin.springerj.g2e.movement.VelocityComponent;

public class Spawner extends AbstractEntity {

    public Spawner(Vector2 position) {
        // Components
        add(new PositionComponent(position));
        add(new VelocityComponent());
        add(new RotationComponent());
        add(new SpriteComponent("spawner"));
        add(new BulletCooldownComponent(15));
        add(new HealthComponent(500));
        add(new CircleCollisionComponent(16, true));
        get(CircleCollisionComponent.class).invMass = 0;
        //Systems
        add(new SpriteRenderSystem());
        add(new DeathSystem());
        add(new BulletCooldownSystem());
        add(new SpawnerSystem());
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
    }
}
