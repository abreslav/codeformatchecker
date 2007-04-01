package ru.amse.smartlang.format.impl;

import ru.amse.smartlang.format.model.IBlock;
import ru.amse.smartlang.format.model.IBlockType;
import ru.amse.smartlang.format.model.IBlockWalker;
import ru.amse.smartlang.format.model.IRegion;
import ru.amse.smartlang.format.model.Whitespace;

public class Block implements IBlock {
	private IBlockType type;
	private Whitespace whitespace;
	private IRegion whitespaceRegion;
	
	public Block(IBlockType blockType, Whitespace whitespace, IRegion wRegion) {
		super();
		this.type = blockType;
		this.whitespace = whitespace;
		this.whitespaceRegion = wRegion;
	}

	public IBlockType getType() {
		return type;
	}

	public Whitespace getWhitespace() {
		return whitespace;
	}

	public void visit(IBlockWalker walker) {
		walker.visitPrimitive(this);
	}


	public IRegion getWhitespaceRegion() {
		return whitespaceRegion;
	}
}
