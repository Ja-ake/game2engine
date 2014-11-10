package systems;

import components.PositionComponent;
import components.RotationComponent;
import components.SpriteComponent;
import engine.AbstractSystem;
import graphics.Graphics;

public class SpriteRenderSystem extends AbstractSystem {

    private PositionComponent position;
    private RotationComponent rotation;
    private SpriteComponent sprite;

    public SpriteRenderSystem(PositionComponent position, RotationComponent rotation, SpriteComponent sprite) {
        this.position = position;
        this.rotation = rotation;
        this.sprite = sprite;
    }

    public SpriteRenderSystem(PositionComponent position, SpriteComponent sprite) {
        this(position, new RotationComponent(), sprite);
    }

    @Override
    public void update() {
        if (sprite.visible) {
            Graphics.drawSprite(sprite.getTexture(), position.position.x, position.position.y, rotation.rot);
        }
    }

}
