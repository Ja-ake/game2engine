package edu.catlin.springerj.explore.enemy;

import components.PositionComponent;
import components.RotationComponent;
import components.SpriteComponent;
import components.VelocityComponent;
import edu.catlin.springerj.explore.bullets.BulletCooldownComponent;
import edu.catlin.springerj.explore.bullets.BulletCooldownSystem;
import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import engine.AbstractEntity;
import engine.Vector2;

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
        add(new HealthbarSystem());
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
    }
}
