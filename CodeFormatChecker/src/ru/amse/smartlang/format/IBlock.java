package ru.amse.smartlang.format;

public interface IBlock {
	IBlock NULL = new IBlock() {

		public IBlockType getType() {
			return IBlockType.ROOT;
		}

		public Whitespace getWhitespace() {
			return Whitespace.EMPTY;
		}

		public IRegion getWhitespaceRegion() {
			throw new UnsupportedOperationException();
		}

		public void accept(IBlockVisitor visitor) {
			visitor.visitPrimitive(this);
		}

		@Override
		public String toString() {
			return "NULL_BLOCK";
		}

		public String getText() {
			return null;
		}

		public void setWhitespace(Whitespace w) {
			throw new UnsupportedOperationException();
		}
		
	};
	
	Whitespace getWhitespace();
	IRegion getWhitespaceRegion();
	IBlockType getType();
	String getText();
	void setWhitespace(Whitespace w);
	void accept(IBlockVisitor visitor);
}
