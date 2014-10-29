package edu.catlin.springerj.g2e.old.components;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import edu.catlin.springerj.g2e.old.Component;
import edu.catlin.springerj.g2e.old.events.Event;
import edu.catlin.springerj.g2e.old.events.InitializeEvent;
import edu.catlin.springerj.g2e.old.events.TickEvent;
import static org.lwjgl.opengl.GL11.*;

public class Window implements Component {
	protected int width, height;
	protected String title;

	/**
	 * Creates a window of the default size.
	 */
	public Window() {
		width = 640;
		height = 480;
		title = "";
		
		initialize();
	}

	/**
	 * Creates a window of the default size with a title.
	 */
	public Window(String t) {
		width = 640;
		height = 480;
		title = t;
		
		initialize();
	}

	/**
	 * Creates a window of size w for width and h for height and the title t.
	 */
	public Window(String t, int w, int h) {
		width = w;
		height = h;
		title = t;
		
		initialize();
	}

	/**
	 * Initializes the window.
	 */
	public void initialize() {
		setDisplayMode(width, height, false);
		{
			try {
				Display.setVSyncEnabled(true);
				Display.setResizable(false);
				Display.setTitle(title);
				Display.create();
				Keyboard.create();
				Mouse.create();
			} catch (LWJGLException ex) {
				ex.printStackTrace();
			}
		}
		
		glOrtho(-width/2, width/2, -height/2, height/2, -1, 1);
	}
	
	/**
	 * Sets the display mode of the window.
	 */
	public void setDisplayMode(int width, int height, boolean fullscreen) {
		// return if requested DisplayMode is already set
		if ((Display.getDisplayMode().getWidth() == width) && (Display.getDisplayMode().getHeight() == height)
				&& (Display.isFullscreen() == fullscreen)) {
			return;
		}
		try {
			DisplayMode targetDisplayMode = null;
			if (fullscreen) {
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;
				for (DisplayMode current : modes) {
					if ((current.getWidth() == width) && (current.getHeight() == height)) {
						if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
							if ((targetDisplayMode == null)
									|| (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}

						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel())
								&& (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
							targetDisplayMode = current;
							break;
						}
					}
				}
			} else {
				targetDisplayMode = new DisplayMode(width, height);
			}
			if (targetDisplayMode == null) {
				System.out.println("Failed to find value mode: " + width + "x" + height + " fs=" + fullscreen);
				return;
			}
			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullscreen);
		} catch (LWJGLException e) {
			System.out.println("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullscreen + e);
		}
	}

	public void update() {
		//Display.update();
	}

	public void onEvent(Event e) {
		// Initialize
		if (e instanceof InitializeEvent) {
			InitializeEvent ev = (InitializeEvent) e;
		}

		// TickEvent
		else if (e instanceof TickEvent) {
			TickEvent ev = (TickEvent) e;
		}
	}
}
