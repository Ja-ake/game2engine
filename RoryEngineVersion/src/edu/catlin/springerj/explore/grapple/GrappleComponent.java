package edu.catlin.springerj.explore.grapple;

import edu.catlin.springerj.explore.collisions.CircleCollisionComponent;
import edu.catlin.springerj.explore.player.PlayerEntity;
import engine.AbstractComponent;

public class GrappleComponent extends AbstractComponent {

    public PlayerEntity player;
    public Grapple grapple;
    public CircleCollisionComponent planet;

    public GrappleComponent(PlayerEntity player) {
        this.player = player;
        grapple = null;
        planet = null;
    }

    public void destroyGrapple() {
        if (grapple != null) {
            grapple.destroySelf();
            grapple = null;
            planet = null;
        }
    }
}
