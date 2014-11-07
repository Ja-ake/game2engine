package edu.catlin.springerj.g2e.lwjgl.draw;

import edu.catlin.springerj.g2e.core.AbstractComponent;
import edu.catlin.springerj.g2e.core.AbstractEntity;

public class TextComponent extends AbstractComponent {
	public String text;
	
	@Override
	public void initialize(AbstractEntity e) {
		
	}
	
	public TextComponent() {
		text = "";
	}
	
	public TextComponent(String s) {
		text = s;
	}
}
