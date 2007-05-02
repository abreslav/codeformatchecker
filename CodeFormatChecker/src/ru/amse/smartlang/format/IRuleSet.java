package ru.amse.smartlang.format;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface IRuleSet {
	/**
	 * Adds pattern to rule set
	 * 
	 * @param rule
	 */
	void addPattern(Pattern pattern, Whitespace wh);

	/**
	 * Finds whitespace for specified pattern
	 * block types.
	 * 
	 * @param parent
	 * @param left
	 * @param thiz
	 * @return rule for this block type sequence
	 */
	boolean isValidWhitespace(Pattern pattern, Whitespace wh);
	
	public Collection<Map.Entry<Pattern, List<Whitespace>>> getMultipatterns();
	
	public void setMultipattern(Pattern p, List<Whitespace> lw);
	public List<Whitespace> getWhitespacesFor(Pattern pattern);
	public Whitespace findNearestFor(Pattern p, Whitespace sp);

}
