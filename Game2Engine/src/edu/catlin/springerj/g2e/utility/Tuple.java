package edu.catlin.springerj.g2e.utility;

public class Tuple<L, R> {
	public L left;
	public R right;

	public Tuple() {
		this(null, null);
	}

	public Tuple(L l, R r) {
		left = l;
		right = r;
	}
}
