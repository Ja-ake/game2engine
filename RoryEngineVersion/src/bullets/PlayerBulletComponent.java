package bullets;

import engine.AbstractComponent;

public class PlayerBulletComponent extends AbstractComponent {

    public int type;

    public PlayerBulletComponent(int type) {
        this.type = type;
    }

}
