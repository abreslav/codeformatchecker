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
	
	void accept(IBlockVisitor walker);
	
	IBlock NULL = new IBlock() {
		public IBlockType getType() {
			return IBlockType.NULL;
		}

		public Whitespace getWhitespace() {
			throw new UnsupportedOperationException();
		}

		public IRegion getWhitespaceRegion() {
			throw new UnsupportedOperationException();
		}

		public void accept(IBlockVisitor walker) {
			walker.visitPrimitive(this);
		}
	};
}
