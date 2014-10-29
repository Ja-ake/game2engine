package edu.catlin.springerj.g2e.event;

public class MouseEvent extends Event {
	public static final int ACTION_PRESS = 		0x01;
	public static final int ACTION_RELEASE = 	0x02;
	public static final int ACTION_MOVE = 		0x03;
	public static final int ACTION_OTHER = 		0x0f;
	
	public static final int BUTTON_MB1 = 		0x11;
	public static final int BUTTON_MB2 = 		0x12;
	public static final int BUTTON_MB3 = 		0x13;
	public static final int BUTTON_MB4 = 		0x14;
	public static final int BUTTON_MB5 = 		0x15;
	
	public int action, button, x, y;
	
	public MouseEvent(int act, int but, int xn, int yn) {
		action = act;
		button = but;
		x = xn;
		y = yn;
	}
	
	public MouseEvent() {
		action = 0x00;
		button = 0x10;
		x = 0;
		y = 0;
	}
}
