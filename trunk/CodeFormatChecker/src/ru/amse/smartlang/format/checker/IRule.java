package ru.amse.smartlang.format.checker;

public interface IRule {
	IBlockType getParentBlockType();
	IBlockType getLeftBlockType();
	IBlockType getThisBlockType();
	Whitespace getWhitespace();
}
