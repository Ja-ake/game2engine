package edu.catlin.springerj.explore.bullets;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class PlayerBulletComponent extends AbstractComponent {

    public int type;

    public PlayerBulletComponent(int type) {
        this.type = type;
    }

    @Override
    public void initialize(AbstractEntity e) {
    }

}
