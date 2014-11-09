package edu.catlin.springerj.explore.jake.items;

import edu.catlin.springerj.explore.jake.newjake.CircleCollisionComponent;
import edu.catlin.springerj.explore.jake.newjake.PlayerEntity;
import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.Core;

public class GrappleComponent extends AbstractComponent {

    public PlayerEntity player;
    public Grapple grapple;
    public CircleCollisionComponent planet;

    public GrappleComponent(PlayerEntity player, Grapple grapple) {
        this.player = player;
        this.grapple = grapple;
        planet = null;
    }

    public void destroyGrapple() {
        if (grapple != null) {
            Core.getRootManager().remove(grapple);
            grapple = null;
            planet = null;
        }
    }

    @Override
    public void initialize(AbstractEntity e) {
    }

}
