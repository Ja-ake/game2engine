package edu.catlin.springerj.explore.jake.graphics;

import static org.lwjgl.opengl.GL11.*;
import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.movement.PositionComponent;

public class FilledCircleRenderSystem extends AbstractSystem {
	PositionComponent pc;
	CircleComponent cc;
	
	@Override
	public void initialize(AbstractEntity e) {
		pc = e.getComponent(PositionComponent.class);
		cc = e.getComponent(CircleComponent.class);
	}

	@Override
	public void update() {
		glPushMatrix();
		glDisable(GL_TEXTURE_2D);
        //glDisable(GL_BLEND);
        glTranslated(pc.position.x, pc.position.y, 0);
        //glTranslated(-cc.radius, cc.radius, 0);

        double sides = 64.0d;
        double linewidth = 10.0d;
        double shadow = 4.0d;
        
        // shadow
        glColor4f(0.0f, 0.0f, 0.0f, 0.175f);
        glBegin(GL_TRIANGLE_FAN);
        {
        	glVertex3d(shadow, -shadow, 0.0d);
        	for (double angle=0; angle<=sides; angle++) {
        		glVertex3d(Math.cos((angle/sides)*Math.PI*2)*cc.radius*1.02 + shadow, Math.sin((angle/sides)*Math.PI*2)*cc.radius*1.02 - shadow, 0.0d);
        	}
        }
        glEnd();
        // end shadow
        
        // outline
        glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        glBegin(GL_TRIANGLE_FAN);
        {
        	glVertex3d(0.0d, 0.0d, 0.0d);
        	for (double angle=0; angle<=sides; angle++) {
        		glVertex3d(Math.cos((angle/sides)*Math.PI*2)*cc.radius, Math.sin((angle/sides)*Math.PI*2)*cc.radius, 0.0d);
        	}
        }
        glEnd();
        // end outline
        
        // fill
        glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        glBegin(GL_TRIANGLE_FAN);
        {
        	glVertex3d(0.0d, 0.0d, 0.0d);
        	for (double angle=0; angle<=sides; angle++) {
        		glVertex3d(Math.cos((angle/sides)*Math.PI*2)*(cc.radius-linewidth), Math.sin((angle/sides)*Math.PI*2)*(cc.radius-linewidth), 0.0d);
        	}
        }
        glEnd();
        // end fill
        
        glEnable(GL_BLEND);
        glPopMatrix();
	}

}
