package ru.amse.smartlang.format.model;

import java.util.HashMap;
import java.util.Map;

public final class Whitespace {
	private static final Map<Integer, Whitespace> map = new HashMap<Integer, Whitespace>();
	
	private final int newLines;

	private final int indents;

	private final int spaces;

	private Whitespace(final int newLines, final int indents, final int spaces) {
		super();
		this.newLines = newLines;
		this.indents = indents;
		this.spaces = spaces;
	}

	public int getIndents() {
		return indents;
	}

	public int getNewLines() {
		return newLines;
	}

	public int getSpaces() {
		return spaces;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + indents;
		result = PRIME * result + newLines;
		result = PRIME * result + spaces;
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
		final Whitespace other = (Whitespace) obj;
		if (indents != other.indents)
			return false;
		if (newLines != other.newLines)
			return false;
		if (spaces != other.spaces)
			return false;
		return true;
	}
	
	public static Whitespace getInstance(final int newLines, final int indents, final int spaces) {
		if(newLines > 0xFF || indents > 0xFF || spaces > 0xFF) {
			return new Whitespace(newLines, indents, spaces);
		}
		int key = (newLines & 0xFF) | ((indents & 0xFF) << 16) | ((spaces & 0xFF) << 24);
		if(map.containsKey(key)) {
			return map.get(key);
		}
		Whitespace w = new Whitespace(newLines, indents, spaces);
		map.put(key, w);
		return w;
	}
}
