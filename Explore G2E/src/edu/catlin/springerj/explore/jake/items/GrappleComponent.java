package edu.catlin.springerj.explore.jake.items;

import edu.catlin.springerj.explore.jake.newjake.PlayerEntity;
import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class GrappleComponent extends AbstractComponent {

    public PlayerEntity player;
    public Grapple grapple;

    public GrappleComponent(PlayerEntity player, Grapple grapple) {
        this.player = player;
        this.grapple = grapple;
    }

    @Override
    public void initialize(AbstractEntity e) {
    }

}