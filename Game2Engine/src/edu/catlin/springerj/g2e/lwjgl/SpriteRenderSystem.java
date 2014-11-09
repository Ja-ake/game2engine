package edu.catlin.springerj.g2e.lwjgl;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.draw.Graphics;
import edu.catlin.springerj.g2e.movement.PositionComponent;
import edu.catlin.springerj.g2e.movement.RotationComponent;

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
        	//System.out.println(rotation.rot);
            Graphics.drawSprite(sprite.getTexture(), position.position.x, position.position.y, 
            		rotation.rot, sprite.red, sprite.green, sprite.blue, sprite.alpha);
        }
        sprite.imageIndex += Core.getDefaultTimer().getDeltaTime() * sprite.imageSpeed;
    }

}
