package edu.catlin.springerj.g2e.lwjgl;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.AbstractSystem;
import edu.catlin.springerj.g2e.core.Core;

public class WindowSystem extends AbstractSystem {
	WindowComponent wc;
	
	public WindowSystem(AbstractEntity e) {
		wc = e.getComponent(WindowComponent.class);
	}
	
	@Override
	public void initialize() {
		setDisplayMode(wc.width, wc.height, false);
		{
			try {
				if (Display.isCreated()) return;
				Display.setVSyncEnabled(true);
				Display.setResizable(false);
				Display.setTitle(wc.title);
				Display.create();
				Keyboard.create();
				Mouse.create();
			} catch (LWJGLException ex) {
				ex.printStackTrace();
			}
		}
		
		glOrtho(-wc.width/2, wc.width/2, -wc.height/2, wc.height/2, -1, 1);
		update();
	}
	
	@Override
	public void update() {
		Core.setCloseRequested(Display.isCloseRequested());
		
		Display.update();
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
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
}
