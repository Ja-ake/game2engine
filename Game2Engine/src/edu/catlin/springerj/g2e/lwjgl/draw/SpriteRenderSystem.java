package edu.catlin.springerj.g2e.lwjgl.draw;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.DrawCommand;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.physics.PositionComponent;
import edu.catlin.springerj.g2e.physics.RotationComponent;

public class SpriteRenderSystem extends AbstractSystem {

	private PositionComponent position;
	private RotationComponent rotation;
	private SpriteComponent sprite;

	@Override
	public void initialize(AbstractEntity e) {
		position = e.get(PositionComponent.class);
		rotation = e.get(RotationComponent.class);
		sprite = e.get(SpriteComponent.class);
	}

	@Override
	public void update() {
		if (sprite.visible) {
			((LWJGLManager) Core.getRootManager()).draw(DrawCommand.drawSprite(-100, sprite.getTexture(), position.position.x, position.position.y, rotation.rotation, sprite.red, sprite.green, sprite.blue, sprite.alpha));
		}
		sprite.imageIndex += Core.getDefaultTimer().getDeltaTime() * sprite.imageSpeed;
	}

}
