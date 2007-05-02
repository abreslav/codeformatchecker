package ru.amse.smartlang.format;

import java.util.List;

public class CompositeBlock extends Block implements ICompositeBlock {

	public CompositeBlock(IBlockType type, Whitespace whitespace, IRegion whitespaceRegion, List<IBlock> children) {
		super(type, whitespace, whitespaceRegion);
		this.children = children;
	}

	private List<IBlock> children;

	public List<IBlock> getChildren() {
		return children;
	}
	
	@Override
	public void accept(IBlockVisitor visitor) {
		visitor.visitComposite(this);
	}
}
