package edu.catlin.springerj.g2e.lwjgl;

import org.newdawn.slick.Color;

import edu.catlin.springerj.g2e.lwjgl.draw.Graphics;
import edu.catlin.springerj.g2e.lwjgl.util.Texture;
import edu.catlin.springerj.g2e.math.Color4;

public abstract class DrawCommand {
	protected int layer;

	public abstract void draw();

	public DrawCommand() {
		this(0);
	}

	public DrawCommand(int l) {
		layer = l;
	}

	// layered

	public static DrawCommand drawCircle(int layer, double x, double y, double size, Color4 c) {
		return new DrawCommand() {
			@Override
			public void draw() {
				Graphics.drawCircle(x, y, size, c);
			}
		};
	}

	public static DrawCommand drawLine(int layer, double x1, double y1, double x2, double y2) {
		return new DrawCommand() {
			@Override
			public void draw() {
				Graphics.drawLine(x1, y1, x2, y2);
			}
		};
	}

	public static DrawCommand drawLine(int layer, double x1, double y1, double x2, double y2, Color4 c) {
		return new DrawCommand() {
			@Override
			public void draw() {
				Graphics.drawLine(x1, y1, x2, y2, c);
			}
		};

	}

	public static DrawCommand drawSprite(int layer, Texture s, double x, double y, double angle, double r, double g, double b, double alpha) {
		return new DrawCommand() {
			@Override
			public void draw() {
				Graphics.drawSprite(s, x, y, angle, r, g, b, alpha);
			}
		};

	}

	public static DrawCommand drawText(int layer, String s, double x, double y) {
		return new DrawCommand() {
			@Override
			public void draw() {
				Graphics.drawText(s, x, y);
			}
		};

	}

	public static DrawCommand drawText(int layer, String s, String font, double x, double y, Color c) {
		return new DrawCommand() {
			@Override
			public void draw() {
				Graphics.drawText(s, font, x, y, c);
			}
		};

	}

	public static DrawCommand fillRect(int layer, double x, double y, double w, double h, double r, double g, double b) {
		return new DrawCommand() {
			@Override
			public void draw() {
				Graphics.fillRect(x, y, w, h, r, g, b);
			}
		};

	}

	public static DrawCommand fillRect(int layer, double x, double y, double w, double h, double r, double g, double b, double a) {
		return new DrawCommand() {
			@Override
			public void draw() {
				Graphics.fillRect(x, y, w, h, r, g, b, a);
			}
		};
	}
}
