package edu.catlin.springerj.explore.planets;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.physics.PositionComponent;
import edu.catlin.springerj.g2e.physics.VelocityComponent;

public class PlanetGravityComponent extends AbstractComponent {

    public PositionComponent planetPos;
    public VelocityComponent planetVel;

    @Override
    public void initialize(AbstractEntity e) {
    }

}
