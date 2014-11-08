package edu.catlin.springerj.explore;

import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.lwjgl.LWJGLManager;
import edu.catlin.springerj.g2e.lwjgl.game.FPSDisplay;

public class Jake {
	public static void main(String[] args) {
		Core.initialize(new LWJGLManager());
		Core.getRootManager().add(new FPSDisplay());
		Core.run();
	}
}
