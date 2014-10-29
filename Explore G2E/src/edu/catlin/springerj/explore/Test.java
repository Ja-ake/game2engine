package edu.catlin.springerj.explore;

import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.math.Vector2;
import edu.catlin.springerj.g2e.object.PhysicalObject;

public class Test {
	public static void main(String[] args) {
		// triangle
		Vector2[] triangle = new Vector2[4];
		triangle[0] = new Vector2(0.0f, 0.0f);
		triangle[1] = new Vector2(10.0f, 10.0f);
		triangle[2] = new Vector2(40.0f, -10.0f);
		triangle[3] = new Vector2(50.0f, -20.0f);
		
		Core.initialize(new LWJGLManager());
		//Core.getRootManager().add(new Player());
		//Core.getRootManager().add(new PhysicalObject(new Vector2(0.0f, 0.0f)));
		Core.getRootManager().add(new Triangle(triangle));
		Core.run();
	}
}
