package edu.catlin.springerj.explore.enemy;

import edu.catlin.springerj.explore.bullets.BulletCooldownComponent;
import edu.catlin.springerj.explore.bullets.BulletCooldownSystem;
import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.collisions.CircleCollisionSystem;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.physics.PositionComponent;
import edu.catlin.springerj.g2e.physics.RotationComponent;
import edu.catlin.springerj.g2e.physics.VelocityComponent;
import edu.catlin.springerj.g2e.physics.VelocitySystem;

public class Enemy extends AbstractEntity {

    public Enemy(Vector2 position) {
        // Components
        add(new PositionComponent(position));
        add(new VelocityComponent());
        add(new RotationComponent());
        add(new SpriteComponent("enemy_smoking_black", 5, 2));
        add(new BulletCooldownComponent(5));
        add(new HealthComponent(50));
        add(new CircleCollisionComponent(10, true));
        //Systems
        add(new SpriteRenderSystem());
        add(new VelocitySystem());
        add(new Enemy1System());
        add(new DeathSystem());
        add(new BulletCooldownSystem());
        add(new CircleCollisionSystem());
        add(new HealthbarSystem());
    }

    @Override
    public void initialize() {
    }

    @Override
    public void update() {
    }

}
