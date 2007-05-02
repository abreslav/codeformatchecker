package ru.amse.smartlang.format;

public class NoMatchingRuleException extends RuntimeException {
	private Pattern pattern;

	private static final long serialVersionUID = 8958140139620067359L;
	
	public NoMatchingRuleException(Pattern pattern) {
		super();
		this.pattern = pattern;
	}

	public Pattern getPattern() {
		return pattern;
	}
}
