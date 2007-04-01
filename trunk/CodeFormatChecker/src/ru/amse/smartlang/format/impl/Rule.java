package ru.amse.smartlang.format.impl;

import ru.amse.smartlang.format.model.IBlockType;
import ru.amse.smartlang.format.model.IRule;
import ru.amse.smartlang.format.model.Whitespace;

public class Rule implements IRule {
	private IBlockType leftBlockType;

	private IBlockType parentBlockType;

	private IBlockType thisBlockType;

	private Whitespace whitespace;

	public Rule(IBlockType leftBlockType, IBlockType parentBlockType,
			IBlockType thisBlockType, Whitespace whitespace) {
		super();
		this.leftBlockType = leftBlockType;
		this.parentBlockType = parentBlockType;
		this.thisBlockType = thisBlockType;
		this.whitespace = whitespace;
	}

	public IBlockType getLeftBlockType() {
		return leftBlockType;
	}

	public IBlockType getParentBlockType() {
		return parentBlockType;
	}

	public IBlockType getThisBlockType() {
		return thisBlockType;
	}

	public Whitespace getWhitespace() {
		return whitespace;
	}

}
