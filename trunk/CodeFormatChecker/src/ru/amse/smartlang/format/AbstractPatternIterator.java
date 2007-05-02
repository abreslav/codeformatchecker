package ru.amse.smartlang.format;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPatternIterator implements IBlockVisitor {
	private List<IBlockType> parents = new ArrayList<IBlockType>();
	
	private IBlock currentParent;
	
	private IBlock currentLeft;

	protected IBlock getParent() {
		return currentParent;
	}
	
	protected IBlock getLeft() {
		return currentLeft;
	}
		
	public abstract void visit(IBlockType parent, IBlockType left, IBlock thiz);

	public final void visitComposite(ICompositeBlock block) {
		IBlockType parentType = block.getType();
		parents.add(parentType);
		List<IBlock> children = block.getChildren();
		IBlockType left = IBlockType.NULL;
		currentLeft = IBlock.NULL;
		for (int i = 0; i < children.size(); i++) {
			IBlock thiz = children.get(i);
			currentParent = block;
			visit(parentType, left, thiz);
			thiz.accept(this);
			left = thiz.getType();
			currentLeft = thiz;
		}
		parents.remove(parents.size() - 1);
	}

	public final void visitPrimitive(IBlock block) {
	}
}
