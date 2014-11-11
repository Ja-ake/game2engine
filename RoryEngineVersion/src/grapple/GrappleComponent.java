package grapple;

import planets.Planet;
import player.PlayerEntity;
import engine.AbstractComponent;

public class GrappleComponent extends AbstractComponent {

    public PlayerEntity player;
    public Grapple grapple;
    public Planet planet;

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
