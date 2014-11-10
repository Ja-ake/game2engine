package entities;

import components.PhysicsComponent;
import components.PositionComponent;
import components.SpriteComponent;
import engine.AbstractEntity;
import engine.Vector2;
import physics.Polygon;
import systems.SpriteRenderSystem;

public class Wall extends AbstractEntity {

    public Wall(Vector2 pos) {
        //Components
        PositionComponent pc = new PositionComponent(pos);
        SpriteComponent sc = add(new SpriteComponent());//"wall_block"));
        PhysicsComponent pyc = add(new PhysicsComponent(pc, new Polygon(4, 16)));
        //Systems
        add(new SpriteRenderSystem(pc, sc));
    }
}
