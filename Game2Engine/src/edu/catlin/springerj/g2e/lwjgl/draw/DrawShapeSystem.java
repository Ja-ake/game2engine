package edu.catlin.springerj.g2e.lwjgl.draw;

import static org.lwjgl.opengl.GL11.*;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.exception.InvalidComponentException;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class DrawShapeSystem extends AbstractSystem {

    private ShapeComponent sc;
    private PositionComponent pc;

    @Override
    public void initialize(AbstractEntity ent) {
        sc = ent.getComponent(ShapeComponent.class);
        try {
            pc = ent.getComponent(PositionComponent.class);
        } catch (InvalidComponentException e) {
            pc = null;
        }
    }

    @Override
    public void update() {
        glEnable(GL_TEXTURE_2D);

        glPushMatrix();
        if (pc != null) {
            glTranslated(-pc.position.x, -pc.position.y, 0.0f);
        }
        glBegin(GL_TRIANGLES);
        glColor4d(sc.color.r, sc.color.g, sc.color.b, sc.color.a);
        for (int i = 2; i < sc.verticies.length; i++) {
            glVertex2d(sc.verticies[ 0].x, sc.verticies[ 0].y);
            glVertex2d(sc.verticies[ i].x, sc.verticies[ i].y);
            glVertex2d(sc.verticies[i - 1].x, sc.verticies[i - 1].y);
        }
        glEnd();
        glPopMatrix();

        glDisable(GL_TEXTURE_2D);
    }

}
