package edu.catlin.springerj.g2e.lwjgl.draw;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class ImageRepeatComponent extends AbstractComponent {
	public int xrepeat, yrepeat;
	
	public ImageRepeatComponent() {
		this(1, 1);
	}
	
	public ImageRepeatComponent(int x, int y) {
		xrepeat = x;
		yrepeat = y;
	}
	
	@Override
	public void initialize(AbstractEntity e) {
		
	}
	 
}
