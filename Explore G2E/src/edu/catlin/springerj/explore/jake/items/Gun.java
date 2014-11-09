package edu.catlin.springerj.explore.jake.items;

import edu.catlin.springerj.g2e.core.AbstractEntity;
import edu.catlin.springerj.g2e.core.Core;
import edu.catlin.springerj.g2e.event.EventListener;
import edu.catlin.springerj.g2e.event.EventManager;
import edu.catlin.springerj.g2e.event.MouseEvent;

public class Gun extends AbstractEntity {

	public Gun(AbstractEntity holder) {
		this(holder, GunComponent.TYPE_FIRE);
	}
	
	public Gun(AbstractEntity holder, int type) {
		add(new GunComponent(holder, type));
	}
	
	@Override
	public void initialize() {
		final AbstractEntity thus = this;
		this.getRootManager().getManager(EventManager.class).register(new EventListener<MouseEvent>() {

			@Override
			public void onEvent(MouseEvent event) {
				if (event.action == MouseEvent.BUTTON_MB1) {
					GunComponent gc = thus.getComponent(GunComponent.class);
					if (gc.currentReloadTime > gc.reloadTime) {
						//thus.getManager().add(new Bullet());
						gc.currentReloadTime = 0.0d;
					}
				}
			}
			
		});
	}

	@Override
	public void update() {
		this.getComponent(GunComponent.class).currentReloadTime += Core.getDefaultTimer().getDeltaTime();
	}

}
