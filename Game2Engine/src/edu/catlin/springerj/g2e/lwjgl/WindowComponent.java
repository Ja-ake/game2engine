package edu.catlin.springerj.g2e.lwjgl;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class WindowComponent extends AbstractComponent {
	public int width, height;
	public double centerx = 0, centery = 0;
	public String title;

	/**
	 * Creates a window of the default size.
	 */
	public WindowComponent() {
		width = 640;
		height = 480;
		title = "";
	}

	/**
	 * Creates a window of the default size with a title.
	 */
	public WindowComponent(String t) {
		width = 640;
		height = 480;
		title = t;
	}

	/**
	 * Creates a window of size w for width and h for height and the title t.
	 */
	public WindowComponent(String t, int w, int h) {
		width = w;
		height = h;
		title = t;
	}

	@Override
	public void initialize(AbstractEntity e) {

	}
}
