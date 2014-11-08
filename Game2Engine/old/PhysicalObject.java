package edu.catlin.springerj.g2e.object;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.lwjgl.SpriteComponent;
import edu.catlin.springerj.g2e.lwjgl.SpriteRenderSystem;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.object.collision.AABBCollisionSystem;
import edu.catlin.springerj.g2e.object.collision.AABBComponent;
import edu.catlin.springerj.g2e.object.collision.CircularCollisionSystem;
import edu.catlin.springerj.g2e.object.collision.CircularCollisionComponent;
import edu.catlin.springerj.g2e.object.collision.PolygonalCollisionSystem;
import edu.catlin.springerj.g2e.object.collision.PolygonalObjectComponent;
import edu.catlin.springerj.g2e.object.movement.FrictionComponent;
import edu.catlin.springerj.g2e.object.movement.FrictionSystem;
import edu.catlin.springerj.g2e.object.movement.PositionComponent;
import edu.catlin.springerj.g2e.object.movement.RotationComponent;
import edu.catlin.springerj.g2e.object.movement.RotationVelocityComponent;
import edu.catlin.springerj.g2e.object.movement.VelocityComponent;
import edu.catlin.springerj.g2e.object.movement.VelocityMovementSystem;

public class PhysicalObject extends AbstractEntity {

	public PhysicalObject() {
		this(new Vector2(0.0f, 0.0f), new Vector2(0.0f, 0.0f));
	}
	
	public PhysicalObject(Vector2 position) {
		this(position, new Vector2(0.0f, 0.0f));
	}
	
	public PhysicalObject(Vector2 position, Vector2 velocity) {
		
		// Components
		add(new SpriteComponent());
		add(new PositionComponent(position));
		add(new RotationComponent(/*0.0f*/));
		add(new VelocityComponent(velocity));
		add(new RotationVelocityComponent(/*new Vector2(0.0f, 0.0f)*/));
		add(new FrictionComponent(2.0f));
		add(new PolygonalObjectComponent(0.0f, 0.0f, 32.0f, 0.0f, 32.0f, 32.0f, 0.0f, 32.0f));
		//add(new CircularCollisionComponent(16.0f));
		//add(new AABBComponent(32.0f, 32.0f));
	}
	
	@Override
	public void initialize() {
		// Systems
		add(new SpriteRenderSystem(this, "block_red")); // SpriteComponent
		add(new VelocityMovementSystem());
		add(new FrictionSystem());
		add(new PolygonalCollisionSystem());
		//add(new CircularCollisionSystem());
		//add(new AABBCollisionSystem());
	}

	@Override
	public void update() {
		
	}

}
