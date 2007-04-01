package ru.amse.smartlang.format.model;

public final class Pair<F, S> {
	public final F LEFT;
	public final S RIGHT;

	public Pair(final F left, final S right) {
		LEFT = left;
		RIGHT = right;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((LEFT == null) ? 0 : LEFT.hashCode());
		result = PRIME * result + ((RIGHT == null) ? 0 : RIGHT.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Pair other = (Pair) obj;
		if (LEFT == null) {
			if (other.LEFT != null)
				return false;
		} else if (!LEFT.equals(other.LEFT))
			return false;
		if (RIGHT == null) {
			if (other.RIGHT != null)
				return false;
		} else if (!RIGHT.equals(other.RIGHT))
			return false;
		return true;
	}
}
