package ru.amse.smartlang.format.model;

import java.io.Serializable;

public interface IRuleSet extends Serializable {
	/**
	 * Adds rule to rule set
	 * 
	 * @param rule
	 */
	void addRule(IRule rule);

	/**
	 * Finds rule for specified block type sequence or to sequance of it`s super
	 * block types.
	 * 
	 * @param parent
	 * @param left
	 * @param thiz
	 * @return rule for this block type sequence
	 */
	IRule getRule(IBlockType parent, IBlockType left, IBlockType thiz);

	/**
	 * Checks if this rule set contains rule for exactly this block type
	 * sequence, without using those supers.
	 * 
	 * @param parent
	 * @param left
	 * @param thiz
	 * @return
	 */
	public boolean containsExactRule(IBlockType parent, IBlockType left,
			IBlockType thiz);
}
