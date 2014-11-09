package edu.catlin.springerj.explore.jake.items;

import edu.catlin.springerj.explore.jake.newjake.PlayerEntity;
import edu.catlin.springerj.explore.planets.Planet;
import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class GrappleComponent extends AbstractComponent {

    public PlayerEntity player;
    public Grapple grapple;
    public Planet planet;

    public GrappleComponent(PlayerEntity player, Grapple grapple) {
        this.player = player;
        this.grapple = grapple;
        planet = null;
    }

    @Override
    public void initialize(AbstractEntity e) {
    }

}
