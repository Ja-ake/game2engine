package edu.catlin.springerj.explore.planets;

import components.PositionComponent;
import components.VelocityComponent;
import engine.AbstractComponent;
import engine.AbstractEntity;

public class PlanetGravityComponent extends AbstractComponent {

    public PositionComponent planetPos;
    public VelocityComponent planetVel;

    @Override
    public void initialize(AbstractEntity e) {
    }

}
