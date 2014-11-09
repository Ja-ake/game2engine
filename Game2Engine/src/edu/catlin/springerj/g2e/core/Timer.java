package edu.catlin.springerj.g2e.core;

public class Timer {
	private long nanotimeprev;
	private long nanotimenow;
	private double deltaseconds;
	
	public Timer() {
		nanotimeprev = System.nanoTime();
		nanotimenow = nanotimeprev;
		deltaseconds = 0.0f;
	}
	
	public void update() {
		nanotimenow = System.nanoTime();
		deltaseconds = ((double) nanotimenow - (double) nanotimeprev) / 1000000000.0f;
		nanotimeprev = nanotimenow;
		if (deltaseconds < 0) deltaseconds = 0;
	}
	
	public double getCurrentTime() {
		return (double) System.nanoTime() / 1000000000.0f;
	}
	
	public double getDeltaTime() {
		return Math.min(deltaseconds, 1.0d); 
	}
}
