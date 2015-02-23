package edu.catlin.springerj.g2e.core;

public class Timer {
	private long nanotimeprev;
	private long nanotimenow;
	private double deltaseconds;
	
	private double inverseSpeed;

	public Timer() {
		nanotimeprev = System.nanoTime();
		nanotimenow = nanotimeprev;
		deltaseconds = 0.0f;
		inverseSpeed = 1.0d;
	}

	public double getCurrentTime() {
		return (double) System.nanoTime() / 1000000000.0f;
	}

	public double getDeltaTime() {
		return Math.min(deltaseconds, 0.1d) / inverseSpeed;
	}

	public void update() {
		nanotimenow = System.nanoTime();
		deltaseconds = ((double) nanotimenow - (double) nanotimeprev) / 1000000000.0f;
		nanotimeprev = nanotimenow;
		if (deltaseconds < 0) deltaseconds = 0;
	}
	
	public void setInverseSpeed(double nSpeed) {
		inverseSpeed = nSpeed;
	}
	
	public double getInverseSpeed() {
		return inverseSpeed;
	}
}
