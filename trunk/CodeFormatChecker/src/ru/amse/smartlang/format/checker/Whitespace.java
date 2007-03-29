package ru.amse.smartlang.format.checker;

public class Whitespace {
	private final int newLines;

	private final int indents;

	private final int spaces;

	public Whitespace(final int newLines, final int indents, final int spaces) {
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

}
