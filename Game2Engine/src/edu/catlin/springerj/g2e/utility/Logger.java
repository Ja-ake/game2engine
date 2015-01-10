package edu.catlin.springerj.g2e.utility;

import java.io.PrintStream;

public abstract class Logger {
	private static PrintStream output;
	private static boolean debug;

	static {
		output = System.out;
		debug = false;
	}

	public static boolean getDebug() {
		return debug;
	}

	public static void println() {
		if (debug) System.out.println(Thread.currentThread().getStackTrace());
		output.println();
	}

	public static void println(char out) {
		if (debug) System.out.println(Thread.currentThread().getStackTrace());
		output.println(out);
	}

	public static void println(char[] out) {
		if (debug) System.out.println(Thread.currentThread().getStackTrace());
		output.println(out);
	}

	public static void println(double out) {
		if (debug) System.out.println(Thread.currentThread().getStackTrace());
		output.println(out);
	}

	public static void println(float out) {
		if (debug) System.out.println(Thread.currentThread().getStackTrace());
		output.println(out);
	}

	public static void println(int out) {
		if (debug) System.out.println(Thread.currentThread().getStackTrace());
		output.println(out);
	}

	public static void println(long out) {
		if (debug) System.out.println(Thread.currentThread().getStackTrace());
		output.println(out);
	}

	public static void println(Object out) {
		if (debug) System.out.println(Thread.currentThread().getStackTrace());
		output.println(out);
	}

	public static void println(String out) {
		if (debug) System.out.println(Thread.currentThread().getStackTrace());
		output.println(out);
	}

	public static void setDebug(boolean d) {
		debug = d;
	}
}
