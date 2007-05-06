package ru.amse.smartlang.format;

public class Block implements IBlock {
	private IBlockType type;

	private Whitespace whitespace;

	private IRegion whitespaceRegion;
	
	private String text;

	
	public Block(IBlockType type, String text, Whitespace whitespace, IRegion whitespaceRegion) {
		this.type = type;
		this.text = text;
		this.whitespace = whitespace;
		this.whitespaceRegion = whitespaceRegion;
	}
	
	

	public Block(IBlockType type, Whitespace whitespace,
			IRegion whitespaceRegion) {
		this.type = type;
		this.whitespace = whitespace;
		this.whitespaceRegion = whitespaceRegion;
	}



	public IBlockType getType() {
		return type;
	}

	public Whitespace getWhitespace() {
		return whitespace;
	}

	public IRegion getWhitespaceRegion() {
		return whitespaceRegion;
	}

	public void accept(IBlockVisitor visitor) {
		visitor.visitPrimitive(this);
	}
	
	@Override
	public String toString() {
		return "[" + type.toString() + "]" + whitespace.toString();
	}

	public String getText() {
		return text;
	}



	public void setWhitespace(Whitespace w) {
		whitespace = w;
	}
}
