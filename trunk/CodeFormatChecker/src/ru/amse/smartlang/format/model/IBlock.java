package ru.amse.smartlang.format.model;

import ru.amse.smartlang.format.impl.Region;

public interface IBlock {
	/**
	 * Get block indent relative to previous block
	 */
	Whitespace getWhitespace();
	
	/**
	 * Get block`s indent region (postion & length) realtive to prevous block
	 * @see IBlock#getWhitespace() 
	 * @return indent`s region
	 */
	IRegion getWhitespaceRegion();
	/**
	 * Get block type description
	 */
	IBlockType getType();
	
	void visit(IBlockWalker walker);
	
	IBlock NULL = new IBlock() {
		IRegion region = new Region(0, 0);
		public IBlockType getType() {
			return IBlockType.NULL;
		}

		public Whitespace getWhitespace() {
			return Whitespace.getInstance(0, 0, 0);
		}

		public IRegion getWhitespaceRegion() {
			return region;
		}

		public void visit(IBlockWalker walker) {
			walker.visitPrimitive(this);
		}
	};
}
