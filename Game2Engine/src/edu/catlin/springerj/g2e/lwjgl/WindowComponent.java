package edu.catlin.springerj.g2e.lwjgl;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import static org.lwjgl.opengl.GL11.*;

public class WindowComponent extends AbstractComponent {
	protected int width, height;
	protected String title;

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
}
