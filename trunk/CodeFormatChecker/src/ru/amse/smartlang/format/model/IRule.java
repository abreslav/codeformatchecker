package ru.amse.smartlang.format.model;

/**
 * Desctibes code formatter rule.
 */
public interface IRule {
	boolean isDecent();
	IBlockType getParentBlockType();
	IBlockType getLeftBlockType();
	IBlockType getThisBlockType();
	Whitespace getWhitespace();
}
