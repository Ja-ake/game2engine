package edu.catlin.springerj.explore.enemy;

import components.PositionComponent;
import components.RotationComponent;
import components.SpriteComponent;
import components.VelocityComponent;
import edu.catlin.springerj.explore.bullets.BulletCooldownComponent;
import edu.catlin.springerj.explore.bullets.BulletCooldownSystem;
import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.collisions.CircleCollisionSystem;
import engine.AbstractEntity;
import engine.Vector2;
import systems.SpriteRenderSystem;
import systems.VelocityMovementSystem;

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
        add(new VelocityMovementSystem());
        add(new Enemy1System());
        add(new DeathSystem());
        add(new BulletCooldownSystem());
        add(new CircleCollisionSystem());
        add(new HealthbarSystem());
    }

}
