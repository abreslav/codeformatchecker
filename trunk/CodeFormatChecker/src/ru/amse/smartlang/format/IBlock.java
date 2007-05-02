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

		public void clearWhitespace() {
		}
		
		public void recalculateWhitespace(IBlock parent, IBlock left) {
		}
		
		@Override
		public String toString() {
			return "NULL_BLOCK";
		}

		public String getText() {
			return null;
		}
		
	};
	
	Whitespace getWhitespace();
	IRegion getWhitespaceRegion();
	IBlockType getType();
	String getText();
	void clearWhitespace();
	void recalculateWhitespace(IBlock parent, IBlock left);
	void accept(IBlockVisitor visitor);
}
