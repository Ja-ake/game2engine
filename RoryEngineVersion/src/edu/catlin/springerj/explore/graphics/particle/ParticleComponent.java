package edu.catlin.springerj.explore.graphics.particle;

import java.util.ArrayList;

import engine.AbstractComponent;

public class ParticleComponent extends AbstractComponent {

    public ArrayList<Particle> particles;
    public boolean blendAdd;

    public ParticleComponent(boolean blendAdd) {
        particles = new ArrayList();
        this.blendAdd = blendAdd;
    }

}
