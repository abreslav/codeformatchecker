package ru.amse.smartlang.format;


public class InvalidWhitespaceException extends RuntimeException {
	private static final long serialVersionUID = 5306442141716023521L;
	
	private Pattern pattern;
	
	private Whitespace expected;
	
	private Whitespace occured;

	public InvalidWhitespaceException(Pattern pattern, Whitespace expected, Whitespace occured) {
		super();
		this.pattern = pattern;
		this.expected = expected;
		this.occured = occured;
	}

	public Whitespace getExpected() {
		return expected;
	}

	public Whitespace getOccured() {
		return occured;
	}

	public Pattern getPattern() {
		return pattern;
	}
	
}
